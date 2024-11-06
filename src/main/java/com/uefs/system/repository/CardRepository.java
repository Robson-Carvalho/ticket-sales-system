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

package com.uefs.system.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.uefs.system.Interface.IRepository;
import com.uefs.system.model.Card;
import com.uefs.system.utils.PathsFile;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Classe repositório para a entidade Card, responsável por gerenciar as operações de CRUD
 * utilizando um arquivo JSON como armazenamento.
 */
public class CardRepository implements IRepository<Card> {
    private static final String FILE_PATH = PathsFile.getCardsJSON();
    private final List<Card> cards;

    /**
     * Construtor da classe que inicializa a lista de cartões carregando os dados do arquivo JSON.
     */
    public CardRepository() {
        cards = loadCards();
    }

    /**
     * Carrega a lista de cartões do arquivo JSON.
     *
     * @return Lista de cartões carregada do arquivo, ou uma nova lista se ocorrer algum erro
     */
    private List<Card> loadCards() {
        List<Card> cardList = new ArrayList<>();
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Card>>() {}.getType();
            cardList = new Gson().fromJson(reader, listType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cardList != null ? cardList : new ArrayList<>();
    }

    /**
     * Salva a lista de cartões no arquivo JSON.
     */
    private void saveCards() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            gson.toJson(cards, writer);
        } catch (Exception e) {
            System.out.println("Error while saving cards");
        }
    }

    /**
     * Busca um cartão pelo seu ID.
     *
     * @param id ID do cartão a ser buscado
     * @return Cartão encontrado ou null se não existir
     */
    @Override
    public Card findById(UUID id) {
        return loadCards().stream().filter(creditCard -> creditCard.getId().equals(id)).findFirst().orElse(null);
    }

    /**
     * Retorna todos os cartões salvos.
     *
     * @return Lista de todos os cartões
     */
    @Override
    public List<Card> findAll() {
        return loadCards();
    }

    /**
     * Salva um novo cartão no repositório e persiste no arquivo JSON.
     *
     * @param creditCard Cartão a ser salvo
     */
    @Override
    public void save(Card creditCard) {
        cards.add(creditCard);
        saveCards();
    }

    /**
     * Atualiza um cartão existente, removendo o antigo e salvando a nova versão.
     *
     * @param creditCard Cartão a ser atualizado
     */
    @Override
    public void update(Card creditCard) {
        delete(creditCard.getId());
        cards.add(creditCard);
        saveCards();
    }

    /**
     * Deleta um cartão pelo seu ID.
     *
     * @param id ID do cartão a ser deletado
     */
    @Override
    public void delete(UUID id) {
        cards.removeIf(creditCard -> creditCard.getId().equals(id));
        saveCards();
    }

    /**
     * Deleta todos os cartões do repositório.
     */
    @Override
    public void deleteAll() {
        cards.clear();
        saveCards();
    }
}
