package com.example.had.request;

import java.util.UUID;

public class doctorConnectionRequest {
    private UUID doctorId;
    private UUID userId;

    public UUID getDoctorId() {
        return doctorId;
    }

    public UUID getUserId() {
        return userId;
    }
}
