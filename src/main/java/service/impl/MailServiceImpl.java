package service.impl;

import dao.MailDAO;
import dao.MailFolderDAO;
import dao.MailboxDAO;
import dto.FolderResponse;
import dto.MailboxResponse;
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
            inbox.setAlias("shoujianxiang");
            inbox.setFolderType(MailFolder.FolderType.INBOX);
            inbox.setMailbox(mailbox);
            inbox.setMailList(MailUtils.readMails(mailbox, inbox));
            mailbox.getFolders().add(inbox);
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
    public List<FolderResponse> listFolder(Integer boxId) {
        return mailboxDAO.findOne(boxId).getFolders().stream().map(f -> modelMapper.map(f, FolderResponse.class)).collect(Collectors.toList());
    }

}
