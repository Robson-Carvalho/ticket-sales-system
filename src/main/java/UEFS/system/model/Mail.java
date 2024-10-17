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

public class Mail {
    private final UUID id;
    private final UUID userID;
    private final String subject;
    private final String content;

    public Mail(UUID userID, String subject, String content) {
        this.id = UUID.randomUUID();
        this.userID = userID;
        this.subject = subject;
        this.content = content;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserID() {
        return userID;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }
}
