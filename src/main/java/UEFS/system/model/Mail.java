package main.java.UEFS.system.model;

import java.util.UUID;

public class Mail {
    private final UUID id;
    private final UUID userID;
    private final String subject;
    private final String content;

    public Mail(UUID userID, String subject, String content) {
        this.id = UUID.randomUUID();
        this.userID = userID;
        this.subject = subject;
        this.content = content;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserID() {
        return userID;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return "In progress";
    }
}
