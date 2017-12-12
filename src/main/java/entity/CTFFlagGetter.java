package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ctf_flag_getter")
public class CTFFlagGetter implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String userEmail;

    private Date time;

    @JsonIgnore
    @ManyToOne(targetEntity = CTFProblem.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CTFProblem problem;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public CTFProblem getProblem() {
        return problem;
    }

    public void setProblem(CTFProblem problem) {
        this.problem = problem;
    }
}
