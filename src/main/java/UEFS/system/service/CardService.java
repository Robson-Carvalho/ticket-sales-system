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

package main.java.UEFS.system.service;

import main.java.UEFS.system.interfaces.IService;
import main.java.UEFS.system.model.Card;
import main.java.UEFS.system.repository.CardRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CardService implements IService<Card> {
    private final CardRepository cardRepository;

    public CardService() {this.cardRepository = new CardRepository();}

    @Override
    public Card create(Card card) {
        Card _c = this.getCardByNumber(card.getCardNumber());

        if (_c != null) {
            throw new IllegalArgumentException("Cartão com este número já existe.");
        }

        cardRepository.save(card);

        return card;
    }

    @Override
    public List<Card> getAll() {return cardRepository.findAll();}

    @Override
    public Card getById(UUID id) {return cardRepository.findById(id);}

    public Card getCardByNumber(String number) {
        return this.getAll().stream().filter(card -> card.getCardNumber().equals(number)).findFirst().orElse(null);
    }

    public void disable(UUID id) {
        Card card = this.getById(id);

        if (card != null) {
            card.disable();
            this.update(card);
        }
    }

    @Override
    public void update(Card card) {cardRepository.update(card);}

    @Override
    public void delete(UUID id) {cardRepository.delete(id);}

    @Override
    public void deleteAll() {cardRepository.deleteAll();}
}
