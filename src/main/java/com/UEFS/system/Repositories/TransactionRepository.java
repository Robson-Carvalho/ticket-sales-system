/***************************
 * Autor: Robson Carvalho de Souza
 * Componente Curricular: MI de Programação
 * Concluído em: 16/09/2024
 *
 * Declaro que este código foi elaborado por mim de forma individual e não contém nenhum
 * trecho de código de outro colega ou de outro autor, tais como provindos de livros e
 * apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
 * de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
 * do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
 ******************************/

package main.java.com.UEFS.system.Repositories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import main.java.com.UEFS.system.Interfaces.IRepository;
import main.java.com.UEFS.system.Models.Transaction;
import main.java.com.UEFS.system.PathsFile.PathsFile;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Classe repositório para a entidade Transaction, responsável por gerenciar as operações de CRUD
 * utilizando um arquivo JSON como armazenamento.
 */
public class TransactionRepository implements IRepository<Transaction> {
    private static final String FILE_PATH = PathsFile.getTransactionsJSON();
    private final List<Transaction> transactions;

    /**
     * Construtor da classe que inicializa a lista de transações carregando os dados do arquivo JSON.
     */
    public TransactionRepository() {
        transactions = loadTransactions();
    }

    /**
     * Carrega a lista de transações do arquivo JSON.
     *
     * @return Lista de transações carregada do arquivo, ou uma nova lista se ocorrer algum erro.
     */
    private List<Transaction> loadTransactions() {
        List<Transaction> transactionList = new ArrayList<>();
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Transaction>>() {}.getType();
            transactionList = new Gson().fromJson(reader, listType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactionList != null ? transactionList : new ArrayList<>();
    }

    /**
     * Salva a lista de transações no arquivo JSON.
     */
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

    /**
     * Busca uma transação pelo seu ID.
     *
     * @param id ID da transação a ser buscada.
     * @return Transação encontrada ou null se não existir.
     */
    @Override
    public Transaction findById(UUID id) {
        return loadTransactions().stream()
                .filter(transaction -> transaction.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    /**
     * Retorna todas as transações salvas.
     *
     * @return Lista de todas as transações.
     */
    @Override
    public List<Transaction> findAll() {
        return loadTransactions();
    }

    /**
     * Salva uma nova transação no repositório e persiste no arquivo JSON.
     *
     * @param transaction Transação a ser salva.
     */
    @Override
    public void save(Transaction transaction) {
        transactions.add(transaction);
        saveTransactions();
    }

    /**
     * Atualiza uma transação existente, removendo a antiga e salvando a nova versão.
     *
     * @param transaction Transação a ser atualizada.
     */
    @Override
    public void update(Transaction transaction) {
        delete(transaction.getId());
        transactions.add(transaction);
        saveTransactions();
    }

    /**
     * Deleta uma transação pelo seu ID.
     *
     * @param id ID da transação a ser deletada.
     */
    @Override
    public void delete(UUID id) {
        transactions.removeIf(transaction -> transaction.getId().equals(id));
        saveTransactions();
    }

    /**
     * Deleta todas as transações do repositório.
     */
    @Override
    public void deleteAll() {
        transactions.clear();
        saveTransactions();
    }
}
