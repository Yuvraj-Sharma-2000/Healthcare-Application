package com.example.had.response;

import java.util.List;
import java.util.UUID;

public class WeekQuestions {
    private UUID session_id;
    private int session_number;
    private boolean isSessionCompleted;
    private String session_image_url;
    private String session_title;
    private List<SessionQuestion> sessions;

    public WeekQuestions(UUID session_id,
                         int session_number,
                         boolean isSessionCompleted,
                         String session_image_url,
                         String session_title,
                         List<SessionQuestion> sessions) {
        this.session_id = session_id;
        this.session_number = session_number;
        this.isSessionCompleted = isSessionCompleted;
        this.session_image_url = session_image_url;
        this.session_title = session_title;
        this.sessions = sessions;
    }

    public UUID getSession_id() {
        return session_id;
    }

    public int getSession_number() {
        return session_number;
    }

    public boolean isSessionCompleted() {
        return isSessionCompleted;
    }

    public String getSession_image_url() {
        return session_image_url;
    }

    public String getSession_title() {
        return session_title;
    }

    public List<SessionQuestion> getSessions() {
        return sessions;
    }

    public void setSessions(List<SessionQuestion> sessions) {
        this.sessions = sessions;
    }
}
