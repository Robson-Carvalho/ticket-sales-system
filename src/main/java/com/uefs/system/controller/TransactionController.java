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

package com.uefs.system.controller;

import com.uefs.system.emun.PaymentMethod;
import com.uefs.system.model.Ticket;
import com.uefs.system.model.Transaction;
import com.uefs.system.model.User;
import com.uefs.system.model.Card;
import com.uefs.system.model.Event;
import com.uefs.system.service.TransactionService;

import java.util.UUID;

public class TransactionController {
    private final TransactionService transactionService;
    private final UserController userController;
    private final TicketController ticketController;
    private final CardController cardController;
    private final EventController eventController;
    private final MailController mailController;

    public TransactionController() {
        this.transactionService = new TransactionService();
        this.userController = new UserController();
        this.ticketController = new TicketController();
        this.cardController = new CardController();
        this.eventController = new EventController();
        this.mailController = new MailController();
    }

    /**
     * Cria uma nova transação de compra de ingresso sem cartão de crédito.
     *
     * @param email   O email do usuário que está comprando o ingresso.
     * @param ticketID O UUID do ingresso associado à compra.
     * @param seat    O assento associado ao ingresso.
     * @return O UUID da transação criada.
     */
    public UUID create(String email, UUID ticketID, String seat) {
        User user = userController.getByEmail(email);
        Transaction transaction = new Transaction(user.getId(), ticketID, 0.0, PaymentMethod.TICKET);
        mailController.create(user, transaction, "Comprovante de compra");
        return transactionService.create(transaction).getId();
    }

    /**
     * Cria uma nova transação de compra de ingresso com cartão de crédito.
     *
     * @param email   O email do usuário que está comprando o ingresso.
     * @param ticketID O UUID do ingresso associado à compra.
     * @param cardId  O UUID do cartão de crédito utilizado na compra.
     * @param seat    O assento associado ao ingresso.
     * @return O UUID da transação criada.
     */
    public UUID create(String email, UUID ticketID, UUID cardId, String seat) {
        User user = userController.getByEmail(email);
        Card card = cardController.getById(cardId);
        Transaction transaction = new Transaction(user.getId(), ticketID, card.getId(), 0.0, PaymentMethod.CREDIT_CARD);
        mailController.create(user, transaction, "Comprovante de compra");
        return transactionService.create(transaction).getId();
    }

    /**
     * Obtém uma transação pelo ID.
     *
     * @param transactionId O UUID da transação a ser buscada.
     * @return A transação correspondente ao ID.
     */
    public Transaction getById(UUID transactionId) {
        return transactionService.getById(transactionId);
    }

    /**
     * Obtém o evento associado a uma compra pelo ID da transação.
     *
     * @param purchaseId O UUID da compra.
     * @return O evento associado à compra.
     */
    public Event getEventByPurchaseId(UUID purchaseId) {
        Transaction transaction = getById(purchaseId);
        Ticket ticket = ticketController.getById(transaction.getTicketID());
        return eventController.getById(ticket.getEventId());
    }

    /**
     * Obtém o login do usuário associado a uma compra pelo ID da transação.
     *
     * @param purchaseId O UUID da compra.
     * @return O login do usuário associado à compra.
     */
    public String getUserLoginByPurchaseId(UUID purchaseId) {
        Transaction transaction = getById(purchaseId);
        return userController.getById(transaction.getUserID()).getLogin();
    }

    /**
     * Obtém o ingresso associado a uma compra pelo ID da transação.
     *
     * @param purchaseId O UUID da compra.
     * @return O ingresso associado à compra.
     */
    public Ticket getTicketByPurchaseId(UUID purchaseId) {
        return ticketController.getById(this.getById(purchaseId).getTicketID());
    }

    /**
     * Obtém o cartão de crédito associado a uma compra pelo ID da transação.
     *
     * @param purchaseId O UUID da compra.
     * @return O cartão de crédito associado à compra.
     */
    public Card getCardByPurchaseID(UUID purchaseId) {
        Transaction transaction = getById(purchaseId);
        return cardController.getById(transaction.getCreditCardID());
    }

    /**
     * Deleta todas as transações.
     */
    public void deleteAll() {
        transactionService.deleteAll();
    }
}
