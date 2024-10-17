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

package main.java.UEFS.system.model;

import java.util.Objects;
import java.util.UUID;

public class Ticket {
    private final UUID id;
    private final UUID eventID;
    private Boolean status;
    private final Double price;
    private final String code;


    public Ticket(UUID eventID, Double price, String code) {
        this.id = UUID.randomUUID();
        this.eventID = eventID;
        this.status = true;
        this.price = price;
        this.code = code;
    }


    public Ticket(UUID eventID, String code) {
        this.id = UUID.randomUUID();
        this.eventID = eventID;
        this.status = true;
        this.price = 0.0;
        this.code = code;
    }


    public UUID getId() {
        return id;
    }

    public UUID getEventId() {
        return this.eventID;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Double getPrice() {
        return price;
    }

    public String getCode() {
        return code;
    }

    public Boolean isActive() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(code, ticket.code) &&
                Objects.equals(eventID, ticket.eventID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventID, code);
    }
}
