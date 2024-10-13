package main.java.UEFS.system.model;

import java.util.UUID;

/**
 * Represents a comment made by a user on an event.
 * Each comment includes a unique identifier, the user and event identifiers,
 * a rating, and the content of the comment.
 */
public class Comment {
    private final UUID id;
    private final UUID userID;
    private final UUID eventID;
    private int rating;
    private String content;

    /**
     * Constructs a new Comment object with the specified user ID, event ID, rating, and content.
     * A unique identifier is automatically generated for the comment.
     *
     * @param userID  the unique identifier of the user who made the comment
     * @param eventID the unique identifier of the event being commented on
     * @param rating  the rating given by the user, typically on a scale (e.g., 1-5)
     * @param content the textual content of the comment
     */
    public Comment(UUID userID, UUID eventID, int rating, String content) {
        this.id = UUID.randomUUID();
        this.userID = userID;
        this.eventID = eventID;
        this.rating = rating;
        this.content = content;
    }

    /**
     * Gets the unique identifier of the comment.
     *
     * @return the UUID of the comment
     */
    public UUID getId() {
        return id;
    }

    /**
     * Gets the unique identifier of the user who made the comment.
     *
     * @return the UUID of the user
     */
    public UUID getUserID() {
        return this.userID;
    }

    /**
     * Gets the unique identifier of the event being commented on.
     *
     * @return the UUID of the event
     */
    public UUID getEventID() {
        return eventID;
    }

    /**
     * Gets the rating given by the user.
     *
     * @return the rating value
     */
    public int getRating() {
        return rating;
    }

    /**
     * Gets the content of the comment.
     *
     * @return the textual content of the comment
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of the comment.
     *
     * @param content the new content for the comment
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Sets the rating for the comment.
     *
     * @param rating the new rating value
     */
    public void setRating(int rating) {
        this.rating = rating;
    }
}
