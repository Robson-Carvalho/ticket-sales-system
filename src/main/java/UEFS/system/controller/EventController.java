package main.java.UEFS.system.controller;

import main.java.UEFS.system.entity.Event;
import main.java.UEFS.system.entity.User;
import main.java.UEFS.system.service.EventService;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class EventController {
    private final EventService eventService;

    private final UserController userController;

    public EventController() {
        this.eventService = new EventService();
        this.userController = new UserController();
    }

    public Event create(User admin, String name, String description, Date date){
        Event event = new Event(name, description, date);

        User _admin = userController.getById(admin.getId());

        if(_admin == null || !_admin.isAdmin()) {
            throw new SecurityException("Somente administradores podem cadastrar eventos.");
        }

        eventService.create(event);
        return event;
    }

    public Event getById(UUID id){
        return eventService.getById(id);
    }

    public List<Event> getAll(){
        return eventService.getAll();
    }

    public void update(Event event) {
        eventService.update(event);
    }

    public void delete(UUID id) {
        eventService.delete(id);
    }

    public void deleteAll() {
        eventService.deleteAll();
    }
}
