package main.java.UEFS.system.service;

import main.java.UEFS.system.model.Event;
import main.java.UEFS.system.repository.EventRepository;
import main.java.UEFS.system.interfaces.IService;

import java.util.List;
import java.util.UUID;

public class EventService implements IService<Event> {
    private final EventRepository eventRepository;

    public EventService() {
        this.eventRepository = new EventRepository();
    }

    @Override
    public Event create(Event event) {
        eventRepository.save(event); return event;
    }

    @Override
    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    @Override
    public Event getById(UUID id) {return eventRepository.findById(id);}

    @Override
    public void update(Event event) {
        eventRepository.update(event);
    }

    @Override
    public void delete(UUID id) {
        eventRepository.delete(id);
    }

    @Override
    public void deleteAll() {
        eventRepository.deleteAll();
    }
}
