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
import main.java.com.UEFS.system.Models.User;
import main.java.com.UEFS.system.PathsFile.PathsFile;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Classe repositório para a entidade User, responsável por gerenciar as operações de CRUD
 * utilizando um arquivo JSON como armazenamento.
 */
public class UserRepository implements IRepository<User> {
    private static final String FILE_PATH = PathsFile.getUsersJSON();
    private final List<User> users;

    /**
     * Construtor da classe que inicializa a lista de usuários carregando os dados do arquivo JSON.
     */
    public UserRepository() {
        users = loadUsers();
    }

    /**
     * Carrega a lista de usuários do arquivo JSON.
     *
     * @return Lista de usuários carregada do arquivo, ou uma nova lista se ocorrer algum erro.
     */
    private List<User> loadUsers() {
        List<User> userList = new ArrayList<>();
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<User>>() {}.getType();
            userList = new Gson().fromJson(reader, listType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList != null ? userList : new ArrayList<>();
    }

    /**
     * Salva a lista de usuários no arquivo JSON.
     */
    private void saveUsers() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            gson.toJson(users, writer);
        } catch (Exception e) {
            System.out.println("Error while saving users");
        }
    }

    /**
     * Busca um usuário pelo seu ID.
     *
     * @param id ID do usuário a ser buscado.
     * @return Usuário encontrado ou null se não existir.
     */
    @Override
    public User findById(UUID id) {
        return loadUsers().stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    /**
     * Retorna todos os usuários salvos.
     *
     * @return Lista de todos os usuários.
     */
    @Override
    public List<User> findAll() {
        return loadUsers();
    }

    /**
     * Salva um novo usuário no repositório e persiste no arquivo JSON.
     *
     * @param user Usuário a ser salvo.
     */
    @Override
    public void save(User user) {
        users.add(user);
        saveUsers();
    }

    /**
     * Atualiza um usuário existente, removendo o antigo e salvando a nova versão.
     *
     * @param user Usuário a ser atualizado.
     */
    @Override
    public void update(User user) {
        delete(user.getId());
        users.add(user);
        saveUsers();
    }

    /**
     * Deleta um usuário pelo seu ID.
     *
     * @param id ID do usuário a ser deletado.
     */
    @Override
    public void delete(UUID id) {
        users.removeIf(user -> user.getId().equals(id));
        saveUsers();
    }

    /**
     * Deleta todos os usuários do repositório.
     */
    public void deleteAll() {
        users.clear();
        saveUsers();
    }
}
