package com.example.had.entity;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.UUID;

@Entity(name = "Report")
@Table(
        name = "report_master"
)
public class report {
    @Id
    @SequenceGenerator(
            name = "report_sequence",
            sequenceName = "report_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = UUID,
            generator = "report_sequence"
    )
    @Column(
            name = "report_id",
            length = 12
    )
    private String id;


    @Column(
            name = "week_number",
            nullable = false
    )
    private int weekNumber;


    @Column(
            name = "session_number",
            nullable = false
    )
    private int sessionNumber;


    @Column(
            name = "percentage_obtained",
            nullable = false,
            precision = 2
    )
    private float percentageObtained;


    @Column(
            name = "last_session_stamp"
    )
    private String lastSessionStamp;


    @Column(
            name = "last_session_completed"
    )
    private String lastSessionCompleted;


    @Column(
            name = "number_of_session_completed"
    )
    private int sessionCompleted;

    @Column(
            name = "number_of_week_completed"
    )
    private int weekCompleted;


    @OneToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "user_id"
    )
    private user user;

    public report() {
    }

    public report(String id,
                  int weekNumber,
                  int sessionNumber,
                  float percentageObtained,
                  String lastSessionStamp,
                  String lastSessionCompleted,
                  int sessionCompleted,
                  int weekCompleted) {
        this.id = id;
        this.weekNumber = weekNumber;
        this.sessionNumber = sessionNumber;
        this.percentageObtained = percentageObtained;
        this.lastSessionStamp = lastSessionStamp;
        this.lastSessionCompleted = lastSessionCompleted;
        this.sessionCompleted = sessionCompleted;
        this.weekCompleted = weekCompleted;
    }

    public report(String id,
                  int weekNumber,
                  int sessionNumber,
                  float percentageObtained,
                  String lastSessionStamp,
                  String lastSessionCompleted,
                  int sessionCompleted,
                  int weekCompleted,
                  com.example.had.entity.user user) {
        this.id = id;
        this.weekNumber = weekNumber;
        this.sessionNumber = sessionNumber;
        this.percentageObtained = percentageObtained;
        this.lastSessionStamp = lastSessionStamp;
        this.lastSessionCompleted = lastSessionCompleted;
        this.sessionCompleted = sessionCompleted;
        this.weekCompleted = weekCompleted;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    public int getSessionNumber() {
        return sessionNumber;
    }

    public void setSessionNumber(int sessionNumber) {
        this.sessionNumber = sessionNumber;
    }

    public float getPercentageObtained() {
        return percentageObtained;
    }

    public void setPercentageObtained(float percentageObtained) {
        this.percentageObtained = percentageObtained;
    }

    public String getLastSessionStamp() {
        return lastSessionStamp;
    }

    public void setLastSessionStamp(String lastSessionStamp) {
        this.lastSessionStamp = lastSessionStamp;
    }

    public String getLastSessionCompleted() {
        return lastSessionCompleted;
    }

    public void setLastSessionCompleted(String lastSessionCompleted) {
        this.lastSessionCompleted = lastSessionCompleted;
    }

    public int getSessionCompleted() {
        return sessionCompleted;
    }

    public void setSessionCompleted(int sessionCompleted) {
        this.sessionCompleted = sessionCompleted;
    }

    public int getWeekCompleted() {
        return weekCompleted;
    }

    public void setWeekCompleted(int weekCompleted) {
        this.weekCompleted = weekCompleted;
    }

    public com.example.had.entity.user getUser() {
        return user;
    }

    public void setUser(com.example.had.entity.user user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "report{" +
                "id='" + id + '\'' +
                ", weekNumber=" + weekNumber +
                ", sessionNumber=" + sessionNumber +
                ", percentageObtained=" + percentageObtained +
                ", lastSessionStamp='" + lastSessionStamp + '\'' +
                ", lastSessionCompleted='" + lastSessionCompleted + '\'' +
                ", sessionCompleted=" + sessionCompleted +
                ", weekCompleted=" + weekCompleted +
                ", user=" + user +
                '}';
    }
}