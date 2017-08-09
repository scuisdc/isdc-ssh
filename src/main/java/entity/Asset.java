package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.*;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-21.
 */
@Entity
@Table(name = "asset")
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Asset {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name", length = 20)
    private String name;

    @Column(name = "money")
    private Integer money;


    public Asset() {
    }
    public Asset(String name,int money) {
        this.name = name;
        this.money=money;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setMoney(Integer money) {
        this.money = money;
    }
    public String getName(){return name;};
    public Integer getMoney(){return money;}
}
