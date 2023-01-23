package com.example.had.entity;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.UUID;

@Entity(name = "User")
@Table(
        name = "user_master",
        uniqueConstraints =
        @UniqueConstraint(
                name = "user_email_unique",
                columnNames = "email"
        )
)
public class user {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = UUID,
            generator = "user_sequence"
    )
    @Column(
            name = "user_id"
    )
    private String id;


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
            length = 10
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


    @ManyToMany(
            mappedBy = "userList"
    )
    private List<doctor> doctorList = new ArrayList<>();


    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<chat> chatList = new ArrayList<>();


    @OneToOne(
            mappedBy = "user",
            orphanRemoval = true
    )
    private report report;

    public user() {
    }

    public user(String id,
                String email,
                String firstName,
                String lastName,
                String middleName,
                String gender,
                LocalDate dob,
                String contact,
                String address,
                String registrationStamp,
                float depressionSeverity) {
        this.id = id;
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

    public user(String id,
                String email,
                String firstName,
                String lastName,
                String middleName,
                String gender,
                LocalDate dob,
                String contact,
                String address,
                String registrationStamp,
                float depressionSeverity,
                List<doctor> doctorList,
                List<chat> chatList,
                com.example.had.entity.report report) {
        this.id = id;
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
        this.doctorList = doctorList;
        this.chatList = chatList;
        this.report = report;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public List<doctor> getDoctorList() {
        return doctorList;
    }

    public void setDoctorList(List<doctor> doctorList) {
        this.doctorList = doctorList;
    }

    public List<chat> getChatList() {
        return chatList;
    }

    public void setChatList(List<chat> chatList) {
        this.chatList = chatList;
    }

    public com.example.had.entity.report getReport() {
        return report;
    }

    public void setReport(com.example.had.entity.report report) {
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
                ", doctorList=" + doctorList +
                ", chatList=" + chatList +
                ", report=" + report +
                '}';
    }
}