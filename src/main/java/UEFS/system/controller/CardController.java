package main.java.UEFS.system.controller;

import main.java.UEFS.system.model.Card;
import main.java.UEFS.system.service.CardService;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CardController {
    private final CardService cardService;

    public CardController() {
        this.cardService = new CardService();
    }

    public Card create(UUID userID, String name, String brand, String number, String accountNumber, Date expiryDate, String cvv){
        Card card = new Card(userID, name, brand, accountNumber, number, expiryDate, cvv);
        return cardService.create(card);
    }

    public void delete(UUID id){
        cardService.delete(id);
    }

    public Card getById(UUID id){
        return cardService.getById(id);
    }

    public Card disableCreditCard(UUID id){
        Card card = getById(id);

        card.disable();

        cardService.update(card);

        return card;
    }

    public List<Card> getAll(){
        return cardService.getAll();
    }

    public void deleteAll(){cardService.deleteAll();}
}
