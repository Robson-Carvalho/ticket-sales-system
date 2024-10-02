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

    public Transaction(UUID userID, UUID ticketID, UUID creditCardID, Date date, Double amount, PaymentMethod method) {
        this.id = UUID.randomUUID();
        this.userID = userID;
        this.ticketID = ticketID;
        this.creditCardID = creditCardID;
        this.date = date;
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
        if(paymentMethod == null) return null;

        if(paymentMethod.equals(PaymentMethod.CREDIT_CARD)){
            return "Cartão de Crédito/Débito";
        }

        return "Boleto";
    }
}
