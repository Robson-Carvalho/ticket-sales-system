package models;

import java.util.Objects;
import java.util.UUID;

public class TicketModel {
    private UUID id;
    private EventModel event;
    private Boolean status;
    private Double price;
    private String code;

    public TicketModel(EventModel event, Double price, String code) {
        this.id = UUID.randomUUID();
        this.event = event;
        this.status = true;
        this.price = price;
        this.code = code;
    }

    public TicketModel(EventModel event, String code) {
        this.id = UUID.randomUUID();
        this.event = event;
        this.status = true;
        this.price = 0.0;
        this.code = code;
    }

    public UUID getId() {
        return id;
    }

    public EventModel getEvent() {
        return event;
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
        if(this.event.isActive()){
            this.status = true;
        }
    }

    public Boolean cancel() {
        if(this.event.isActive()){
            this.status = false;
            return true;
        }
        return false;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketModel ticket = (TicketModel) o;
        return Objects.equals(code, ticket.code) &&
                Objects.equals(event, ticket.event);
    }

    @Override
    public int hashCode() {
        return Objects.hash(event, code);
    }

}
