package qrizzly.project.model;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Ivan on 19.01.2017.
 */
@Entity
@Table(name = "reports")
public class Reports {
    @Id
    //@GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "StartDate")
    private Date startDate;

    @Column(name = "EndDate")
    private Date endDate;

    @Column(name = "Performer")
    private String performer;

    @Column(name = "Activity")
    private String activity;

    public Reports() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
    public String getEndDateInString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d,yyyy", Locale.ENGLISH);
        return dateFormat.format(endDate);
    }

    public String getStartDateInString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d,yyyy", Locale.ENGLISH);
        return dateFormat.format(startDate);
    }

    @Override
    public String toString() {
        return "Reports{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", performer='" + performer + '\'' +
                ", activity='" + activity + '\'' +
                '}';
    }
}
