package entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-24.
 */
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "index_banner")
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "detail")
    private String detail;

    @Column(name = "router_link")
    private String routerLink;

    @Column(name = "href")
    private String href;

    @Column(name = "action")
    private String action;

    @Column(name = "background_pic")
    private String pic;

    @Column(name = "piority")
    private Integer piority;

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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getPiority() {
        return piority;
    }

    public void setPiority(Integer piority) {
        this.piority = piority;
    }

    public String getRouterLink() {
        return routerLink;
    }

    public void setRouterLink(String routerLink) {
        this.routerLink = routerLink;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
