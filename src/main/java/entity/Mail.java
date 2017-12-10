package entity;

import javax.persistence.*;

@Entity
@Table(name = "mail")
public class Mail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;

    private String sender;

    private String content;

    private String receiver;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Mailbox.class)
    private Mailbox mailbox;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE})
    private MailFolder mailFolder;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Mailbox getMailbox() {
        return mailbox;
    }

    public void setMailbox(Mailbox mailbox) {
        this.mailbox = mailbox;
    }

    public MailFolder getMailFolder() {
        return mailFolder;
    }

    public void setMailFolder(MailFolder mailFolder) {
        this.mailFolder = mailFolder;
    }
}
