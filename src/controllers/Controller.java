package controllers;

import models.EventModel;
import models.TicketModel;
import models.UserModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Controller {
    private List<EventModel> events = new ArrayList<EventModel>();

    public UserModel createUser(String login, String password, String name, String cpf, String email, Boolean isAdmin) {
        return new UserModel(login, password, name, cpf, email, isAdmin);
    }

    public EventModel createEvent(UserModel admin, String name, String description, Date date) {
        if(admin.isAdmin()){
            EventModel event = new EventModel(name, description, date);
            events.add(event);
            return event;
        }
        throw new SecurityException("Somente administradores podem cadastrar eventos.");
    }

    public void addSeatToEvent(String nameEvent, String seat) {
        for(EventModel event : events){
            if(event.getName().equals(nameEvent)){
                event.addSeat(seat);
            }
        }
    }

    public TicketModel buyTicket(UserModel user, String nameEvent, String seat) {
        for (EventModel event : events) {
            if (event.getName().equals(nameEvent)) {
                if (event.getSeats().contains(seat)) {
                    TicketModel ticket = new TicketModel(event, seat);
                    user.addTicket(ticket);
                    return ticket;
                }
            }
        }
        return null;
    }

    public boolean cancelBuy(UserModel user, TicketModel ticket) {
        List<TicketModel> tickets = user.getTickets();

        for (TicketModel ticketI : tickets) {
            if (ticketI.equals(ticket)) {
                boolean eventIsActive = ticketI.getEvent().isActive();

                if(eventIsActive){
                    user.removeTicket(ticket);
                    return ticket.cancel();
                }
            }
        }
        return false;
    }

    public List<EventModel> listAvailableEvents(){
        List<EventModel> availableEvents = new ArrayList<EventModel>();

        for(EventModel event : events){
            if(event.isActive()){
                availableEvents.add(event);
            }
        }

        return availableEvents;
    }

    public List<TicketModel> listPurchasedTickets(UserModel user){
        return user.getTickets();
    }
}
