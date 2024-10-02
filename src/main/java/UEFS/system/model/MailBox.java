package main.java.UEFS.system.model;

import java.util.UUID;

public class MailBox {
    private final UUID id;
    private final UUID userID;
    private final UUID transactionID;
    private final String subject;

    public MailBox(UUID userID, UUID transactionID, String subject) {
        this.id = UUID.randomUUID();
        this.userID = userID;
        this.transactionID = transactionID;
        this.subject = subject;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserID() {
        return userID;
    }

    public UUID getTransactionID() {
        return transactionID;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return "In progress";
    }
}
