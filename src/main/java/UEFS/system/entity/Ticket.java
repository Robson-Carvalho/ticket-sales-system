package main.java.UEFS.system.entity;

import com.google.gson.annotations.Expose;
import main.java.UEFS.system.service.EventService;

import java.util.Objects;
import java.util.UUID;

public class Ticket {
    @Expose
    private UUID id;
    @Expose
    private UUID eventID;
    @Expose
    private Boolean status;
    @Expose
    private Double price;
    @Expose
    private String code;

    @Expose(serialize = false)
    private EventService eventService = new EventService();

    public Ticket(Event event, Double price, String code) {
        this.id = UUID.randomUUID();
        this.eventID = event.getId();
        this.status = true;
        this.price = price;
        this.code = code;
    }

    public Ticket(Event event, String code) {
        this.id = UUID.randomUUID();
        this.eventID = event.getId();
        this.status = true;
        this.price = 0.0;
        this.code = code;
    }

    public UUID getId() {
        return id;
    }

    public Event getEvent() {
        return eventService.getById(eventID);
    }

    public Double getPrice() {
        return price;
    }

    public String getCode() {
        return code;
    }

    public Boolean isActive() {
        return status;
    }

    public void reactive() {
        Event event = eventService.getById(eventID);
        if(event.isActive()){
            this.status = true;
        }
    }

    public Boolean cancel() {
        Event event = eventService.getById(eventID);
        if(event.isActive()){
            status = false;
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(code, ticket.code) &&
                Objects.equals(eventID, ticket.eventID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventID, code);
    }

}
