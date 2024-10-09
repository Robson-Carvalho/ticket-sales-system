package main.java.UEFS.system.service;

import main.java.UEFS.system.interfaces.IService;
import main.java.UEFS.system.model.Card;
import main.java.UEFS.system.repository.CardRepository;

import java.util.List;
import java.util.UUID;

public class CardService implements IService<Card> {
    private final CardRepository cardRepository;

    public CardService() {this.cardRepository = new CardRepository();}

    @Override
    public Card create(Card card) {cardRepository.save(card); return card;}

    @Override
    public List<Card> getAll() {return cardRepository.findAll();}

    @Override
    public Card getById(UUID id) {return cardRepository.findById(id);}

    @Override
    public void update(Card card) {cardRepository.update(card);}

    @Override
    public void delete(UUID id) {cardRepository.delete(id);}

    @Override
    public void deleteAll() {cardRepository.deleteAll();}
}
