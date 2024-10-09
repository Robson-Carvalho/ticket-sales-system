package main.java.UEFS.system.model;

import java.util.UUID;

public class Comment {
    private final UUID id;
    private final UUID userID;
    private final UUID eventID;
    private int rating;
    private String content;

    public Comment(UUID userID, UUID eventID, int rating, String content) {
        this.id = UUID.randomUUID();
        this.userID = userID;
        this.eventID = eventID;
        this.rating = rating;
        this.content = content;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserID() {
        return this.userID;
    }

    public UUID getEventID() {
        return eventID;
    }

    public int getRating() {
        return rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
