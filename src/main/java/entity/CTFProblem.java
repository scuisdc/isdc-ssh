package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by WaterMelon on 2017/11/8.
 */

@Entity
@Table(name = "CTFProblem")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CTFProblem {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String title;
    @Column
    private String data;
    @Column
    private Date date;

}

