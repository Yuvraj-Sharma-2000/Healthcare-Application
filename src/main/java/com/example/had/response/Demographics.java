package com.example.had.response;

public class Demographics {
    private int assignedPatients;
    private int notAssignedPatients;
    private int verifiedDoctors;
    private int unverifiedDoctors;

    public Demographics(int assignedPatients, int notAssignedPatients, int verifiedDoctors, int unverifiedDoctors) {
        this.assignedPatients = assignedPatients;
        this.notAssignedPatients = notAssignedPatients;
        this.verifiedDoctors = verifiedDoctors;
        this.unverifiedDoctors = unverifiedDoctors;
    }

    public int getAssignedPatients() {
        return assignedPatients;
    }

    public int getNotAssignedPatients() {
        return notAssignedPatients;
    }

    public int getVerifiedDoctors() {
        return verifiedDoctors;
    }

    public int getUnverifiedDoctors() {
        return unverifiedDoctors;
    }
}
