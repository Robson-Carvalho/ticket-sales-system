package main.java.UEFS.system.controllers;

import main.java.UEFS.system.entitys.Event;
import main.java.UEFS.system.entitys.Ticket;
import main.java.UEFS.system.services.TicketService;

import java.util.List;
import java.util.UUID;

public class TicketController {
    private final TicketService ticketService;

    public TicketController() {
        this.ticketService = new TicketService();
    }

    public Ticket create(Event event, String seat){
        Ticket ticket = new Ticket(event, seat);
        ticketService.create(ticket);
        return ticket;
    }

    public Ticket create(Event event, Double price, String seat){
        Ticket ticket = new Ticket(event, price, seat);
        ticketService.create(ticket);
        return ticket;
    }

    public Ticket getById(UUID id){
        return ticketService.getById(id);
    }

    public List<Ticket> getAll(){
        return ticketService.getAll();
    }

    public void update(Ticket ticket) {
        ticketService.update(ticket);
    }

    public void delete(UUID id) {
        ticketService.delete(id);
    }

    public void deleteAll(){
        ticketService.deleteAll();
    }
}
