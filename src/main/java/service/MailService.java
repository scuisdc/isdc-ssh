package service;

import entity.Mail;
import entity.Mailbox;

public interface MailService {
    void sendMail(Mailbox mailbox, Mail mail);
}
