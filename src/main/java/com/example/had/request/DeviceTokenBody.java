package com.example.had.request;

import java.util.UUID;

public class DeviceTokenBody {
    private UUID patientId;
    private String token;

    public UUID getPatientId() {
        return patientId;
    }

    public void setPatientId(UUID patientId) {
        this.patientId = patientId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
