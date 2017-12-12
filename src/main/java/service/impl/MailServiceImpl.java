package service.impl;

import entity.Mail;
import entity.Mailbox;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import service.MailService;

import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.util.Properties;

@Service("MailService")
@Transactional
public class MailServiceImpl implements MailService {
    @Override
    public void sendMail(Mailbox mailbox, Mail mail) {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();

        sender.setHost(mailbox.getSmtpServer());
        sender.setPort(mailbox.getSmtpPort());
        sender.setUsername(mailbox.getAccount());
        sender.setPassword(mailbox.getPassword()); // 这里要用邀请码，不是你登录邮箱的密码

        Properties pro = System.getProperties(); // 下面各项缺一不可
        pro.put("mail.smtp.auth", "true");
        pro.put("mail.smtp.ssl.enable", "true");
        pro.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        sender.setJavaMailProperties(pro);

        MimeMessage message = sender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(mailbox.getAccount()); // 发送人
            helper.setTo(mail.getReceiver()); // 收件人
            helper.setSubject(mail.getTitle()); // 标题
            helper.setText(mail.getContent()); // 内容
            sender.send(message);
            System.out.println("发送完毕！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
