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

package main.java.UEFS.system.controller;

import main.java.UEFS.system.model.Event;
import main.java.UEFS.system.model.Ticket;
import main.java.UEFS.system.service.EventService;
import main.java.UEFS.system.service.TicketService;

import java.util.List;
import java.util.UUID;

public class TicketController {
    private final TicketService ticketService;
    private final EventService eventService;

    public TicketController() {
        this.ticketService = new TicketService();
        this.eventService = new EventService();
    }

    public Ticket create(UUID eventId, String seat){
        Ticket ticket = new Ticket(eventId, seat);
        ticketService.create(ticket);
        return ticket;
    }

    public Ticket create(UUID eventId, Double price, String seat){
        Ticket ticket = new Ticket(eventId, price, seat);
        ticketService.create(ticket);

        Event _event = eventService.getById(eventId);

        if(_event.getSeats().contains(seat)){
            throw new IllegalArgumentException("Não é possível cadastrar o mesmo assento duas vezes para um único evento.");
        }

        _event.addSeat(seat);

        eventService.update(_event);

        return ticket;
    }

    public Ticket getById(UUID id){
        return this.ticketService.getById(id);
    }

    public Boolean cancelById(UUID id) {
       return ticketService.cancelById(id);
    }

    public void reactiveById(UUID id) {
        ticketService.reactiveById(id);
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
