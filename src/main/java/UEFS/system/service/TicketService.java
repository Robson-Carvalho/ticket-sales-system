/***************************
 * Autor: Robson Carvalho de Souza
 * Componente Curricular: MI de Programação
 * Concluído em: 16/09/2024
 * Declaro que este código foi elaborado por mim de forma individual e não contém nenhum
 * trecho de código de outro colega ou de outro autor, tais como provindos de livros e
 * apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
 * de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
 * do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
 ******************************/

package main.java.UEFS.system.service;

import main.java.UEFS.system.model.Event;
import main.java.UEFS.system.model.Ticket;
import main.java.UEFS.system.repository.EventRepository;
import main.java.UEFS.system.repository.TicketRepository;
import main.java.UEFS.system.interfaces.IService;

import java.util.List;
import java.util.UUID;

public class TicketService implements IService<Ticket> {
    private final TicketRepository ticketRepository;
    private final EventRepository eventRepository;

    public TicketService() {
        this.ticketRepository = new TicketRepository();
        this.eventRepository = new EventRepository();
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

    public boolean cancelById(UUID id) {
        Ticket ticket = this.getById(id);
        Event event = this.eventRepository.findById(ticket.getEventId());

        if (event.isActive()) {
            ticket.setStatus(false);
            this.update(ticket);
            return true;
        }
        return false;
    }
    public void reactiveById(UUID id) {
        Ticket ticket = this.getById(id);
        Event event = this.eventRepository.findById(ticket.getEventId());
        if (event.isActive()) {
            ticket.setStatus(true);
            this.update(ticket);
        }
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
