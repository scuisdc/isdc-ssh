package entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-9-9.
 */
@Entity
@Table(name = "application_form")
public class ApplicationForm {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "openid", unique = true)
    private String openid;

    @Column(name = "name")
    private String name;

    @Column(name = "stu_id")
    private Long stuId;

    @Column(name = "tel")
    private Long tel;

    @Column(name = "email")
    private String email;

    @Lob
    @Column(name = "introduce")
    private String introduce;

    @Lob
    @Column(name = "description")
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "gender")
    private String gender;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "interview")
    private Date interview;

    @Lob
    @Column(name = "judge")
    private String judge;

    @Column(name = "block", columnDefinition = "boolean default false", nullable = false)
    private Boolean block;

    @Column(name = "pass", columnDefinition = "boolean default false", nullable = false)
    private Boolean pass;

    @Column(name = "reason")
    private String reason;

    @Column(name = "impression_score")
    private Double impressionScore;

    @Column(name = "tech_score")
    private Double techScore;

    @Column(name = "integrated_score")
    private Double integratedScore;

    @Column(name = "interviewer")
    private String interviewer;

    public Boolean getBlock() {
        return block;
    }

    public void setBlock(Boolean block) {
        this.block = block;
    }

    public Boolean getPass() {
        return pass;
    }

    public void setPass(Boolean pass) {
        this.pass = pass;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Double getImpressionScore() {
        return impressionScore;
    }

    public void setImpressionScore(Double impressionScore) {
        this.impressionScore = impressionScore;
    }

    public Double getTechScore() {
        return techScore;
    }

    public void setTechScore(Double techScore) {
        this.techScore = techScore;
    }

    public Double getIntegratedScore() {
        return integratedScore;
    }

    public void setIntegratedScore(Double integratedScore) {
        this.integratedScore = integratedScore;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStuId() {
        return stuId;
    }

    public void setStuId(Long stuId) {
        this.stuId = stuId;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getTel() {
        return tel;
    }

    public void setTel(Long tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getInterview() {
        return interview;
    }

    public void setInterview(Date interview) {
        this.interview = interview;
    }

    public String getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(String interviewer) {
        this.interviewer = interviewer;
    }

    public String getJudge() {
        return judge;
    }

    public void setJudge(String judge) {
        this.judge = judge;
    }
}
