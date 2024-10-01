package main.java.UEFS.system.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import main.java.UEFS.system.entity.Ticket;
import main.java.UEFS.system.repository.Interface.IRepository;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TicketRepository implements IRepository<Ticket> {
    private static final String FILE_PATH = "src/main/java/UEFS/system/storage/tickets.json";
    private final List<Ticket> tickets;

    public TicketRepository() {
        tickets = loadTickets();
    }

    private List<Ticket> loadTickets() {
        List<Ticket> ticketList = new ArrayList<>();
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Ticket>>() {}.getType();
            ticketList = new Gson().fromJson(reader, listType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ticketList != null ? ticketList : new ArrayList<>();

    }

    private void saveTickets() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {

            Gson gson = new GsonBuilder()
                     .excludeFieldsWithoutExposeAnnotation()
                    .setPrettyPrinting()
                    .create();
            gson.toJson(tickets, writer);
        } catch (Exception e) {
            System.out.println("Error while saving tickets");
        }
    }

    @Override
    public Ticket findById(UUID id) {
        return loadTickets().stream().filter(ticket -> ticket.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Ticket> findAll() {
        return loadTickets();
    }

    @Override
    public void save(Ticket ticket) {
        tickets.add(ticket);
        saveTickets();
    }

    @Override
    public void update(Ticket ticket) {
        delete(ticket.getId());
        tickets.add(ticket);
        saveTickets();
    }

    @Override
    public void delete(UUID id) {
        tickets.removeIf(ticket -> ticket.getId().equals(id));
        saveTickets();
    }

    @Override
    public void deleteAll() {
        tickets.clear();
        saveTickets();
    }
}
