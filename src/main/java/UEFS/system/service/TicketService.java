package main.java.UEFS.system.service;

import main.java.UEFS.system.model.Ticket;
import main.java.UEFS.system.repository.TicketRepository;
import main.java.UEFS.system.interfaces.IService;

import java.util.List;
import java.util.UUID;

public class TicketService implements IService<Ticket> {
    private final TicketRepository ticketRepository;

    public TicketService() {
        this.ticketRepository = new TicketRepository();
    }

    @Override
    public Ticket create(Ticket ticket) {
        ticketRepository.save(ticket);
        return ticket;
    }

    @Override
    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket getById(UUID id) {
        return ticketRepository.findById(id);
    }

    @Override
    public void update(Ticket ticket) {
        ticketRepository.update(ticket);
    }

    @Override
    public void delete(UUID id) {
        ticketRepository.delete(id);
    }

    @Override
    public void deleteAll() {
        ticketRepository.deleteAll();
    }
}
