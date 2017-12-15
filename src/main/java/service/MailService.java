package service;

import dto.FolderResponse;
import dto.MailboxResponse;
import entity.Mailbox;
import entity.User;

import java.util.List;

public interface MailService {

    boolean addAccount(Mailbox mailbox);

    List<MailboxResponse> listAccount(User user);

    List<FolderResponse> listFolder(Integer boxId);

    boolean updateAccount(Mailbox mailbox, int boxId, User user);

    boolean deleteAccount(Integer boxId, User user);

    boolean deleteAccounts(List<Integer> boxIds, User user);
}
