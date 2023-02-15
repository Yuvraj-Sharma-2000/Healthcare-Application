package com.example.had.entity;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "User")
@Table(
        name = "user_master",
        uniqueConstraints =
        @UniqueConstraint(
                name = "user_email_unique",
                columnNames = "email"
        )
)
public class User {
    @Id
    @GeneratedValue(generator = "uuid4")
    @GenericGenerator(name = "UUID", strategy = "uuid4")
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(
            name = "user_id"
    )
    private UUID id;
    @Column(
            name = "email",
            nullable = false
    )
    private String email;

    @Column(
            name = "fname",
            nullable = false,
            length = 15
    )
    private String firstName;


    @Column(
            name = "lname",
            nullable = false,
            length = 15
    )
    private String lastName;


    @Column(
            name = "mname",
            nullable = false,
            length = 15
    )
    private String middleName;


    @Column(
            name = "gender",
            nullable = false
    )
    private String gender;



    @Column(
            name = "date_of_birth",
            nullable = false
    )
    private LocalDate dob;


    @Column(
            name = "contact",
            nullable = false,
            length = 13
    )
    private String contact;


    @Column(
            name = "address",
            nullable = false,
            length = 100
    )
    private String address;


    @Column(
            name = "registration_stamp",
            nullable = false
    )
    private String registrationStamp;


    @Column(
            name = "depression_severity"
    )
    private float depressionSeverity;
    @ManyToOne
    private Doctor doctor;
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Chat> chatList = new ArrayList<>();


    @OneToOne(
            mappedBy = "user",
            orphanRemoval = true
    )
    private Report report;
    public void addReport(Report report){
        this.setReport(report);
        report.setUser(this);
    }
    public void removeReport(Report report){
        this.setReport(null);
        report.setUser(null);
    }
    public void addChat(Chat chat){
        if(!this.chatList.contains(chat)){
            chatList.add(chat);
            chat.setUser(this);
        }
    }
    public void removeChat(Chat chat){
        if (this.chatList.contains(chat)){
            this.chatList.remove(chat);
            chat.setUser(null);
        }
    }
    public User() {
    }

    public User(String email,
                String firstName,
                String lastName,
                String middleName,
                String gender,
                LocalDate dob,
                String contact,
                String address,
                String registrationStamp,
                float depressionSeverity) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.gender = gender;
        this.dob = dob;
        this.contact = contact;
        this.address = address;
        this.registrationStamp = registrationStamp;
        this.depressionSeverity = depressionSeverity;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRegistrationStamp() {
        return registrationStamp;
    }

    public void setRegistrationStamp(String registrationStamp) {
        this.registrationStamp = registrationStamp;
    }

    public float getDepressionSeverity() {
        return depressionSeverity;
    }

    public void setDepressionSeverity(float depressionSeverity) {
        this.depressionSeverity = depressionSeverity;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public List<Chat> getChatList() {
        return chatList;
    }

    public void setChatList(List<Chat> chatList) {
        this.chatList = chatList;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    @Override
    public String toString() {
        return "user{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", gender='" + gender + '\'' +
                ", dob=" + dob +
                ", contact='" + contact + '\'' +
                ", address='" + address + '\'' +
                ", registrationStamp='" + registrationStamp + '\'' +
                ", depressionSeverity=" + depressionSeverity +
                ", doctorList=" + doctor +
                ", chatList=" + chatList +
                ", report=" + report +
                '}';
    }
}