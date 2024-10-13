package main.java.UEFS.system.model;

import java.util.UUID;

/**
 * Represents an email message in the system.
 * Each email has a unique identifier, is associated with a user, and contains a subject and content.
 */
public class Mail {
    private final UUID id;
    private final UUID userID;
    private final String subject;
    private final String content;

    /**
     * Constructs a new Mail object with the specified user ID, subject, and content.
     * A unique identifier is automatically generated for the email.
     *
     * @param userID  the UUID of the user associated with the email
     * @param subject the subject of the email
     * @param content the content of the email
     */
    public Mail(UUID userID, String subject, String content) {
        this.id = UUID.randomUUID();
        this.userID = userID;
        this.subject = subject;
        this.content = content;
    }

    /**
     * Gets the unique identifier of the email.
     *
     * @return the UUID of the email
     */
    public UUID getId() {
        return id;
    }

    /**
     * Gets the user ID associated with the email.
     *
     * @return the UUID of the user associated with the email
     */
    public UUID getUserID() {
        return userID;
    }

    /**
     * Gets the subject of the email.
     *
     * @return the subject of the email
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Gets the content of the email.
     *
     * @return the content of the email
     */
    public String getContent() {
        return "In progress";
    }
}
