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
import main.java.UEFS.system.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Controller {
    private final UserController userController;
    private final EventController eventController;
    private final TicketController ticketController;
    private final CardController cardController;
    private final TransactionController transactionController;
    private final MailController mailBoxController;
    private final CommentController commentController;

    public Controller() {
        this.userController = new UserController();
        this.eventController = new EventController();
        this.ticketController = new TicketController();
        this.cardController = new CardController();
        this.transactionController = new TransactionController();
        this.mailBoxController = new MailController();
        this.commentController = new CommentController();
    }

    private Event getEventByName(String eventName){
        List<Event> events = eventController.getAll();

        for(Event event : events){
            if(event.getName().equals(eventName)){
                return event;
            }
        }

        return null;
    }

    public Event getEventById(UUID eventId){
        return eventController.getById(eventId);
    }

    public User createUser(String login, String password, String name, String cpf, String email, Boolean isAdmin) throws Exception {
        return userController.create(login, password, name, cpf, email, isAdmin);
    }

    public Event createEvent(User admin, String name, String description, Date date){
        if(admin.isAdmin()){
            return eventController.create(admin, name, description, date);
        }

        throw new SecurityException("Somente administradores podem cadastrar eventos.");
    }


    public void addSeatToEvent(String eventName, String seat){
        Event event = getEventByName(eventName);

        if(event != null){
            event.addSeat(seat);
            eventController.update(event);
        }
    }

    public Ticket buyTicket(User user, String eventName, String seat) throws Exception {
        if(userController.getById(user.getId()) == null){
            return null;
        }

        Event event = getEventByName(eventName);

        if (event != null){
            Ticket ticket = ticketController.create(event.getId(), seat);
            user.addTicket(ticket);
            userController.update(user);
            return ticket;
        }

        return  null;
    }

    public Boolean cancelBuy(User _user, Ticket _ticket) throws Exception {
        User user = userController.getById(_user.getId());
        Ticket ticket = ticketController.getById(_ticket.getId());

        if(user != null && ticket != null){
            _user.removeTicket(_ticket);
            userController.update(_user);
            ticketController.cancelById(_ticket.getId());
            ticketController.update(_ticket);
            return true;
        }

        return false;
    }

    public List<Event> listAvailableEvents(){
        return eventController.getAll();
    }

    public List<Ticket> listPurchasedTickets(User user){
        List<Ticket> tickets = new ArrayList<Ticket>();

        User _user = userController.getById(user.getId());
        List<UUID> ticketIDs = _user.getTickets();

        for(UUID id: ticketIDs){
            Ticket t = ticketController.getById(id);
            tickets.add(t);
        }

        return tickets;
    }

    public void deleteDB(){
        userController.deleteAll();
        eventController.deleteAll();
        ticketController.deleteAll();
        mailBoxController.deleteAll();
        transactionController.deleteAll();
        cardController.deleteAll();
        commentController.deleteAll();
    }
}