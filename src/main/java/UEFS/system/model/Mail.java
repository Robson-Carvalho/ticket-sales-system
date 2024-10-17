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
 * Classe que representa um e-mail no sistema.
 * Cada e-mail possui um ID único, o ID do usuário remetente, assunto e conteúdo.
 */
public class Mail {
    private final UUID id;
    private final UUID userID;
    private final String subject;
    private final String content;

    /**
     * Construtor que inicializa um objeto Mail com as informações fornecidas.
     * O ID do e-mail é gerado automaticamente.
     *
     * @param userID  ID do usuário remetente
     * @param subject Assunto do e-mail
     * @param content Conteúdo do e-mail
     */
    public Mail(UUID userID, String subject, String content) {
        this.id = UUID.randomUUID();
        this.userID = userID;
        this.subject = subject;
        this.content = content;
    }

    /**
     * Retorna o ID único do e-mail.
     *
     * @return ID do e-mail
     */
    public UUID getId() {
        return id;
    }

    /**
     * Retorna o ID do usuário remetente.
     *
     * @return ID do usuário remetente
     */
    public UUID getUserID() {
        return userID;
    }

    /**
     * Retorna o assunto do e-mail.
     *
     * @return Assunto do e-mail
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Retorna o conteúdo do e-mail.
     *
     * @return Conteúdo do e-mail
     */
    public String getContent() {
        return content;
    }
}
