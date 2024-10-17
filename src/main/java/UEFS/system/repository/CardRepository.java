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
import main.java.UEFS.system.model.Card;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class CardRepository implements IRepository<Card> {
    private static final String FILE_PATH = "src/main/java/UEFS/system/storage/cards.json";
    private final List<Card> cards;

    public CardRepository() {
        cards = loadCards();
    }

    private List<Card> loadCards() {
        List<Card> cardList = new ArrayList<>();
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Card>>() {}.getType();
            cardList =  new Gson().fromJson(reader, listType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cardList != null ? cardList : new ArrayList<>();
    }

    private void saveCards() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            gson.toJson(cards, writer);
        } catch (Exception e) {
            System.out.println("Error while saving cards");
        }
    }

    @Override
    public Card findById(UUID id) {
        return loadCards().stream().filter(creditCard -> creditCard.getId().equals(id)).findFirst().orElse(null);

    }

    @Override
    public List<Card> findAll() {
        return loadCards();
    }

    @Override
    public void save(Card creditCard) {
        cards.add(creditCard);
        saveCards();
    }

    @Override
    public void update(Card creditCard) {
        delete(creditCard.getId());
        cards.add(creditCard);
        saveCards();
    }

    @Override
    public void delete(UUID id) {
        cards.removeIf(creditCard -> creditCard.getId().equals(id));
        saveCards();
    }

    @Override
    public void deleteAll() {
        cards.clear();
        saveCards();
    }
}
