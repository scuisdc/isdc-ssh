package entity;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CTFFlagGetter")

@JsonInclude(JsonInclude.Include.NON_NULL)

public class CTFFlagGetter implements Serializable{


    @Id
    @Column
    private int problemID;
    @Id
    @Column
    private String userEmail;

    @Column
    private String time;


    public CTFFlagGetter(int problemID, String userEmail, String time) {
        this.problemID = problemID;
        this.userEmail = userEmail;
        this.time = time;

    }

    public CTFFlagGetter() {

    }

    public int getProblemID() {
        return problemID;
    }

    public void setProblemID(int problemID) {
        this.problemID = problemID;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
