package support;

import entity.Mail;
import entity.MailAttachment;
import entity.MailFolder;
import entity.Mailbox;
import org.apache.james.mime4j.message.*;
import org.apache.james.mime4j.message.BodyPart;
import org.apache.james.mime4j.message.Message;
import org.apache.james.mime4j.message.Multipart;
import org.apache.james.mime4j.parser.MimeEntityConfig;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MailUtils {
    private static void parseBodyParts(Multipart multipart, StringBuffer txtBody, StringBuffer htmlBody, List<BodyPart> attachments) throws IOException {
        for (BodyPart part : multipart.getBodyParts()) {
            if (part.isMimeType("text/plain")) {
                String txt = getTxtPart(part);
                txtBody.append(txt);
            } else if (part.isMimeType("text/html")) {
                String html = getTxtPart(part);
                htmlBody.append(html);
            } else if (part.getDispositionType() != null && !part.getDispositionType().equals("")) {
                attachments.add(part);
            }

            if (part.isMultipart()) {
                parseBodyParts((Multipart) part.getBody(), txtBody, htmlBody, attachments);
            }
        }
    }

    private static String getTxtPart(Entity part) throws IOException {
        TextBody tb = (TextBody) part.getBody();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        tb.writeTo(baos);
        return new String(baos.toByteArray());
    }

    public static Mail parseMessage(InputStream data) {
        Mail mail = new Mail();
        mail.setAttachments(new ArrayList<>());
        StringBuffer txtBody = new StringBuffer();
        StringBuffer htmlBody = new StringBuffer();
        List<BodyPart> attachments = new ArrayList();
        try {
            MimeEntityConfig config = new MimeEntityConfig();
            config.setMaxContentLen(-1);
            config.setCountLineNumbers(false);
            config.setMaxHeaderCount(200);
            config.setMaxLineLen(-1);
            config.setStrictParsing(false);
            Message mimeMsg = new Message(data, config);

            mail.setTo(mimeMsg.getTo() == null ? null : mimeMsg.getTo().toString());
            mail.setFrom(mimeMsg.getFrom() == null ? null : mimeMsg.getFrom().toString());
            mail.setSubject(mimeMsg.getSubject());
            mail.setSendDate(mimeMsg.getDate());
            if (mimeMsg.isMultipart()) {
                Multipart multipart = (Multipart) mimeMsg.getBody();
                parseBodyParts(multipart, txtBody, htmlBody, attachments);
            } else {
                String text = getTxtPart(mimeMsg);
                txtBody.append(text);
            }

            mail.setTextBody(txtBody.toString());
            mail.setHtmlBody(htmlBody.toString());

            for (BodyPart attach : attachments) {
                MailAttachment attachment = new MailAttachment();
                attachment.setFileName(attach.getFilename());
                try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                    BinaryBody bb = (BinaryBody) attach.getBody();
                    bb.writeTo(outputStream);
                    attachment.setFile(outputStream.toByteArray());
                }
                mail.getAttachments().add(attachment);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return mail;
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
                return new PasswordAuthentication(mailbox.getAccount(), mailbox.getPassword());
                //fwajedvovvsndddd
                //vEujkWzyK658
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
                Mail mail = MailUtils.parseMessage(message.getInputStream());
                if (mail.getFrom() == null) {
                    mail.setFrom(message.getFrom()[0].toString());
                }
                if (mail.getSendDate() == null) {
                    mail.setSendDate(message.getSentDate());
                }
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
}
