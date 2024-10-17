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

import main.java.UEFS.system.enums.PaymentMethod;

import java.util.Date;
import java.util.UUID;


public class Transaction {
    private final UUID id;
    private final UUID userID;
    private final UUID ticketID;
    private final UUID creditCardID;
    private final Date date;
    private final Double amount;
    private final PaymentMethod paymentMethod;

    public Transaction(UUID userID, UUID ticketID, UUID creditCardID, Double amount, PaymentMethod method) {
        this.id = UUID.randomUUID();
        this.userID = userID;
        this.ticketID = ticketID;
        this.creditCardID = creditCardID;
        this.date = new Date();
        this.amount = amount;
        this.paymentMethod = method;
    }

    public Transaction(UUID userID, UUID ticketID, Double amount, PaymentMethod method) {
        this.id = UUID.randomUUID();
        this.userID = userID;
        this.ticketID = ticketID;
        this.creditCardID = null;
        this.date = new Date();
        this.amount = amount;
        this.paymentMethod = method;
    }


    public UUID getId() {
        return id;
    }

    public UUID getUserID() {
        return userID;
    }


    public UUID getTicketID() {
        return ticketID;
    }


    public UUID getCreditCardID() {
        return creditCardID;
    }


    public Date getDate() {
        return date;
    }

    public Double getAmount() {
        return amount;
    }

    public String getPaymentMethod() {
        if (paymentMethod.equals(PaymentMethod.CREDIT_CARD)) {
            return "Cartão";
        }
        return "Boleto";
    }
}
