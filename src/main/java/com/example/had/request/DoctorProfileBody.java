package com.example.had.request;

import java.util.UUID;

public class DoctorProfileBody {
    private UUID doctorID;

    private String address;

    private String contact;
    private String degree;
    private int patientLimit;

    private String specialization;

    public String getAddress() {
        return address;
    }
    public UUID getDoctorID() {
        return doctorID;
    }


    public String getContact() {
        return contact;
    }

    public int getPatientLimit() {
        return patientLimit;
    }

    public String getDegree() {
        return degree;
    }

    public String getSpecialization() {
        return specialization;
    }
}
