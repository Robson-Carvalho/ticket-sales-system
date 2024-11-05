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

package main.java.com.UEFS.system.controller;

import main.java.com.UEFS.system.Models.Comment;
import main.java.com.UEFS.system.Services.CommentService;

import java.util.List;
import java.util.UUID;

/**
 * Controlador responsável pela gestão de comentários.
 * Esta classe fornece métodos para criar, obter, atualizar e deletar comentários
 * através da interação com o serviço de comentários.
 */
public class CommentController {
    private final CommentService commentService;

    /**
     * Construtor do CommentController.
     * Inicializa o serviço de comentários.
     */
    public CommentController() {
        this.commentService = new CommentService();
    }

    /**
     * Cria um novo comentário.
     *
     * @param userID  ID do usuário que está fazendo o comentário.
     * @param eventID ID do evento ao qual o comentário está associado.
     * @param rating  Avaliação dada pelo usuário.
     * @param content Conteúdo do comentário.
     * @return O comentário criado.
     */
    public Comment create(UUID userID, UUID eventID, int rating, String content) {
        return commentService.create(new Comment(userID, eventID, rating, content));
    }

    /**
     * Obtém um comentário pelo seu ID.
     *
     * @param id ID do comentário a ser obtido.
     * @return O comentário correspondente ao ID fornecido.
     */
    public Comment getById(UUID id) {
        return commentService.getById(id);
    }

    /**
     * Obtém todos os comentários.
     *
     * @return Lista de todos os comentários.
     */
    public List<Comment> getAll() {
        return commentService.getAll();
    }

    /**
     * Obtém todos os comentários feitos por um usuário específico.
     *
     * @param id ID do usuário cujos comentários devem ser buscados.
     * @return Lista de comentários do usuário especificado.
     */
    public List<Comment> getAllByUserId(UUID id) {
        return commentService.getAllByUserId(id);
    }

    /**
     * Obtém todos os comentários relacionados a um evento específico.
     *
     * @param id ID do evento cujos comentários devem ser buscados.
     * @return Lista de comentários do evento especificado.
     */
    public List<Comment> getCommentsByEventId(UUID id) {
        return commentService.getCommentsByEventId(id);
    }

    /**
     * Obtém a média das avaliações de um evento pelo seu ID.
     *
     * @param id ID do evento cujas avaliações devem ser calculadas.
     * @return A média das avaliações do evento.
     */
    public float getEventRatingByEventId(UUID id) {
        return commentService.getEventRatingByEventId(id);
    }

    /**
     * Atualiza um comentário existente.
     *
     * @param id      ID do comentário a ser atualizado.
     * @param content Novo conteúdo do comentário.
     * @param rating  Nova avaliação do comentário.
     */
    public void update(UUID id, String content, int rating) {
        Comment oldComment = commentService.getById(id);
        oldComment.setContent(content);
        oldComment.setRating(rating);
        commentService.update(oldComment);
    }

    /**
     * Deleta um comentário pelo seu ID.
     *
     * @param id ID do comentário a ser deletado.
     */
    public void delete(UUID id) {
        commentService.delete(id);
    }

    /**
     * Deleta todos os comentários.
     */
    public void deleteAll() {
        commentService.deleteAll();
    }
}
