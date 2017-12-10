
package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cms_course")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Course {
    @Transient
    @JsonIgnore
    private static SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");
    @Transient
    @JsonIgnore
    private static SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "week")
    private String week;
    @Temporal(TemporalType.TIME)
    @Column(name = "time_start")
    private Date startTime;
    @Temporal(TemporalType.TIME)
    @Column(name = "time_end")
    private Date endTime;
    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date courseDate;
    @Column(name = "host")
    private String host;
    @Column(name = "content")
    private String content;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private List<File> files;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Schedule.class)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }


    public String getTime() {
        return formatTime.format(startTime) + "-" + formatTime.format(endTime);
    }


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getDate() {
        return formatDate.format(courseDate);
    }


    public Date getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(Date courseDate) {
        this.courseDate = courseDate;
    }
}
