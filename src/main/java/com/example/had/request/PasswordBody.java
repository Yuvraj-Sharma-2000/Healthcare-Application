package com.example.had.request;

public class PasswordBody {
    private String email;
    private String newPassword;
    private String oldPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getEmail() {
        return email;
    }
}
