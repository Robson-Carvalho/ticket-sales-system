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

package com.uefs.system.testFacade.TransactionTestFacade;

import com.uefs.system.controller.TransactionController;
import com.uefs.system.controller.UserController;
import com.uefs.system.model.Card;
import com.uefs.system.model.Ticket;
import com.uefs.system.model.Transaction;

import java.util.UUID;

/**
 * Facade para testes de transações, fornecendo uma interface simplificada para a criação, consulta e manipulação de compras.
 */
public class PurchaseTestFacade {
    private final TransactionController transactionController;
    private final UserController userController;

    /**
     * Construtor que inicializa os controladores de transações e de usuários.
     */
    public PurchaseTestFacade() {
        this.transactionController = new TransactionController();
        this.userController = new UserController();
    }

    /**
     * Cria uma nova compra sem cartão.
     *
     * @param email Email do usuário que realiza a compra.
     * @param eventId ID do evento.
     * @param seat Assento selecionado.
     * @return ID da compra criada.
     */
    public String create(String email, String eventId, String seat) {
        return transactionController.create(email, UUID.fromString(eventId), seat).toString();
    }

    /**
     * Cria uma nova compra com cartão.
     *
     * @param email Email do usuário que realiza a compra.
     * @param eventId ID do evento.
     * @param cardId ID do cartão usado na compra.
     * @param seat Assento selecionado.
     * @return ID da compra criada.
     */
    public String create(String email, String eventId, String cardId, String seat) {
        return transactionController.create(email, UUID.fromString(eventId), UUID.fromString(cardId), seat).toString();
    }

    /**
     * Retorna a transação pelo seu ID.
     *
     * @param id ID da transação.
     * @return Objeto Transaction correspondente ao ID.
     */
    public Transaction getById(String id) {
        return transactionController.getById(UUID.fromString(id));
    }

    /**
     * Retorna o ID do evento associado à compra.
     *
     * @param id ID da compra.
     * @return ID do evento.
     */
    public String getEventByPurchaseId(String id) {
        return transactionController.getEventByPurchaseId(UUID.fromString(id)).getId().toString();
    }

    /**
     * Retorna o login do usuário associado à compra.
     *
     * @param id ID da compra.
     * @return Login do usuário.
     */
    public String getUserLoginByPurchaseId(String id) {
        return transactionController.getUserLoginByPurchaseId(UUID.fromString(id));
    }

    /**
     * Retorna o ticket associado à compra.
     *
     * @param id ID da compra.
     * @return Objeto Ticket correspondente à compra.
     */
    public Ticket getTicketByPurchaseId(String id) {
        return transactionController.getTicketByPurchaseId(UUID.fromString(id));
    }

    /**
     * Retorna a quantidade de mensagens na caixa de correio do usuário associado à compra.
     *
     * @param id ID da compra.
     * @return Quantidade de mensagens.
     */
    public int getUserMailBoxByPurchaseId(String id) {
        return userController.getMailBox(transactionController.getById(UUID.fromString(id)).getUserID()).size();
    }

    /**
     * Retorna o cartão associado à compra.
     *
     * @param id ID da compra.
     * @return Objeto Card correspondente à compra.
     */
    public Card getCardByPurchaseID(String id) {
        return transactionController.getCardByPurchaseID(UUID.fromString(id));
    }

    /**
     * Deleta todas as compras.
     */
    public void deleteAllPurchases() {
        this.transactionController.deleteAll();
    }
}
