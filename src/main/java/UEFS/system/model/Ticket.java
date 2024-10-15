package main.java.UEFS.system.model;

import java.util.Objects;
import java.util.UUID;

/**
 * Represents a ticket for an event in the system.
 * Each ticket is associated with an event, has a unique identifier, a status, price, and code.
 */
public class Ticket {
    private final UUID id;
    private final UUID eventID;
    private Boolean status;
    private final Double price;
    private final String code;

    /**
     * Constructs a new Ticket with the specified event, price, and code.
     * The ticket status is set to active by default.
     *
     * @param event the event associated with the ticket
     * @param price the price of the ticket
     * @param code  the unique code of the ticket
     */
    public Ticket(Event event, Double price, String code) {
        this.id = UUID.randomUUID();
        this.eventID = event.getId();
        this.status = true;
        this.price = price;
        this.code = code;
    }

    /**
     * Constructs a new Ticket with the specified event and code.
     * The ticket price is set to 0.0 and the status is set to active by default.
     *
     * @param event the event associated with the ticket
     * @param code  the unique code of the ticket
     */
    public Ticket(Event event, String code) {
        this.id = UUID.randomUUID();
        this.eventID = event.getId();
        this.status = true;
        this.price = 0.0;
        this.code = code;
    }

    /**
     * Gets the unique identifier of the ticket.
     *
     * @return the UUID of the ticket
     */
    public UUID getId() {
        return id;
    }

    /**
     * Gets the event associated with the ticket.
     *
     * @return the event associated with the ticket
     */
    public UUID getEventId() {
        return this.eventID;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * Gets the price of the ticket.
     *
     * @return the price of the ticket
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Gets the code of the ticket.
     *
     * @return the unique code of the ticket
     */
    public String getCode() {
        return code;
    }

    /**
     * Checks if the ticket is active.
     *
     * @return true if the ticket is active, false otherwise
     */
    public Boolean isActive() {
        return status;
    }

    /**
     * Checks if this ticket is equal to another object.
     * Two tickets are considered equal if they have the same code and event ID.
     *
     * @param o the object to compare with
     * @return true if the tickets are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(code, ticket.code) &&
                Objects.equals(eventID, ticket.eventID);
    }

    /**
     * Generates a hash code for the ticket.
     *
     * @return the hash code of the ticket
     */
    @Override
    public int hashCode() {
        return Objects.hash(eventID, code);
    }
}
