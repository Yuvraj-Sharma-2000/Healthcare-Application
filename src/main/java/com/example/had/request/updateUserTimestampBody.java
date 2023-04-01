package com.example.had.request;

import java.sql.Timestamp;
import java.util.UUID;

public class updateUserTimestampBody {
    private String username;
    private Timestamp entryTime;
    private Timestamp exitTime;

    public String getUsername() {
        return username;
    }

    public Timestamp getEntryTime() {
        return entryTime;
    }

    public Timestamp getExitTime() {
        return exitTime;
    }
}
