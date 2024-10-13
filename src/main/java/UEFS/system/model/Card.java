package main.java.UEFS.system.model;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents a credit card linked to a user. Each card has unique information such as card number, brand, account number,
 * expiry date, and a CVV code. It also includes the card's current status (active or inactive).
 */
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

    /**
     * Constructs a new Card object with the specified user ID, user name, brand, account number, card number,
     * expiry date, and CVV code. The card is initially set to active.
     *
     * @param userID        the unique identifier of the user who owns the card
     * @param userName      the name of the user who owns the card
     * @param brand         the brand of the card (e.g., Visa, MasterCard)
     * @param accountNumber the account number associated with the card
     * @param number        the card number
     * @param expiryDate    the expiry date of the card
     * @param cvv           the CVV code of the card
     */
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

    /**
     * Gets the name of the user who owns the card.
     *
     * @return the name of the user
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Gets the unique identifier of the card.
     *
     * @return the UUID of the card
     */
    public UUID getId() {
        return id;
    }

    /**
     * Gets the unique identifier of the user who owns the card.
     *
     * @return the UUID of the user
     */
    public UUID getUserId() {
        return userID;
    }

    /**
     * Gets the card number.
     *
     * @return the card number
     */
    public String getCardNumber() {
        return number;
    }

    /**
     * Disables the card, marking its status as inactive.
     */
    public void disable() {
        this.status = false;
    }

    /**
     * Gets the expiry date of the card.
     *
     * @return the expiry date
     */
    public Date getExpiryDate() {
        return expiryDate;
    }

    /**
     * Gets the CVV code of the card.
     *
     * @return the CVV code
     */
    public String getCvv() {
        return cvv;
    }

    /**
     * Checks if the card is currently active.
     *
     * @return true if the card is active, false otherwise
     */
    public Boolean isActive() {
        return status;
    }

    /**
     * Gets the brand of the card (e.g., Visa, MasterCard).
     *
     * @return the card brand
     */
    public String getCardBrand() {
        return brand;
    }

    /**
     * Gets the account number associated with the card.
     *
     * @return the account number
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Compares this card to another object for equality. Two cards are considered equal if they have the same
     * card number and brand.
     *
     * @param o the object to compare to
     * @return true if the cards are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card cc = (Card) o;
        return Objects.equals(number, cc.number) && Objects.equals(brand, cc.brand);
    }

    /**
     * Computes a hash code for the card, based on the card number and brand.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(number, brand);
    }
}
