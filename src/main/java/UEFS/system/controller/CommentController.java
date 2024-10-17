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

package main.java.UEFS.system.controller;

import main.java.UEFS.system.model.Comment;
import main.java.UEFS.system.service.CommentService;

import java.util.List;
import java.util.UUID;

public class CommentController {
    private final CommentService commentService;

    public CommentController() {
        this.commentService = new CommentService();
    }

    public Comment create(UUID userID, UUID eventID, int rating, String content){
        return commentService.create(new Comment(userID, eventID, rating, content));
    }

    public Comment getById(UUID id){
        return commentService.getById(id);
    }

    public List<Comment> getAll(){
        return commentService.getAll();
    }

    public List<Comment> getAllByUserId(UUID id){return commentService.getAllByUserId(id);}

    public  List<Comment> getCommentsByEventId(UUID id){return commentService.getCommentsByEventId(id);}

    public float getEventRatingByEventId(UUID id){return commentService.getEventRatingByEventId(id);}

    public void update(UUID id, String content, int rating){
        Comment oldComment = commentService.getById(id);
        oldComment.setContent(content);
        oldComment.setRating(rating);
        commentService.update(oldComment);
    }

    public void delete(UUID id){commentService.delete(id);}

    public void deleteAll(){commentService.deleteAll();}
}
