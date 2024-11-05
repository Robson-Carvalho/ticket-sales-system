/***************************
 * Autor: Robson Carvalho de Souza
 * Componente Curricular: MI de Programação
 * Concluído em: 16/09/2024
 *
 * Declaro que este código foi elaborado por mim de forma individual e não contém nenhum
 * trecho de código de outro colega ou de outro autor, tais como provindos de livros e
 * apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
 * de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
 * do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
 ******************************/

package main.java.com.UEFS.system.Services;

import main.java.com.UEFS.system.Interfaces.IService;
import main.java.com.UEFS.system.Models.Card;
import main.java.com.UEFS.system.Repositories.CardRepository;

import java.util.List;
import java.util.UUID;

/**
 * Classe de serviço para gerenciar operações relacionadas ao cartão.
 * Implementa a interface IService para a entidade Card.
 */
public class CardService implements IService<Card> {
    private final CardRepository cardRepository;

    /**
     * Construtor da classe que inicializa o repositório de cartões.
     */
    public CardService() {
        this.cardRepository = new CardRepository();
    }

    /**
     * Cria um novo cartão, verificando se o número do cartão já existe.
     *
     * @param card Cartão a ser criado.
     * @return O cartão criado.
     * @throws IllegalArgumentException Se o número do cartão já existir.
     */
    @Override
    public Card create(Card card) {
        Card _c = this.getCardByNumber(card.getCardNumber());

        if (_c != null) {
            throw new IllegalArgumentException("Cartão com este número já existe.");
        }

        cardRepository.save(card);
        return card;
    }

    /**
     * Retorna todos os cartões salvos.
     *
     * @return Lista de todos os cartões.
     */
    @Override
    public List<Card> getAll() {
        return cardRepository.findAll();
    }

    /**
     * Busca um cartão pelo seu ID.
     *
     * @param id ID do cartão a ser buscado.
     * @return Cartão encontrado ou null se não existir.
     */
    @Override
    public Card getById(UUID id) {
        return cardRepository.findById(id);
    }

    /**
     * Busca um cartão pelo seu número.
     *
     * @param number Número do cartão a ser buscado.
     * @return Cartão encontrado ou null se não existir.
     */
    public Card getCardByNumber(String number) {
        return this.getAll().stream()
                .filter(card -> card.getCardNumber().equals(number))
                .findFirst()
                .orElse(null);
    }

    /**
     * Desabilita um cartão pelo seu ID.
     *
     * @param id ID do cartão a ser desabilitado.
     */
    public void disable(UUID id) {
        Card card = this.getById(id);

        if (card != null) {
            card.disable();
            this.update(card);
        }
    }

    /**
     * Atualiza um cartão existente.
     *
     * @param card Cartão a ser atualizado.
     */
    @Override
    public void update(Card card) {
        cardRepository.update(card);
    }

    /**
     * Deleta um cartão pelo seu ID.
     *
     * @param id ID do cartão a ser deletado.
     */
    @Override
    public void delete(UUID id) {
        cardRepository.delete(id);
    }

    /**
     * Deleta todos os cartões do repositório.
     */
    @Override
    public void deleteAll() {
        cardRepository.deleteAll();
    }
}
