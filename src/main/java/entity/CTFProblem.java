package entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by WaterMelon on 2017/11/8.
 */

@Entity
@Table(name = "CTFProblem")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CTFProblem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String title;
    @Column
    private String data;
    @Column
    private String time;
    @Column
    private String userEmail;
    @Column
    private String flag;
    @Column
    private String magnet;
    @Column
    private String UserName;

    public CTFProblem() {
    }

    public CTFProblem(String title, String data, String time, String userEmail, String flag, String magnet) {
        this.title = title;
        this.data = data;
        this.time = time;
        this.userEmail = userEmail;
        this.flag = flag;
        this.magnet = magnet;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getMagnet() {
        return magnet;
    }

    public void setMagnet(String magnet) {
        this.magnet = magnet;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

}

