package main.java.UEFS.system.model;

import java.util.Objects;
import java.util.UUID;

public class CreditCard {
    private final UUID id;
    private final UUID userID;
    private final String cardNumber;
    private final String expiryDate;
    private final String cvv;

    public CreditCard( UUID userID, String cardNumber, String expiryDate, String cvv) {
        this.id = UUID.randomUUID();
        this.userID = userID;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    public UUID getId() { return id; }
    public UUID getUserId() { return userID; }
    public String getNumber() { return cardNumber; }
    public String getExpiryDate() { return expiryDate; }
    public String getCvv() { return cvv; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard cc = (CreditCard) o;
        return Objects.equals(cardNumber, cc.cardNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber);
    }
}
