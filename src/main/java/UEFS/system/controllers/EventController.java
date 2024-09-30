package main.java.UEFS.system.controllers;

import main.java.UEFS.system.entitys.Event;
import main.java.UEFS.system.services.EventService;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class EventController {
    private final EventService eventService;

    public EventController() {
        this.eventService = new EventService();
    }

    public Event create(String name, String description, Date date){
        Event event = new Event(name, description, date);
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
