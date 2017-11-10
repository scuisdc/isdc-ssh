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
    private Date time;
    @Column
    private String userEmail;
    @Column
    private String flag;
    @Column
    @ElementCollection
    private Set<String> flagGetters;

    public CTFProblem(String title, String data, Date time, String userEmail, String flag) {
        this.title = title;
        this.data = data;
        this.time = time;
        this.userEmail = userEmail;
        this.flag = flag;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
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
   public Set<String> getFlagGetters() {
        return flagGetters;
    }

    public void setFlagGetters(Set<String> flagGetters) {
        this.flagGetters = flagGetters;
    }
}

