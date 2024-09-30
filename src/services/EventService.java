package services;

import entitys.Event;
import repositories.EventRepository;

import java.util.List;
import java.util.UUID;

public class EventService {
    private final EventRepository eventRepository;

    public EventService() {
        this.eventRepository = new EventRepository();
    }

    public Event getById(UUID id) {
        return eventRepository.findById(id);
    }

    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    public void create(Event event) {
        eventRepository.save(event);
    }

    public void update(Event event) {
        eventRepository.update(event);
    }

    public void delete(UUID id) {
        eventRepository.delete(id);
    }

    public void deleteAll() {
        eventRepository.deleteAll();
    }
}
