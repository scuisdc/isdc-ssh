package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-9-9.
 */
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "application_form")
public class ApplicationForm {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "openid", unique = true)
    private String openid;

    @Column(name = "name")
    private String name;

    @Column(name = "stu_id")
    private Long stuId;

    @Lob
    @Column(name = "content")
    private String content;
}
