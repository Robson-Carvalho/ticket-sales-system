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

package main.java.UEFS.system.model;

import java.util.UUID;

public class Comment {
    private final UUID id;
    private final UUID userID;
    private final UUID eventID;
    private int rating;
    private String content;

    public Comment(UUID userID, UUID eventID, int rating, String content) {
        this.id = UUID.randomUUID();
        this.userID = userID;
        this.eventID = eventID;
        this.rating = rating;
        this.content = content;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserID() {
        return this.userID;
    }


    public UUID getEventID() {
        return eventID;
    }


    public int getRating() {
        return rating;
    }


    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
