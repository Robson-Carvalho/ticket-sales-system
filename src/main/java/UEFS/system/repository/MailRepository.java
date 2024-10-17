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
import main.java.UEFS.system.model.Mail;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class MailRepository implements IRepository<Mail> {
    private static final String FILE_PATH = "src/main/java/UEFS/system/storage/mails.json";
    private final List<Mail> mails;

    public MailRepository() {
        mails = loadMailBoxs();
    }

    private List<Mail> loadMailBoxs() {
        List<Mail> creditCardList = new ArrayList<>();
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Mail>>() {}.getType();
            creditCardList =  new Gson().fromJson(reader, listType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return creditCardList != null ? creditCardList : new ArrayList<>();
    }

    private void saveMails() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            gson.toJson(mails, writer);
        } catch (Exception e) {
            System.out.println("Error while saving mailBox");
        }
    }

    @Override
    public Mail findById(UUID id) {
        return loadMailBoxs().stream().filter(creditCard -> creditCard.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Mail> findAll() {
        return loadMailBoxs();
    }

    @Override
    public void save(Mail creditCard) {
        mails.add(creditCard);
        saveMails();
    }

    @Override
    public void update(Mail creditCard) {
        delete(creditCard.getId());
        mails.add(creditCard);
        saveMails();
    }

    @Override
    public void delete(UUID id) {
        mails.removeIf(creditCard -> creditCard.getId().equals(id));
        saveMails();
    }

    @Override
    public void deleteAll() {
        mails.clear();
        saveMails();
    }
}
