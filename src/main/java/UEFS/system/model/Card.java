package main.java.UEFS.system.model;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Card {
    private final UUID id;
    private final UUID userID;
    private final String userName;
    private final String brand;
    private final String accountNumber;
    private final String number;
    private final Date expiryDate;
    private final String cvv;
    private Boolean status;

    public Card(UUID userID, String userName, String brand, String accountNumber, String number, Date expiryDate, String cvv) {
        this.id = UUID.randomUUID();
        this.userID = userID;
        this.userName = userName;
        this.brand = brand;
        this.accountNumber = accountNumber;
        this.number = number;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.status = true;
    }

    public String getUserName(){return userName;}
    public UUID getId() { return id; }
    public UUID getUserId() { return userID; }
    public String getCardNumber() { return number; }
    public void disable() {this.status = false;}
    public Date getExpiryDate() { return expiryDate; }
    public String getCvv() { return cvv; }
    public Boolean isActive() { return status; }
    public String getCardBrand() { return brand; }
    public String getAccountNumber() { return accountNumber; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card cc = (Card) o;
        return Objects.equals(number, cc.number) && Objects.equals(brand, cc.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, brand);
    }
}
