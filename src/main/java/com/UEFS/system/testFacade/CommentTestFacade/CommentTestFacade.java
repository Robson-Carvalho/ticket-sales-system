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

package main.java.com.UEFS.system.testFacade.CommentTestFacade;

import main.java.com.UEFS.system.controller.CommentController;
import main.java.com.UEFS.system.Models.Comment;

import java.util.UUID;

/**
 * Facade para facilitar a criação e manipulação de comentários durante os testes.
 */
public class CommentTestFacade {
    private final CommentController commentController;

    /**
     * Construtor que inicializa o controlador de comentários.
     */
    public CommentTestFacade() {
        commentController = new CommentController();
    }

    /**
     * Cria um novo comentário para um evento.
     *
     * @param userID ID do usuário que faz o comentário.
     * @param eventID ID do evento no qual o comentário é feito.
     * @param rating Avaliação do evento dada pelo usuário.
     * @param content Conteúdo do comentário.
     * @return ID do comentário criado.
     */
    public String create(String userID, String eventID, int rating, String content) {
        Comment comment = commentController.create(UUID.fromString(userID), UUID.fromString(eventID), rating, content);
        return comment.getId().toString();
    }

    /**
     * Busca um comentário pelo seu ID.
     *
     * @param id ID do comentário.
     * @return Objeto Comment correspondente ao ID.
     */
    public Comment getById(String id) {
        return commentController.getById(UUID.fromString(id));
    }

    /**
     * Retorna o conteúdo de um comentário pelo seu ID.
     *
     * @param id ID do comentário.
     * @return Conteúdo do comentário.
     */
    public String getContentById(String id) {
        Comment comment = commentController.getById(UUID.fromString(id));
        return comment.getContent();
    }

    /**
     * Retorna a avaliação (rating) de um comentário pelo seu ID.
     *
     * @param id ID do comentário.
     * @return Avaliação dada no comentário.
     */
    public int getRatingCommentById(String id) {
        Comment comment = commentController.getById(UUID.fromString(id));
        return comment.getRating();
    }

    /**
     * Retorna o ID do usuário associado a um comentário.
     *
     * @param id ID do comentário.
     * @return ID do usuário.
     */
    public String getUserIdById(String id) {
        Comment comment = commentController.getById(UUID.fromString(id));
        return comment.getUserID().toString();
    }

    /**
     * Retorna o ID do evento associado a um comentário.
     *
     * @param id ID do comentário.
     * @return ID do evento.
     */
    public String getEventIdById(String id) {
        Comment comment = commentController.getById(UUID.fromString(id));
        return comment.getEventID().toString();
    }

    /**
     * Retorna a média de avaliações para um determinado evento.
     *
     * @param id ID do evento.
     * @return Média das avaliações do evento.
     */
    public float getEventRatingByEventId(String id) {
        return commentController.getEventRatingByEventId(UUID.fromString(id));
    }

    /**
     * Deleta um comentário pelo seu ID.
     *
     * @param commentId ID do comentário a ser deletado.
     */
    public void delete(String commentId) {
        commentController.delete(UUID.fromString(commentId));
    }

    /**
     * Deleta todos os comentários.
     */
    public void deleteAllComments() {
        commentController.deleteAll();
    }
}
