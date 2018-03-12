package service;

import dto.FolderResponse;
import dto.MailPreviewResponse;
import dto.MailboxResponse;
import entity.Mail;
import entity.Mailbox;
import entity.User;

import java.util.List;

public interface MailService {

    boolean addAccount(Mailbox mailbox);

    List<MailboxResponse> listAccount(User user);

    List<FolderResponse> listFolder(Integer boxId, User user);

    boolean updateAccount(Mailbox mailbox, int boxId, User user);

    boolean deleteAccount(Integer boxId, User user);

    boolean deleteAccounts(List<Integer> boxIds, User user);

    List<MailPreviewResponse> readMails(Integer boxId, Integer folderId, User user);

    boolean sendMail(Integer boxId, User user, Mail mail);

    boolean deleteMail(Integer folderId, Integer mailId, User user);

    boolean markAsSeen(Integer mailId, User user);

    boolean deleteMails(Integer folderId, List<Integer> mailIds, User user);
}
