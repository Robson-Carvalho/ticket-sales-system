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
import main.java.com.UEFS.system.Models.Mail;
import main.java.com.UEFS.system.PathsFile.PathsFile;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Classe repositório para a entidade Mail, responsável por gerenciar as operações de CRUD
 * utilizando um arquivo JSON como armazenamento.
 */
public class MailRepository implements IRepository<Mail> {
    private static final String FILE_PATH = PathsFile.getMailsJSON();
    private final List<Mail> mails;

    /**
     * Construtor da classe que inicializa a lista de emails carregando os dados do arquivo JSON.
     */
    public MailRepository() {
        mails = loadMailBoxs();
    }

    /**
     * Carrega a lista de emails do arquivo JSON.
     *
     * @return Lista de emails carregada do arquivo, ou uma nova lista se ocorrer algum erro.
     */
    private List<Mail> loadMailBoxs() {
        List<Mail> mailList = new ArrayList<>();
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Mail>>() {}.getType();
            mailList = new Gson().fromJson(reader, listType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mailList != null ? mailList : new ArrayList<>();
    }

    /**
     * Salva a lista de emails no arquivo JSON.
     */
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

    /**
     * Busca um email pelo seu ID.
     *
     * @param id ID do email a ser buscado.
     * @return Email encontrado ou null se não existir.
     */
    @Override
    public Mail findById(UUID id) {
        return loadMailBoxs().stream().filter(mail -> mail.getId().equals(id)).findFirst().orElse(null);
    }

    /**
     * Retorna todos os emails salvos.
     *
     * @return Lista de todos os emails.
     */
    @Override
    public List<Mail> findAll() {
        return loadMailBoxs();
    }

    /**
     * Salva um novo email no repositório e persiste no arquivo JSON.
     *
     * @param mail Email a ser salvo.
     */
    @Override
    public void save(Mail mail) {
        mails.add(mail);
        saveMails();
    }

    /**
     * Atualiza um email existente, removendo o antigo e salvando a nova versão.
     *
     * @param mail Email a ser atualizado.
     */
    @Override
    public void update(Mail mail) {
        delete(mail.getId());
        mails.add(mail);
        saveMails();
    }

    /**
     * Deleta um email pelo seu ID.
     *
     * @param id ID do email a ser deletado.
     */
    @Override
    public void delete(UUID id) {
        mails.removeIf(mail -> mail.getId().equals(id));
        saveMails();
    }

    /**
     * Deleta todos os emails do repositório.
     */
    @Override
    public void deleteAll() {
        mails.clear();
        saveMails();
    }
}
