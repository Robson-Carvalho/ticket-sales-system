

package main.java.UEFS.system.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import main.java.UEFS.system.interfaces.IRepository;
import main.java.UEFS.system.model.CreditCard;
import main.java.UEFS.system.model.CreditCard;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class CreditCardRepository  implements IRepository<CreditCard> {
    private static final String FILE_PATH = "src/main/java/UEFS/system/storage/creditCards.json";
    private final List<CreditCard> creditCards;

    public CreditCardRepository() {
        creditCards = loadCreditCards();
    }

    private List<CreditCard> loadCreditCards() {
        List<CreditCard> creditCardList = new ArrayList<>();
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<CreditCard>>() {}.getType();
            creditCardList =  new Gson().fromJson(reader, listType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return creditCardList != null ? creditCardList : new ArrayList<>();
    }

    private void saveCreditCards() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            gson.toJson(creditCards, writer);
        } catch (Exception e) {
            System.out.println("Error while saving creditCards");
        }
    }

    @Override
    public CreditCard findById(UUID id) {
        return loadCreditCards().stream().filter(creditCard -> creditCard.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<CreditCard> findAll() {
        return loadCreditCards();
    }

    @Override
    public void save(CreditCard creditCard) {
        creditCards.add(creditCard);
        saveCreditCards();
    }

    @Override
    public void update(CreditCard creditCard) {
        delete(creditCard.getId());
        creditCards.add(creditCard);
        saveCreditCards();
    }

    @Override
    public void delete(UUID id) {
        creditCards.removeIf(creditCard -> creditCard.getId().equals(id));
        saveCreditCards();
    }

    @Override
    public void deleteAll() {
        creditCards.clear();
        saveCreditCards();
    }
}
