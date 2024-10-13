package main.java.UEFS.system.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Represents an event in the system.
 * Each event has a unique identifier, name, description, date, and a list of available seats.
 */
public class Event {
    private final UUID id;
    private final String name;
    private final String description;
    private final Date date;
    private final List<String> seats = new ArrayList<>();

    /**
     * Constructs a new Event object with the specified name, description, and date.
     * A unique identifier is automatically generated for the event.
     *
     * @param name        the name of the event
     * @param description the description of the event
     * @param date        the date of the event
     */
    public Event(String name, String description, Date date) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.date = date;
    }

    /**
     * Gets the unique identifier of the event.
     *
     * @return the UUID of the event
     */
    public UUID getId() {
        return id;
    }

    /**
     * Gets the name of the event.
     *
     * @return the name of the event
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the description of the event.
     *
     * @return the description of the event
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the date of the event.
     *
     * @return the date of the event
     */
    public Date getDate() {
        return date;
    }

    /**
     * Gets the list of seats for the event.
     *
     * @return a list of seat identifiers
     */
    public List<String> getSeats() {
        return seats;
    }

    /**
     * Adds a seat to the event's list of available seats.
     *
     * @param seat the identifier of the seat to add
     */
    public void addSeat(String seat) {
        seats.add(seat);
    }

    /**
     * Removes a seat from the event's list of available seats.
     *
     * @param seat the identifier of the seat to remove
     */
    public void removeSeat(String seat) {
        seats.remove(seat);
    }

    /**
     * Checks if the event is still active.
     * An event is considered active if the current date is before the event's date.
     *
     * @return true if the event is active, false otherwise
     */
    public Boolean isActive() {
        Date today = new Date();
        return today.before(this.date);
    }
}
