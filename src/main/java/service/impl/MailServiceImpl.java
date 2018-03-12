package service.impl;

import dao.MailDAO;
import dao.MailFolderDAO;
import dao.MailboxDAO;
import dto.FolderResponse;
import dto.MailPreviewResponse;
import dto.MailboxResponse;
import entity.Mail;
import entity.MailFolder;
import entity.Mailbox;
import entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.MailService;
import support.MailUtils;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("MailService")
@Transactional
public class MailServiceImpl implements MailService {

    private final MailDAO mailDAO;
    private final ModelMapper modelMapper;
    private final MailboxDAO mailboxDAO;
    private final MailFolderDAO mailFolderDao;

    @Autowired
    public MailServiceImpl(MailDAO mailDAO, ModelMapper modelMapper, MailboxDAO mailboxDAO, MailFolderDAO mailFolderDao) {
        this.mailDAO = mailDAO;
        this.modelMapper = modelMapper;
        this.mailboxDAO = mailboxDAO;
        this.mailFolderDao = mailFolderDao;
    }

    @Override
    public boolean addAccount(Mailbox mailbox) {
        try {
            MailFolder inbox = new MailFolder();
            inbox.setAlias("收件箱");
            inbox.setFolderType(MailFolder.FolderType.INBOX);
            inbox.setMailbox(mailbox);
            inbox.setMailList(MailUtils.readMails(mailbox, inbox));
            mailbox.getFolders().add(inbox);
            MailFolder trash = new MailFolder();
            trash.setAlias("废纸篓");
            trash.setFolderType(MailFolder.FolderType.TRASH);
            trash.setMailbox(mailbox);
            mailbox.getFolders().add(trash);
            MailFolder sent = new MailFolder();
            sent.setAlias("发件箱");
            sent.setFolderType(MailFolder.FolderType.SENT);
            sent.setMailbox(mailbox);
            mailbox.getFolders().add(sent);
            mailboxDAO.save(mailbox);
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<MailboxResponse> listAccount(User user) {
        return mailboxDAO.findByUser(user.getId()).stream().map(mail -> modelMapper.map(mail, MailboxResponse.class)).collect(Collectors.toList());
    }

    @Override
    public List<FolderResponse> listFolder(Integer boxId, User user) {
        Mailbox account = mailboxDAO.findOne(boxId);
        if (account != null && account.getUser().getId().equals(user.getId())) {
            return account.getFolders().stream().map(f -> modelMapper.map(f, FolderResponse.class)).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public boolean updateAccount(Mailbox mailbox, int boxId, User user) {
        Mailbox target = mailboxDAO.findOne(boxId);
        if (target != null && target.getUser().getId().equals(user.getId())) {
            target.setAlias(mailbox.getAlias());
            target.setPassword(mailbox.getPassword());
            target.setPop3Port(mailbox.getPop3Port());
            target.setPop3Server(mailbox.getPop3Server());
            target.setSmtpPort(mailbox.getSmtpPort());
            target.setSmtpServer(mailbox.getSmtpServer());
            mailboxDAO.update(target);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteAccount(Integer boxId, User user) {
        Mailbox target = mailboxDAO.findOne(boxId);
        if (target != null && target.getUser().getId().equals(user.getId())) {
            mailboxDAO.delete(target);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteAccounts(List<Integer> boxIds, User user) {
        boxIds.stream().map(mailboxDAO::findOne).filter(box -> box.getUser().getId().equals(user.getId())).forEach(mailboxDAO::delete);
        return true;
    }

    @Override
    public List<MailPreviewResponse> readMails(Integer boxId, Integer folderId, User user) {
        try {
            Mailbox account = mailboxDAO.findOne(boxId);
            if (account != null && account.getUser().getId().equals(user.getId())) {
                Optional<MailFolder> first = account.getFolders().stream().filter(f -> f.getId().equals(folderId)).findFirst();
                if (!first.isPresent()) {
                    return null;
                }
                MailFolder folder = first.get();
                folder.getMailList().addAll(MailUtils.readMails(account, folder));
                mailboxDAO.save(account);
                return folder.getMailList().stream().map(mail -> modelMapper.map(mail, MailPreviewResponse.class)).collect(Collectors.toList());
            } else {
                return null;
            }
        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean sendMail(Integer boxId, User user, Mail mail) {
        Mailbox account = mailboxDAO.findOne(boxId);
        if (account != null && account.getUser().getId().equals(user.getId())) {
            MailFolder sent;
            Optional<MailFolder> first = account.getFolders().stream().filter(f -> f.getFolderType().equals(MailFolder.FolderType.SENT)).findFirst();
            if (first.isPresent()) {
                sent = first.get();
                try {
                    MailUtils.sendMail(account, mail);
                } catch (MessagingException e) {
                    e.printStackTrace();
                    return false;
                }
                mail.setMailFolder(sent);
                sent.getMailList().add(mail);
                mailFolderDao.update(sent);
                return true;
            }

        }
        return false;
    }

    @Override
    public boolean deleteMail(Integer folderId, Integer mailId, User user) {
        MailFolder folder = mailFolderDao.findOne(folderId);
        if (folder != null && folder.getMailbox().getUser().getId().equals(user.getId())) {
            Mail mail = mailDAO.findOne(mailId);
            if (mail != null && mail.getMailFolder().getId().equals(folderId)) {
                return moveOrDeleteMail(folder, mail);
            }
        }
        return false;
    }

    @Override
    public boolean markAsSeen(Integer mailId, User user) {
        Mail mail = mailDAO.findOne(mailId);
        if (mail != null && mail.getMailFolder().getMailbox().getUser().getId().equals(user.getId())) {
            mail.setSeen(!mail.isSeen());
            mailDAO.update(mail);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteMails(Integer folderId, List<Integer> mailIds, User user) {
        MailFolder folder = mailFolderDao.findOne(folderId);
        if (folder != null && folder.getMailbox().getUser().getId().equals(user.getId())) {
            mailIds.stream().map(mailDAO::findOne).filter(mail -> mail.getMailFolder().getId().equals(folderId)).forEach(mail -> moveOrDeleteMail(folder, mail));
            return true;
        }
        return false;
    }

    private boolean moveOrDeleteMail(MailFolder folder, Mail mail) {
        MailFolder trash;
        if (folder.getFolderType().equals(MailFolder.FolderType.INBOX)) {
            Optional<MailFolder> first = folder.getMailbox().getFolders().stream().filter(f -> f.getFolderType().equals(MailFolder.FolderType.TRASH)).findFirst();
            if (first.isPresent()) {
                trash = first.get();
                mail.setMailFolder(trash);
                trash.getMailList().add(mail);
                mailFolderDao.update(trash);
                return true;
            }
        } else {
            mailDAO.delete(mail);
            return true;
        }
        return false;
    }

}
