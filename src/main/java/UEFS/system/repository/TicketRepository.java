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
import main.java.UEFS.system.model.Ticket;

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
        for (Ticket ticket : tickets) {
            if(ticket.getId().equals(id)){
                return ticket;
            }
        }

        return null;
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
