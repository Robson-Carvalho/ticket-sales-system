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

/**
 * Classe de serviço para gerenciar operações relacionadas a comentários.
 * Implementa a interface IService para a entidade Comment.
 */
public class CommentService implements IService<Comment> {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final EventService eventService;

    /**
     * Construtor da classe que inicializa os repositórios necessários.
     */
    public CommentService() {
        this.commentRepository = new CommentRepository();
        this.userService = new UserService();
        this.eventService = new EventService();
    }

    /**
     * Cria um novo comentário, associando-o a um evento e a um usuário.
     *
     * @param comment Comentário a ser criado.
     * @return O comentário criado ou null se o evento ou usuário não existirem.
     * @throws SecurityException Se o evento não estiver ativo.
     */
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

    /**
     * Retorna todos os comentários salvos no repositório.
     *
     * @return Lista de todos os comentários.
     */
    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    /**
     * Retorna todos os comentários de um usuário específico.
     *
     * @param id ID do usuário cujos comentários serão filtrados.
     * @return Lista de comentários do usuário.
     */
    public List<Comment> getAllByUserId(UUID id) {
        List<Comment> filterComments;
        filterComments = (List<Comment>) this.getAll().stream().filter(event -> event.getUserID().equals(id)).findFirst().orElse(null);
        return  filterComments;
    }

    /**
     * Retorna todos os comentários associados a um evento específico.
     *
     * @param eventID ID do evento cujos comentários serão buscados.
     * @return Lista de comentários do evento.
     */
    public List<Comment> getCommentsByEventId(UUID eventID) {
        List<Comment> comments = new ArrayList<Comment>();

        for (Comment comment : this.getAll()) {
            if (comment.getEventID().equals(eventID)) {
                comments.add(comment);
            }
        }

        return comments;
    }

    /**
     * Calcula a média das avaliações dos comentários associados a um evento.
     *
     * @param eventID ID do evento cujas avaliações serão calculadas.
     * @return Média das avaliações dos comentários.
     */
    public float getEventRatingByEventId(UUID eventID) {
        List<Comment> comments = this.getCommentsByEventId(eventID);
        int rating = 0;

        for (Comment comment : comments) {
            rating += comment.getRating();
        }

        return (float) rating / comments.size();
    }

    /**
     * Busca um comentário pelo seu ID.
     *
     * @param id ID do comentário a ser buscado.
     * @return Comentário encontrado ou null se não existir.
     */
    @Override
    public Comment getById(UUID id) {
        return commentRepository.findById(id);
    }

    /**
     * Atualiza um comentário existente.
     *
     * @param comment Comentário a ser atualizado.
     */
    @Override
    public void update(Comment comment) {
        commentRepository.save(comment);
    }

    /**
     * Deleta um comentário pelo seu ID.
     *
     * @param id ID do comentário a ser deletado.
     */
    @Override
    public void delete(UUID id) {
        commentRepository.delete(id);
    }

    /**
     * Deleta todos os comentários do repositório.
     */
    @Override
    public void deleteAll() {
        commentRepository.deleteAll();
    }
}
