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

package com.uefs.system.testFacade.CardTestFacade;

import com.uefs.system.controller.CardController;
import com.uefs.system.controller.UserController;
import com.uefs.system.model.Card;
import com.uefs.system.model.User;

import java.util.Date;
import java.util.UUID;

/**
 * Facade para facilitar a criação e manipulação de cartões durante os testes.
 */
public class CardTestFacade {
    private final CardController cardController;
    private final UserController userController;

    /**
     * Construtor que inicializa os controladores de cartões e usuários.
     */
    public CardTestFacade() {
        this.cardController = new CardController();
        this.userController = new UserController();
    }

    /**
     * Cria um novo cartão para um usuário.
     *
     * @param userEmail Email do usuário para associar o cartão.
     * @param cardNumber Número do cartão.
     * @param expiryDate Data de expiração do cartão.
     * @param cvv Código de segurança do cartão.
     * @return ID do cartão criado.
     */
    public String create(String userEmail, String cardNumber, Date expiryDate, int cvv) {
        User user = userController.getByEmail(userEmail);
        String cvvString = String.valueOf(cvv);
        Card card = cardController.create(user.getId(), user.getName(), "Visa", cardNumber, UUID.randomUUID().toString(), expiryDate, cvvString);
        return card.getId().toString();
    }

    /**
     * Retorna o nome do usuário associado a um cartão.
     *
     * @param cardId ID do cartão.
     * @return Nome do usuário.
     */
    public String getUserNameByCardId(String cardId) {
        Card card = cardController.getById(UUID.fromString(cardId));
        return card.getUserName();
    }

    /**
     * Retorna o número do cartão.
     *
     * @param cardId ID do cartão.
     * @return Número do cartão.
     */
    public String getCardNumberByCardId(String cardId) {
        Card card = cardController.getById(UUID.fromString(cardId));
        return card.getCardNumber();
    }

    /**
     * Retorna o ano de expiração de um cartão.
     *
     * @param cardId ID do cartão.
     * @return Ano de expiração.
     */
    public int getYearByCardId(String cardId) {
        Card card = cardController.getById(UUID.fromString(cardId));
        return card.getExpiryDate().getYear();
    }

    /**
     * Retorna o mês de expiração de um cartão.
     *
     * @param cardId ID do cartão.
     * @return Mês de expiração.
     */
    public int getMonthByCardId(String cardId) {
        Card card = cardController.getById(UUID.fromString(cardId));
        return card.getExpiryDate().getMonth();
    }

    /**
     * Retorna o dia de expiração de um cartão.
     *
     * @param cardId ID do cartão.
     * @return Dia de expiração.
     */
    public int getDayByCardId(String cardId) {
        Card card = cardController.getById(UUID.fromString(cardId));
        return card.getExpiryDate().getDay();
    }

    /**
     * Desativa um cartão.
     *
     * @param cardId ID do cartão a ser desativado.
     */
    public void disable(String cardId) {
        cardController.disable(UUID.fromString(cardId));
    }

    /**
     * Retorna o status de um cartão (ativo ou inativo).
     *
     * @param cardId ID do cartão.
     * @return Status do cartão.
     */
    public boolean getStatusByCardId(String cardId) {
        Card card = cardController.getById(UUID.fromString(cardId));
        return card.getStatus();
    }

    /**
     * Deleta um cartão pelo seu ID.
     *
     * @param cardId ID do cartão a ser deletado.
     */
    public void delete(String cardId) {
        cardController.delete(UUID.fromString(cardId));
    }

    /**
     * Busca um cartão pelo seu ID.
     *
     * @param id ID do cartão.
     * @return Objeto Card correspondente ao ID.
     */
    public Card getById(String id) {
        return cardController.getById(UUID.fromString(id));
    }

    /**
     * Deleta todos os cartões.
     */
    public void deleteAllCards() {
        cardController.deleteAll();
    }
}
