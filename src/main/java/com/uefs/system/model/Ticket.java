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

import java.util.Objects;
import java.util.UUID;

/**
 * Classe que representa um ingresso no sistema.
 * Cada ingresso está associado a um evento, possui um preço, um código e um status.
 */
public class Ticket {
    private final UUID id;
    private final UUID eventID;
    private Boolean status;
    private final Double price;
    private final String code;

    /**
     * Construtor que inicializa um objeto Ticket com um evento, preço e código.
     * O ID do ingresso é gerado automaticamente.
     *
     * @param eventID ID do evento associado
     * @param price   Preço do ingresso
     * @param code    Código único do ingresso
     */
    public Ticket(UUID eventID, Double price, String code) {
        this.id = UUID.randomUUID();
        this.eventID = eventID;
        this.status = true;
        this.price = price;
        this.code = code;
    }

    /**
     * Construtor alternativo que inicializa um ingresso gratuito com um evento e código.
     * O ID do ingresso é gerado automaticamente.
     *
     * @param eventID ID do evento associado
     * @param code    Código único do ingresso
     */
    public Ticket(UUID eventID, String code) {
        this.id = UUID.randomUUID();
        this.eventID = eventID;
        this.status = true;
        this.price = 0.0;
        this.code = code;
    }

    /**
     * Retorna o ID único do ingresso.
     *
     * @return ID do ingresso
     */
    public UUID getId() {
        return id;
    }

    /**
     * Retorna o ID do evento associado.
     *
     * @return ID do evento
     */
    public UUID getEventId() {
        return this.eventID;
    }

    /**
     * Altera o status do ingresso.
     *
     * @param status Novo status do ingresso
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * Retorna o preço do ingresso.
     *
     * @return Preço do ingresso
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Retorna o código do ingresso.
     *
     * @return Código do ingresso
     */
    public String getCode() {
        return code;
    }

    /**
     * Verifica se o ingresso está ativo.
     *
     * @return true se o ingresso está ativo, caso contrário, false
     */
    public Boolean isActive() {
        return status;
    }

    /**
     * Compara dois ingressos com base no código e ID do evento.
     *
     * @param o Objeto a ser comparado
     * @return true se os ingressos forem iguais, caso contrário, false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(code, ticket.code) &&
                Objects.equals(eventID, ticket.eventID);
    }

    /**
     * Gera o código hash para o ingresso com base no evento e código.
     *
     * @return Código hash do ingresso
     */
    @Override
    public int hashCode() {
        return Objects.hash(eventID, code);
    }
}
