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

package main.java.UEFS.system.testFacade.TransactionTestFacade;

import main.java.UEFS.system.controller.CardController;
import main.java.UEFS.system.controller.TransactionController;
import main.java.UEFS.system.controller.UserController;
import main.java.UEFS.system.model.Card;
import main.java.UEFS.system.model.Ticket;
import main.java.UEFS.system.model.Transaction;

import java.util.UUID;

public class PurchaseTestFacade {
    private final TransactionController transactionController;
    private final UserController userController;

    public PurchaseTestFacade() {
        this.transactionController = new TransactionController();
        this.userController = new UserController();
    }

    public String create(String email, String eventId, String seat){
        return transactionController.create(email, UUID.fromString(eventId), seat).toString();
    }

    public String create(String email, String eventId, String cardId, String seat){
        return transactionController.create(email, UUID.fromString(eventId), UUID.fromString(cardId), seat).toString();

    }

    public Transaction getById(String id){
        return transactionController.getById(UUID.fromString(id));
    }

    public String getEventByPurchaseId(String id){
        return transactionController.getEventByPurchaseId(UUID.fromString(id)).getId().toString();
    }

    public String getUserLoginByPurchaseId(String id){
        return transactionController.getUserLoginByPurchaseId(UUID.fromString(id));
    }

    public Ticket getTicketByPurchaseId(String id){return transactionController.getTicketByPurchaseId(UUID.fromString(id));}

    public int getUserMailBoxByPurchaseId(String id){
        return userController.getMailBox(transactionController.getById(UUID.fromString(id)).getUserID()).size();
    }

    public Card getCardByPurchaseID(String id){
        return transactionController.getCardByPurchaseID(UUID.fromString(id));
    }

    public void deleteAllPurchases(){this.transactionController.deleteAll();}
}
