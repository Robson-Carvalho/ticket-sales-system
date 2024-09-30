package main.java.UEFS.system.services;

import main.java.UEFS.system.entitys.Ticket;
import main.java.UEFS.system.repositories.TicketRepository;

import java.util.List;
import java.util.UUID;

public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService() {
        this.ticketRepository = new TicketRepository();
    }

    public Ticket getById(UUID id) {
        return ticketRepository.findById(id);
    }

    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    public void create(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    public void update(Ticket ticket) {
        ticketRepository.update(ticket);
    }

    public void delete(UUID id) {
        ticketRepository.delete(id);
    }

    public void deleteAll() {
        ticketRepository.deleteAll();
    }
}
