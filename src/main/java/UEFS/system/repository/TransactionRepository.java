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
import main.java.UEFS.system.model.Transaction;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TransactionRepository implements IRepository<Transaction> {
    private static final String FILE_PATH = "src/main/java/UEFS/system/storage/transactions.json";
    private final List<Transaction> transactions;

    public TransactionRepository() {
        transactions = loadTransactions();
    }

    private List<Transaction> loadTransactions() {
        List<Transaction> eventList = new ArrayList<>();
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Transaction>>() {}.getType();
            eventList =  new Gson().fromJson(reader, listType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return eventList != null ? eventList : new ArrayList<>();
    }

    private void saveTransactions() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            gson.toJson(transactions, writer);
        } catch (Exception e) {
            System.out.println("Error while saving transactions");
        }
    }

    @Override
    public Transaction findById(UUID id) {
        return loadTransactions().stream().filter(event -> event.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Transaction> findAll() {
        return loadTransactions();
    }

    @Override
    public void save(Transaction transaction) {
        transactions.add(transaction);
        saveTransactions();
    }

    @Override
    public void update(Transaction transaction) {
        delete(transaction.getId());
        transactions.add(transaction);
        saveTransactions();
    }

    @Override
    public void delete(UUID id) {
        transactions.removeIf(event -> event.getId().equals(id));
        saveTransactions();
    }

    @Override
    public void deleteAll() {
        transactions.clear();
        saveTransactions();
    }
}
