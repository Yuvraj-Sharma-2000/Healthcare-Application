package com.example.had.entity;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.UUID;

@Entity(name = "Chat")
@Table(
        name = "chat_master"
)
public class chat {
    @Id
    @SequenceGenerator(
            name = "chat_sequence",
            sequenceName = "chat_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = UUID,
            generator = "chat_sequence"
    )
    @Column(
            name = "chat_id",
            length = 12
    )
    private String id;


    @Column(
            name = "chat_message",
            updatable = false
    )
    private String chatMessage;


    @Column(
            name = "chat_sent_stamp",
            nullable = false
    )
    private String chatSentStamp;

    @ManyToOne
    @JoinColumn(
            name = "doctor_id",
            nullable = false,
            referencedColumnName = "doctor_id",
            foreignKey = @ForeignKey(
                    name = "doctor_chat_fk"
            )
    )
    private doctor doctor;


    @ManyToOne
    @JoinColumn(
            name = "patient_id",
            nullable = false,
            referencedColumnName = "user_id",
            foreignKey = @ForeignKey(
                    name = "user_chat_fk"
            )
    )
    private user user;

    public chat() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChatMessage() {
        return chatMessage;
    }

    public void setChatMessage(String chatMessage) {
        this.chatMessage = chatMessage;
    }

    public String getChatSentStamp() {
        return chatSentStamp;
    }

    public void setChatSentStamp(String chatSentStamp) {
        this.chatSentStamp = chatSentStamp;
    }

    public com.example.had.entity.doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(com.example.had.entity.doctor doctor) {
        this.doctor = doctor;
    }

    public com.example.had.entity.user getUser() {
        return user;
    }

    public void setUser(com.example.had.entity.user user) {
        this.user = user;
    }

    public chat(String chatMessage, String chatSentStamp) {
        this.chatMessage = chatMessage;
        this.chatSentStamp = chatSentStamp;
    }

    public chat(String chatMessage, String chatSentStamp, doctor doctor, user user) {
        this.chatMessage = chatMessage;
        this.chatSentStamp = chatSentStamp;
        this.doctor = doctor;
        this.user = user;
    }

    @Override
    public String toString() {
        return "chat{" +
                "id='" + id + '\'' +
                ", chatMessage='" + chatMessage + '\'' +
                ", chatSentStamp='" + chatSentStamp + '\'' +
                ", doctor=" + doctor +
                ", user=" + user +
                '}';
    }
}