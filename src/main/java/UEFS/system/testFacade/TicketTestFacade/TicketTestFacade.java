package main.java.UEFS.system.testFacade.TicketTestFacade;

import main.java.UEFS.system.controller.EventController;
import main.java.UEFS.system.controller.TicketController;
import main.java.UEFS.system.model.Event;
import main.java.UEFS.system.model.Ticket;

import java.util.UUID;

public class TicketTestFacade {
    private final TicketController ticketController;
    private final EventController eventController;

    public TicketTestFacade(){
        this.ticketController = new TicketController();
        this.eventController = new EventController();
    }

    public String create(String eventId, Double price, String seat){
        Event event = eventController.getById(UUID.fromString(eventId));
        Ticket ticket = ticketController.create(event, price, seat);
        return ticket.getId().toString();
    }

    public String getEventByTicketId(String id){
        Ticket ticket = ticketController.getById(UUID.fromString(id));
        return ticket.getEventId().toString();
    }

    public Double getPriceByTicketId(String id){
        Ticket ticket = ticketController.getById(UUID.fromString(id));
        return ticket.getPrice();
    }

    public void cancelByTicketId(String id){
        ticketController.cancelById(UUID.fromString(id));
    }

    public String getSeatByTicketId(String id){
        Ticket ticket = ticketController.getById(UUID.fromString(id));
        return ticket.getCode();
    }

    public void reactiveById(String id){
        ticketController.reactiveById(UUID.fromString(id));
    }

    public boolean getIsAdminTicketId(String id){
        Ticket ticket = ticketController.getById(UUID.fromString(id));
        return ticket.isActive();
    }

    public Ticket getById(String id){
        return ticketController.getById(UUID.fromString(id));
    }

    public boolean getIsActive(String id){
        Ticket ticket = ticketController.getById(UUID.fromString(id));
        return ticket.isActive();
    }

    public void deleteAllTickets(){
        ticketController.deleteAll();
    }
}
