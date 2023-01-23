package com.example.had.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;
import static jakarta.persistence.GenerationType.UUID;

@Entity(name = "Doctor")
@Table(
        name = "doctor_master",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "doctor_email_unique",
                        columnNames = "email"
                ),
                @UniqueConstraint(
                        name = "doctor_registration_number_unique",
                        columnNames = "registration_number"
                ),
                @UniqueConstraint(
                        name = "doctor_contact_unique",
                        columnNames = "contact"
                )
        }
)
public class doctor {
    @Id
    @SequenceGenerator(
            name = "doctor_sequence",
            sequenceName = "doctor_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = UUID,
            generator = "doctor_sequence"
    )
    @Column(
            name = "doctor_id"
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
            name = "age",
            nullable = false
    )
    private int age;


    @Column(
            name = "degree",
            nullable = false
    )
    private String degree;


    @Column(
            name = "specialisation",
            nullable = true
    )
    private String specialisation;


    @Column(
            name = "experience",
            nullable = false
    )
    private int experience;


    @Column(
            name = "address",
            nullable = false,
            length = 100
    )
    private String address;


    @Column(
            name = "contact",
            nullable = false,
            length = 10
    )
    private String contact;


    @Column(
            name = "image_url",
            nullable = true
    )
    private String imageUrl;


    @Column(
            name = "rating",
            nullable = true
    )
    private int rating;


    @Column(
            name = "patient_limit",
            nullable = false
    )
    private int patientLimit;


    @Column(
            name = "patient_count",
            nullable = false
    )
    private int patientCount;


    @Column(
            name = "registration_number",
            nullable = false
    )
    private String registrationNumber;


    @Column(
            name = "registration_stamp",
            nullable = false
    )
    private String registrationStamp;


    @ManyToMany(
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "assigned_doctors",
            joinColumns = @JoinColumn(
                    name = "doctor_id",
                    foreignKey = @ForeignKey(
                            name = "assigned_doctor_doctor_id_fk"
                    )
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "patient_id",
                    foreignKey = @ForeignKey(
                            name = "assigned_doctor_user_id_fk"
                    )
            )
    )
    private List<user> userList = new ArrayList<>();


    @OneToMany(
            mappedBy = "doctor",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<chat> chatList = new ArrayList<>();

    public doctor() {
    }

    public doctor(String email,
                  String firstName,
                  String lastName,
                  String middleName,
                  String gender,
                  int age,
                  String degree,
                  String specialisation,
                  int experience,
                  String address,
                  String contact,
                  String imageUrl,
                  int rating,
                  int patientLimit,
                  int patientCount,
                  String registrationNumber,
                  String registrationStamp) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.gender = gender;
        this.age = age;
        this.degree = degree;
        this.specialisation = specialisation;
        this.experience = experience;
        this.address = address;
        this.contact = contact;
        this.imageUrl = imageUrl;
        this.rating = rating;
        this.patientLimit = patientLimit;
        this.patientCount = patientCount;
        this.registrationNumber = registrationNumber;
        this.registrationStamp = registrationStamp;
    }

    public doctor(String email,
                  String firstName,
                  String lastName,
                  String middleName,
                  String gender,
                  int age,
                  String degree,
                  String specialisation,
                  int experience,
                  String address,
                  String contact,
                  String imageUrl,
                  int rating,
                  int patientLimit,
                  int patientCount,
                  String registrationNumber,
                  String registrationStamp,
                  List<user> userList,
                  List<chat> chatList) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.gender = gender;
        this.age = age;
        this.degree = degree;
        this.specialisation = specialisation;
        this.experience = experience;
        this.address = address;
        this.contact = contact;
        this.imageUrl = imageUrl;
        this.rating = rating;
        this.patientLimit = patientLimit;
        this.patientCount = patientCount;
        this.registrationNumber = registrationNumber;
        this.registrationStamp = registrationStamp;
        this.userList = userList;
        this.chatList = chatList;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getPatientLimit() {
        return patientLimit;
    }

    public void setPatientLimit(int patientLimit) {
        this.patientLimit = patientLimit;
    }

    public int getPatientCount() {
        return patientCount;
    }

    public void setPatientCount(int patientCount) {
        this.patientCount = patientCount;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRegistrationStamp() {
        return registrationStamp;
    }

    public void setRegistrationStamp(String registrationStamp) {
        this.registrationStamp = registrationStamp;
    }

    public List<user> getUserList() {
        return userList;
    }

    public void setUserList(List<user> userList) {
        this.userList = userList;
    }

    public List<chat> getChatList() {
        return chatList;
    }

    public void setChatList(List<chat> chatList) {
        this.chatList = chatList;
    }

    @Override
    public String toString() {
        return "doctor{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", degree='" + degree + '\'' +
                ", specialisation='" + specialisation + '\'' +
                ", experience=" + experience +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", rating=" + rating +
                ", patientLimit=" + patientLimit +
                ", patientCount=" + patientCount +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", registrationStamp='" + registrationStamp + '\'' +
                ", userList=" + userList +
                ", chatList=" + chatList +
                '}';
    }
}