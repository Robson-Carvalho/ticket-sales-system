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

package main.java.UEFS.system.model;

import java.util.UUID;

/**
 * Classe que representa um comentário feito por um usuário em relação a um evento.
 * Cada comentário contém um ID único, o ID do usuário que comentou, o ID do evento,
 * a avaliação (rating) do evento e o conteúdo textual do comentário.
 */
public class Comment {
    private final UUID id;
    private final UUID userID;
    private final UUID eventID;
    private int rating;
    private String content;

    /**
     * Construtor que inicializa um objeto Comment com as informações fornecidas.
     * O ID do comentário é gerado automaticamente.
     *
     * @param userID   ID do usuário que fez o comentário
     * @param eventID  ID do evento comentado
     * @param rating   Avaliação numérica atribuída ao evento
     * @param content  Texto do comentário
     */
    public Comment(UUID userID, UUID eventID, int rating, String content) {
        this.id = UUID.randomUUID();
        this.userID = userID;
        this.eventID = eventID;
        this.rating = rating;
        this.content = content;
    }

    /**
     * Retorna o ID único do comentário.
     *
     * @return ID do comentário
     */
    public UUID getId() {
        return id;
    }

    /**
     * Retorna o ID do usuário que fez o comentário.
     *
     * @return ID do usuário
     */
    public UUID getUserID() {
        return this.userID;
    }

    /**
     * Retorna o ID do evento comentado.
     *
     * @return ID do evento
     */
    public UUID getEventID() {
        return eventID;
    }

    /**
     * Retorna a avaliação numérica atribuída ao evento.
     *
     * @return Avaliação do evento
     */
    public int getRating() {
        return rating;
    }

    /**
     * Retorna o conteúdo textual do comentário.
     *
     * @return Conteúdo do comentário
     */
    public String getContent() {
        return content;
    }

    /**
     * Define um novo conteúdo para o comentário.
     *
     * @param content Novo conteúdo do comentário
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Define uma nova avaliação numérica para o evento.
     *
     * @param rating Nova avaliação do evento
     */
    public void setRating(int rating) {
        this.rating = rating;
    }
}
