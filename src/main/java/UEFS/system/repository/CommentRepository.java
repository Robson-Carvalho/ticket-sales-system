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

package main.java.UEFS.system.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import main.java.UEFS.system.interfaces.IRepository;
import main.java.UEFS.system.model.Comment;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Classe repositório para a entidade Comment, responsável por gerenciar as operações de CRUD
 * utilizando um arquivo JSON como armazenamento.
 */
public class CommentRepository implements IRepository<Comment> {
    private static final String FILE_PATH = "src/main/java/UEFS/system/storage/comments.json";
    private final List<Comment> comments;

    /**
     * Construtor da classe que inicializa a lista de comentários carregando os dados do arquivo JSON.
     */
    public CommentRepository() {
        comments = loadComments();
    }

    /**
     * Carrega a lista de comentários do arquivo JSON.
     *
     * @return Lista de comentários carregada do arquivo, ou uma nova lista se ocorrer algum erro.
     */
    private List<Comment> loadComments() {
        List<Comment> commentList = new ArrayList<>();
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Comment>>() {}.getType();
            commentList = new Gson().fromJson(reader, listType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return commentList != null ? commentList : new ArrayList<>();
    }

    /**
     * Salva a lista de comentários no arquivo JSON.
     */
    private void saveComments() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            gson.toJson(comments, writer);
        } catch (Exception e) {
            System.out.println("Error while saving comments");
        }
    }

    /**
     * Busca um comentário pelo seu ID.
     *
     * @param id ID do comentário a ser buscado.
     * @return Comentário encontrado ou null se não existir.
     */
    @Override
    public Comment findById(UUID id) {
        return loadComments().stream().filter(comment -> comment.getId().equals(id)).findFirst().orElse(null);
    }

    /**
     * Retorna todos os comentários salvos.
     *
     * @return Lista de todos os comentários.
     */
    @Override
    public List<Comment> findAll() {
        return loadComments();
    }

    /**
     * Salva um novo comentário no repositório e persiste no arquivo JSON.
     *
     * @param comment Comentário a ser salvo.
     */
    @Override
    public void save(Comment comment) {
        comments.add(comment);
        saveComments();
    }

    /**
     * Atualiza um comentário existente, removendo o antigo e salvando a nova versão.
     *
     * @param comment Comentário a ser atualizado.
     */
    @Override
    public void update(Comment comment) {
        delete(comment.getId());
        comments.add(comment);
        saveComments();
    }

    /**
     * Deleta um comentário pelo seu ID.
     *
     * @param id ID do comentário a ser deletado.
     */
    @Override
    public void delete(UUID id) {
        comments.removeIf(comment -> comment.getId().equals(id));
        saveComments();
    }

    /**
     * Deleta todos os comentários do repositório.
     */
    @Override
    public void deleteAll() {
        comments.clear();
        saveComments();
    }
}
