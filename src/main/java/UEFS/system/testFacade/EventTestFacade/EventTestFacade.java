package main.java.UEFS.system.testFacade.EventTestFacade;

import main.java.UEFS.system.controller.EventController;
import main.java.UEFS.system.controller.UserController;
import main.java.UEFS.system.model.Event;
import main.java.UEFS.system.model.User;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class EventTestFacade {
    private final EventController eventController;
    private final UserController userController ;

    public EventTestFacade() {
        eventController = new EventController();
        userController = new UserController();
    }

    public void removeSeatByEventId(String seat, String id){
        Event event = eventController.getById(UUID.fromString(id));
        event.removeSeat(seat);
        eventController.update(event);
    }

    public void addSeatByEventId(String seat, String id){
        Event event = eventController.getById(UUID.fromString(id));
        event.addSeat(seat);
        eventController.update(event);
    }

    public String create(String loginAdmin, String name, String description, Date date){
        User user = userController.getByLogin(loginAdmin);
        Event event = eventController.create(user, name, description, date);
        return event.getId().toString();
    }

    public Event getById(String id){
        return eventController.getById(UUID.fromString(id));
    }

    public String getNameByEventId(String id){
        Event event = eventController.getById(UUID.fromString(id));
        return event.getName();
    }

    public List<String> getSeatsByEventId(String id){
        return eventController.getById(UUID.fromString(id)).getSeats();
    }

    public String getDescriptionByEventId(String id){
        Event event = eventController.getById(UUID.fromString(id));
        return event.getDescription();
    }

    public int getYearByEventId(String id){
        Event event = eventController.getById(UUID.fromString(id));
        return event.getDate().getYear();
    }

    public int getMonthByEventId(String id){
        Event event = eventController.getById(UUID.fromString(id));
        return event.getDate().getMonth();
    }

    public int getDayByEventId(String id){
        Event event = eventController.getById(UUID.fromString(id));
        return event.getDate().getDay();
    }

    public boolean getIsActiveByEventId(String id){
        return eventController.getById(UUID.fromString(id)).isActive();
    }

    public void deleteAllEvents(){
        eventController.deleteAll();
    }
}
