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

package com.uefs.system.controller;

import com.uefs.system.model.Card;
import com.uefs.system.service.CardService;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Controlador responsável pela gestão de cartões.
 * Esta classe fornece métodos para criar, deletar, obter e gerenciar cartões
 * através da interação com o serviço de cartões.
 */
public class CardController {
    private final CardService cardService;

    /**
     * Construtor do CardController.
     * Inicializa o serviço de cartões.
     */
    public CardController() {
        this.cardService = new CardService();
    }

    /**
     * Cria um novo cartão.
     *
     * @param userID       ID do usuário associado ao cartão.
     * @param name         Nome do titular do cartão.
     * @param brand        Marca do cartão.
     * @param number       Número do cartão.
     * @param accountNumber Número da conta associada ao cartão.
     * @param expiryDate   Data de expiração do cartão.
     * @param cvv          Código de segurança do cartão.
     * @return O cartão criado.
     */
    public Card create(UUID userID, String name, String brand, String number, String accountNumber, Date expiryDate, String cvv) {
        return cardService.create(new Card(userID, name, brand, accountNumber, number, expiryDate, cvv));
    }

    /**
     * Deleta um cartão pelo seu ID.
     *
     * @param id ID do cartão a ser deletado.
     */
    public void delete(UUID id) {
        cardService.delete(id);
    }

    /**
     * Obtém um cartão pelo seu ID.
     *
     * @param id ID do cartão a ser obtido.
     * @return O cartão correspondente ao ID fornecido.
     */
    public Card getById(UUID id) {
        return cardService.getById(id);
    }

    /**
     * Obtém um cartão pelo seu número.
     *
     * @param number Número do cartão a ser buscado.
     * @return O cartão correspondente ao número fornecido.
     */
    public Card getCardByNumber(String number) {
        return cardService.getCardByNumber(number);
    }

    /**
     * Desativa um cartão pelo seu ID.
     *
     * @param id ID do cartão a ser desativado.
     */
    public void disable(UUID id) {
        cardService.disable(id);
    }

    /**
     * Obtém todos os cartões.
     *
     * @return Lista de todos os cartões.
     */
    public List<Card> getAll() {
        return cardService.getAll();
    }

    /**
     * Deleta todos os cartões.
     */
    public void deleteAll() {
        cardService.deleteAll();
    }
}
