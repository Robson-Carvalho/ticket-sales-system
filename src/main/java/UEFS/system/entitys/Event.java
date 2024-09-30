package main.java.UEFS.system.entitys;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Event {
    private final UUID id;
    private final String name;
    private final String description;
    private final Date date;
    private final List<String> seats = new ArrayList<>();

    public Event(String name, String description, Date date) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.date = date;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public List<String> getSeats() {
        return seats;
    }

    public void addSeat(String seat) {seats.add(seat);}

    public void removeSeat(String seat) {
        seats.remove(seat);
    }

    public Boolean isActive(){
        Date today = new Date();
        return today.before(this.date);
    }
}
