

package main.java.UEFS.system.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import main.java.UEFS.system.interfaces.IRepository;
import main.java.UEFS.system.model.MailBox;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class MailBoxRepository  implements IRepository<MailBox> {
    private static final String FILE_PATH = "src/main/java/UEFS/system/storage/mailBox.json";
    private final List<MailBox> mailBox;

    public MailBoxRepository() {
        mailBox = loadMailBoxs();
    }

    private List<MailBox> loadMailBoxs() {
        List<MailBox> creditCardList = new ArrayList<>();
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<MailBox>>() {}.getType();
            creditCardList =  new Gson().fromJson(reader, listType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return creditCardList != null ? creditCardList : new ArrayList<>();
    }

    private void saveMailBoxs() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            gson.toJson(mailBox, writer);
        } catch (Exception e) {
            System.out.println("Error while saving mailBox");
        }
    }

    @Override
    public MailBox findById(UUID id) {
        return loadMailBoxs().stream().filter(creditCard -> creditCard.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<MailBox> findAll() {
        return loadMailBoxs();
    }

    @Override
    public void save(MailBox creditCard) {
        mailBox.add(creditCard);
        saveMailBoxs();
    }

    @Override
    public void update(MailBox creditCard) {
        delete(creditCard.getId());
        mailBox.add(creditCard);
        saveMailBoxs();
    }

    @Override
    public void delete(UUID id) {
        mailBox.removeIf(creditCard -> creditCard.getId().equals(id));
        saveMailBoxs();
    }

    @Override
    public void deleteAll() {
        mailBox.clear();
        saveMailBoxs();
    }
}
