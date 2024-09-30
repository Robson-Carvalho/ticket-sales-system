package repositories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entitys.Event;
import entitys.User;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EventRepository implements IRepository<Event> {
    private static final String FILE_PATH = "src/storage/events.json";
    private final List<Event> events;

    public EventRepository() {
        events = loadEvents();
    }

    private List<Event> loadEvents() {
        List<Event> eventList = new ArrayList<>(); // Inicializa a lista
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Event>>() {}.getType();
            eventList =  new Gson().fromJson(reader, listType);
        } catch (Exception e) {
            return new ArrayList<>();
        }
        return eventList != null ? eventList : new ArrayList<>();
    }

    private void saveEvents() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting() // Ativa a formatação "bonita"
                    .create();
            gson.toJson(events, writer);
        } catch (Exception e) {
            System.out.println("Error while saving events");
        }
    }

    @Override
    public Event findById(UUID id) {
        return events.stream().filter(event -> event.getId().equals(id)).findFirst().orElse(null);
    }


    @Override
    public List<Event> findAll() {
        return events;
    }

    @Override
    public void save(Event event) {
        events.add(event);
        saveEvents();
    }

    @Override
    public void update(Event event) {
        delete(event.getId());
        events.add(event);
        saveEvents();
    }

    @Override
    public void delete(UUID id) {
        events.removeIf(event -> event.getId().equals(id));
        saveEvents();
    }
}
