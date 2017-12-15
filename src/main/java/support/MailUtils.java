package support;

import entity.Mail;
import entity.MailFolder;
import entity.Mailbox;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class MailUtils {
    private static void parseBodyParts(MimeMultipart multipart, StringBuffer txtBody, StringBuffer htmlBody, List<javax.mail.BodyPart> attachments) throws IOException, MessagingException {
        int count = multipart.getCount();
        for (int i = 0; i < count; i++) {
            javax.mail.BodyPart part = multipart.getBodyPart(i);
            if (part.isMimeType("text/plain")) {
                String txt = getTxtPart(part);
                txtBody.append(txt);
            } else if (part.isMimeType("text/html")) {
                String html = getTxtPart(part);
                htmlBody.append(html);
            } else if (part.getDisposition() != null && !part.getDisposition().equals("")) {
                attachments.add(part);
            }
            if (isMultipart(part) && part.getContent() instanceof MimeMultipart) {
                parseBodyParts((MimeMultipart) part.getContent(), txtBody, htmlBody, attachments);
            }
        }
    }

    private static String getTxtPart(javax.mail.BodyPart part) throws IOException, MessagingException {
        return (String) part.getContent();
    }

    public static void sendMail(Mailbox mailbox, Mail mail) {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(mailbox.getSmtpServer());
        sender.setPort(mailbox.getSmtpPort());
        sender.setUsername(mailbox.getAccount());
        sender.setPassword(mailbox.getPassword());
        Properties pro = System.getProperties();
        pro.put("mail.smtp.auth", "true");
        pro.put("mail.smtp.ssl.enable", "true");
        pro.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        sender.setJavaMailProperties(pro);

        MimeMessage message = sender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(mailbox.getAccount());
            helper.setTo(mail.getTo());
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getTextBody() == null ? mail.getHtmlBody() : mail.getTextBody(), mail.getTextBody() == null);
            sender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Mail> readMails(Mailbox mailbox, MailFolder mailFolder) throws MessagingException {
        List<Mail> mails = new ArrayList<>();
        Properties props = new Properties();
        props.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.pop3.socketFactory.fallback", "false");
        props.put("mail.pop3.socketFactory.port", mailbox.getPop3Port());
        props.put("mail.pop3.port", mailbox.getPop3Port());
        props.put("mail.pop3.host", mailbox.getPop3Server());
        props.put("mail.pop3.user", mailbox.getAccount());
        props.put("mail.store.protocol", "pop3");

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                System.out.println(mailbox.getAccount() + "-------------------" + mailbox.getPassword());
                return new PasswordAuthentication(mailbox.getAccount(), mailbox.getPassword());
            }
        };

        Session session = Session.getDefaultInstance(props, auth);

        Store store = session.getStore("pop3");
        store.connect(mailbox.getPop3Server(), mailbox.getAccount(), mailbox.getPassword());

        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);

        javax.mail.Message[] messages = inbox.getMessages();
        for (javax.mail.Message message : messages) {
            try {
                Mail mail = new Mail();
                StringBuffer txtBody = new StringBuffer();
                StringBuffer htmlBody = new StringBuffer();
                List<javax.mail.BodyPart> attachments = new ArrayList();
                mail.setFrom("");
                mail.setTo("");
                mail.setAttachments(new ArrayList<>());
                Arrays.asList(message.getFrom()).forEach(address -> {
                    if (address instanceof InternetAddress) {
                        mail.setFrom(mail.getFrom() + ((InternetAddress) address).getPersonal() +
                                " <" + ((InternetAddress) address).getAddress() + ">,");
                    } else {
                        mail.setFrom(mail.getFrom() + address.toString() + ",");
                    }
                });

                Arrays.asList(message.getRecipients(javax.mail.Message.RecipientType.TO)).forEach(address -> {
                    if (address instanceof InternetAddress) {
                        mail.setTo(mail.getTo() + ((InternetAddress) address).getPersonal() +
                                " <" + ((InternetAddress) address).getAddress() + ">,");
                    } else {
                        mail.setTo(mail.getTo() + address.toString() + ",");
                    }
                });
                mail.setContentType(message.getContentType());
                Object content = message.getContent();
                if (content instanceof MimeMultipart) {
                    parseBodyParts((MimeMultipart) content, txtBody, htmlBody, attachments);
                } else {
                    if (content instanceof javax.mail.BodyPart) {
                        txtBody.append(getTxtPart((javax.mail.BodyPart) content));
                    } else {
                        txtBody.append(content);
                    }
                }
//                for (javax.mail.BodyPart attach : attachments) {
//                    MailAttachment attachment = new MailAttachment();
//                    attachment.setFileName(attach.getFileName());
//                    try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
//                        BinaryBody bb =  attach.getContent();
//                        bb.writeTo(outputStream);
//                        attachment.setFile(outputStream.toByteArray());
//                    }
//                    mail.getAttachments().add(attachment);
//                }
                mail.setTextBody(txtBody.toString());
                mail.setHtmlBody(htmlBody.toString());
                mail.setSubject(message.getSubject());
                mail.setSendDate(message.getSentDate());
                mail.setMailFolder(mailFolder);
                mails.add(mail);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        inbox.close(false);
        store.close();
        return mails;
    }

    private static boolean isMultipart(javax.mail.BodyPart part) {

        try {
            String[] f = part.getHeader("Content-Type");
            return f != null && f.length > 0 && part.isMimeType("multipart/");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;

    }
}
