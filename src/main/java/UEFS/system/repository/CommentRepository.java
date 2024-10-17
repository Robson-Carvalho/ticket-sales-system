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
import main.java.UEFS.system.model.Comment;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class CommentRepository implements IRepository<Comment> {
    private static final String FILE_PATH = "src/main/java/UEFS/system/storage/comments.json";
    private final List<Comment> comments;

    public CommentRepository() {comments = loadComments();}

    private List<Comment> loadComments() {
        List<Comment> commentList = new ArrayList<>();
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Comment>>() {}.getType();
            commentList =  new Gson().fromJson(reader, listType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return commentList != null ? commentList : new ArrayList<>();
    }

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

    @Override
    public Comment findById(UUID id) {
        return loadComments().stream().filter(event -> event.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Comment> findAll() {
        return loadComments();
    }

    @Override
    public void save(Comment comment) {
        comments.add(comment);
        saveComments();
    }

    @Override
    public void update(Comment comment) {
        delete(comment.getId());
        comments.add(comment);
        saveComments();
    }

    @Override
    public void delete(UUID id) {
        comments.removeIf(event -> event.getId().equals(id));
        saveComments();
    }

    @Override
    public void deleteAll() {
        comments.clear();
        saveComments();
    }
}
