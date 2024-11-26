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

package com.uefs.system.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Classe que representa um evento no sistema.
 * Cada evento possui um ID único, nome, descrição, data e uma lista de assentos.
 */
public class Event {
    private final UUID id;
    private final String name;
    private final String description;
    private final Date date;
    private final Double price;
    private final List<String> seats = new ArrayList<>();

    /**
     * Construtor que inicializa um objeto Event com as informações fornecidas.
     * O ID do evento é gerado automaticamente.
     *
     * @param name        Nome do evento
     * @param description Descrição do evento
     * @param date        Data do evento
     */
    public Event(String name, String description, Date date, Double price) {
        this.price = price;
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.date = date;
    }

    /**
     * Retorna o preço do evento.
     *
     * @return Preço do evento.
     */
    public Double getPrice() {
        return price;
    }


    /**
     * Retorna o ID único do evento.
     *
     * @return ID do evento
     */
    public UUID getId() {
        return id;
    }

    /**
     * Retorna o nome do evento.
     *
     * @return Nome do evento
     */
    public String getName() {
        return name;
    }

    /**
     * Retorna a descrição do evento.
     *
     * @return Descrição do evento
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retorna a data do evento.
     *
     * @return Data do evento
     */
    public Date getDate() {
        return date;
    }

    /**
     * Retorna a lista de assentos do evento.
     *
     * @return Lista de assentos
     */
    public List<String> getSeats() {
        return seats;
    }

    /**
     * Adiciona um assento à lista de assentos do evento.
     *
     * @param seat Assento a ser adicionado
     */
    public void addSeat(String seat) {
        seats.add(seat);
    }

    /**
     * Remove um assento da lista de assentos do evento.
     *
     * @param seat Assento a ser removido
     */
    public void removeSeat(String seat) {
        seats.remove(seat);
    }

    /**
     * Verifica se o evento está ativo, comparando a data atual com a data do evento.
     *
     * @return true se o evento ainda não ocorreu, caso contrário, false
     */
    public Boolean isActive() {
        Date today = new Date();
        return today.before(this.date);
    }
}
