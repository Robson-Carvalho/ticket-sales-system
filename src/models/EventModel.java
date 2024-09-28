package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class EventModel {
    private UUID id;
    private String name;
    private String description;
    private Date date;
    private Boolean status;
    private List<String> seats;

    public EventModel(String name, String description, Date date) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.date = date;
        this.status = true;
        this.seats = new ArrayList<>();
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

    public void addSeat(String seat) {
        seats.add(seat);
    }

    public void removeSeat(String seat) {
        seats.remove(seat);
    }

    public Boolean isActive(){
        Date today = new Date();
        return today.before(this.date);
    }

    public void cancel(){
        if(this.isActive()){
            this.status = false;
        }
    }
}
