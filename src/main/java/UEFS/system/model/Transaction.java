package main.java.UEFS.system.model;

import main.java.UEFS.system.enums.PaymentMethod;

import java.util.Date;
import java.util.UUID;

/**
 * Represents a financial transaction in the system.
 * A transaction is associated with a user, a ticket, a payment method, and may include a credit card.
 */
public class Transaction {
    private final UUID id;
    private final UUID userID;
    private final UUID ticketID;
    private final UUID creditCardID;
    private final Date date;
    private final Double amount;
    private final PaymentMethod paymentMethod;

    /**
     * Constructs a new Transaction with the specified user ID, ticket ID, credit card ID, amount, and payment method.
     * The transaction date is set to the current date and time.
     *
     * @param userID        the ID of the user making the transaction
     * @param ticketID      the ID of the ticket associated with the transaction
     * @param creditCardID  the ID of the credit card used for the transaction
     * @param amount        the amount of the transaction
     * @param method        the payment method used for the transaction
     */
    public Transaction(UUID userID, UUID ticketID, UUID creditCardID, Double amount, PaymentMethod method) {
        this.id = UUID.randomUUID();
        this.userID = userID;
        this.ticketID = ticketID;
        this.creditCardID = creditCardID;
        this.date = new Date();
        this.amount = amount;
        this.paymentMethod = method;
    }

    /**
     * Constructs a new Transaction with the specified user ID, ticket ID, amount, and payment method.
     * The transaction does not include a credit card ID, and the date is set to the current date and time.
     *
     * @param userID   the ID of the user making the transaction
     * @param ticketID the ID of the ticket associated with the transaction
     * @param amount   the amount of the transaction
     * @param method   the payment method used for the transaction
     */
    public Transaction(UUID userID, UUID ticketID, Double amount, PaymentMethod method) {
        this.id = UUID.randomUUID();
        this.userID = userID;
        this.ticketID = ticketID;
        this.creditCardID = null;
        this.date = new Date();
        this.amount = amount;
        this.paymentMethod = method;
    }

    /**
     * Gets the unique identifier of the transaction.
     *
     * @return the UUID of the transaction
     */
    public UUID getId() {
        return id;
    }

    /**
     * Gets the user ID associated with the transaction.
     *
     * @return the UUID of the user
     */
    public UUID getUserID() {
        return userID;
    }

    /**
     * Gets the ticket ID associated with the transaction.
     *
     * @return the UUID of the ticket
     */
    public UUID getTicketID() {
        return ticketID;
    }

    /**
     * Gets the credit card ID used for the transaction, if applicable.
     *
     * @return the UUID of the credit card, or null if no credit card was used
     */
    public UUID getCreditCardID() {
        return creditCardID;
    }

    /**
     * Gets the date and time when the transaction was created.
     *
     * @return the date of the transaction
     */
    public Date getDate() {
        return date;
    }

    /**
     * Gets the amount of the transaction.
     *
     * @return the transaction amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Gets the payment method used for the transaction as a descriptive string.
     *
     * @return "Cartão" if the payment method is CREDIT_CARD, "Boleto" otherwise
     */
    public String getPaymentMethod() {
        if (paymentMethod.equals(PaymentMethod.CREDIT_CARD)) {
            return "Cartão";
        }
        return "Boleto";
    }
}
