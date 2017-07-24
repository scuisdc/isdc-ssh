package Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-23.
 */
@Entity
@Table(name = "blog_post")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"accessToken"})
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Lob
    @Column(name = "content")
    private String content;

    @Column(name = "title")
    private String title;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_date")
    private Date lastModified;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = User.class, optional = false)
    @JoinColumn(name = "user_id")
    private User author;

    @Column(name = "actived")
    private Boolean actived;

    @ManyToMany(targetEntity = Tag.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "post")
    private List<Tag> tags;

    @OneToMany(targetEntity = Comment.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "post")
    private List<Comment> comments;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Boolean getActived() {
        return actived;
    }

    public void setActived(Boolean actived) {
        this.actived = actived;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
