package com.example.had.security;

public enum UserPermission {
    ARTICLE_WRITE("article:write"),
    ARTICLE_READ("article:read"),
    CHAT_WRITE("chat:write"),
    CHAT_READ("chat:read"),
    DOCTOR_WRITE("doctor:write"),
    DOCTOR_READ("doctor:read"),
    QUESTION_WRITE("question:write"),
    QUESTION_READ("question:read"),
    REPORT_WRITE("report:write"),
    REPORT_READ("report:read"),
    USER_WRITE("user:write"),
    USER_READ("user:read");


    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
