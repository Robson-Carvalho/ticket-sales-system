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
        return cardService.create(new Card(userID, name, brand, accountNumber, number, expiryDate, cvv));
    }

    public void delete(UUID id){cardService.delete(id);}

    public Card getById(UUID id){return cardService.getById(id);}

    public Card getCardByNumber(String number){return cardService.getCardByNumber(number);}

    public void disable(UUID id){cardService.disable(id);}

    public List<Card> getAll(){return cardService.getAll();}

    public void deleteAll(){cardService.deleteAll();}
}
