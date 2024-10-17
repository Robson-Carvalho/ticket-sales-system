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

package main.java.UEFS.system.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import main.java.UEFS.system.interfaces.IRepository;
import main.java.UEFS.system.model.Event;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EventRepository implements IRepository<Event> {
    private static final String FILE_PATH = "src/main/java/UEFS/system/storage/events.json";
    private final List<Event> events;

    public EventRepository() {
        events = loadEvents();
    }

    private List<Event> loadEvents() {
        List<Event> eventList = new ArrayList<>();
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Event>>() {}.getType();
            eventList =  new Gson().fromJson(reader, listType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return eventList != null ? eventList : new ArrayList<>();
    }

    private void saveEvents() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            gson.toJson(events, writer);
        } catch (Exception e) {
            System.out.println("Error while saving events");
        }
    }

    @Override
    public Event findById(UUID id) {
        return loadEvents().stream().filter(event -> event.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Event> findAll() {
        return loadEvents();
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

    @Override
    public void deleteAll() {
        events.clear();
        saveEvents();
    }
}
