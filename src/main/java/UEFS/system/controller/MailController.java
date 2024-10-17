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

import main.java.UEFS.system.model.*;
import main.java.UEFS.system.service.MailService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MailController {
    private final MailService mailService;

    public MailController() {
        this.mailService = new MailService();
    }

    public void create(User user, Transaction transaction, String subject){
        String content = "Olá, " + user.getName().split(" ")[0] + "!\n\n"
                + "Comprovante de compra do ingresso para o evento.\n\n"
                + "ID da transação: "+transaction.getId();

        Mail mail = new Mail(user.getId(), subject, content);
        mailService.create(mail);
    }

    public List<Mail> getMailsByUserId(UUID id){
        List<Mail> mails = mailService.getAll();
        List<Mail> returnMails = new ArrayList<Mail>();

        for (Mail mail : mails) {
            if (mail.getUserID().equals(id)) {
                returnMails.add(mail);
            }
        }

        return returnMails;
    }

    public void deleteAll(){
        mailService.deleteAll();
    }
}
