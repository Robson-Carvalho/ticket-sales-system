package controllers;

import entitys.Event;
import entitys.Ticket;
import entitys.User;

import java.util.Date;
import java.util.List;

public class Controller {
    private final UserController userController;
    private final EventController eventController;
    private final TicketController ticketController;

    public Controller() {
        this.userController = new UserController();
        this.eventController = new EventController();
        this.ticketController = new TicketController();
    }

    private Event getEventByName(String eventName){
        List<Event> events = eventController.getAll();

        for(Event event : events){
            if(event.getName().equals(eventName)){
                return  event;
            }
        }

        return null;
    }

    public User createUser(String login, String password, String name, String cpf, String email, Boolean isAdmin){
        return userController.create(login, password, name, cpf, email, isAdmin);
    }

    public Event createEvent(User admin, String name, String description, Date date){
        if(admin.isAdmin()){
            return eventController.create(name, description, date);
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

    public Ticket buyTicket(User _user, String eventName, String seat) {
        User user = userController.getById(_user.getId());
        Event event = getEventByName(eventName);

        if (event != null){
            Ticket ticket = ticketController.create(event, seat);
            user.addTicket(ticket);
            userController.update(user);
            return ticket;
        }

        return  null;
    }
}