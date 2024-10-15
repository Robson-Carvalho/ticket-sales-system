package main.java.UEFS.system.testFacade.CardTestFacade;

import main.java.UEFS.system.controller.CardController;
import main.java.UEFS.system.controller.UserController;
import main.java.UEFS.system.model.Card;
import main.java.UEFS.system.model.User;

import java.util.Date;
import java.util.UUID;

public class CardTestFacade {
    private final CardController cardController;
    private final UserController userController;

    public CardTestFacade() {
        this.cardController = new CardController();
        this.userController = new UserController();
    }

    public String create(String userEmail, String cardNumber, Date expiryDate, int cvv){
        User user = userController.getByEmail(userEmail);
        String cvvString = String.valueOf(cvv);
        Card card = cardController.create(user.getId(), user.getName(),  "Visa", cardNumber, UUID.randomUUID().toString(), expiryDate, cvvString);
        return card.getId().toString();
    }

    public String getUserNameByCardId(String cardId){
        Card card = cardController.getById(UUID.fromString(cardId));
        return card.getUserName();
    }

    public String getCardNumberByCardId(String cardId){
        Card card = cardController.getById(UUID.fromString(cardId));
        return card.getCardNumber();
    }

    public int getYearByCardId(String cardId){
        Card card = cardController.getById(UUID.fromString(cardId));
        return card.getExpiryDate().getYear();
    }

    public int getMonthByCardId(String cardId){
        Card card = cardController.getById(UUID.fromString(cardId));
        return card.getExpiryDate().getMonth();
    }

    public int getDayByCardId(String cardId){
        Card card = cardController.getById(UUID.fromString(cardId));
        return card.getExpiryDate().getDay();
    }

    public void disable(String cardId){
        cardController.disableCreditCard(UUID.fromString(cardId));
    }

    public boolean getStatusByCardId(String cardId){
        Card card = cardController.getById(UUID.fromString(cardId));
        return card.getStatus();
    }

    public void delete(String cardId){
        cardController.delete(UUID.fromString(cardId));
    }

    public Card getById(String id){
        return cardController.getById(UUID.fromString(id));
    }

    public void deleteAllCards(){
        cardController.deleteAll();
    }
}
