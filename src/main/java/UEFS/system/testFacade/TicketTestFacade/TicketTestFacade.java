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
        Ticket ticket = ticketController.create(UUID.fromString(eventId), price, seat);
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
