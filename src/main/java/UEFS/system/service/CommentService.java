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

package main.java.UEFS.system.service;

import main.java.UEFS.system.model.Comment;
import main.java.UEFS.system.model.Event;
import main.java.UEFS.system.model.User;
import main.java.UEFS.system.repository.CommentRepository;
import main.java.UEFS.system.interfaces.IService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CommentService implements IService<Comment> {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final EventService eventService;

    public CommentService() {
        this.commentRepository = new CommentRepository();
        this.userService = new UserService();
        this.eventService = new EventService();
    }

    @Override
    public Comment create(Comment comment) {
        Event event = eventService.getById(comment.getEventID());
        User user = userService.getById(comment.getUserID());

        if(user == null || event == null) return null;

        if(!event.isActive()){
            commentRepository.save(comment);
            return comment;
        }

        throw new SecurityException("Comentário só pode ser adicionando após a realização do evento.");
    }

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    public List<Comment> getAllByUserId(UUID id) {
        List<Comment> filterComments;
        filterComments = (List<Comment>) this.getAll().stream().filter(event -> event.getUserID().equals(id)).findFirst().orElse(null);
        return  filterComments;
    }

    public List<Comment> getCommentsByEventId(UUID eventID) {
        List<Comment> comments = new ArrayList<Comment>();

        for (Comment comment : this.getAll()) {
            if (comment.getEventID().equals(eventID)) {
                comments.add(comment);
            }
        }

        return comments;
    }

    public float getEventRatingByEventId(UUID eventID) {
        List<Comment> comments = this.getCommentsByEventId(eventID);
        int rating = 0;

        for (Comment comment : comments) {
            rating += comment.getRating();
        }

        return (float) rating / comments.size();
    }

    @Override
    public Comment getById(UUID id) {
        return commentRepository.findById(id);
    }

    @Override
    public void update(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void delete(UUID id) {
        commentRepository.delete(id);
    }

    @Override
    public void deleteAll() {
        commentRepository.deleteAll();
    }
}

