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

package main.java.com.UEFS.system.testFacade.TicketTestFacade;

import main.java.com.UEFS.system.controller.TicketController;
import main.java.com.UEFS.system.Models.Ticket;

import java.util.UUID;

/**
 * Facade para testes com tickets, fornecendo uma interface simplificada para a criação, consulta e manipulação de ingressos.
 */
public class TicketTestFacade {
    private final TicketController ticketController;

    /**
     * Construtor que inicializa o controlador de tickets.
     */
    public TicketTestFacade() {
        this.ticketController = new TicketController();
    }

    /**
     * Cria um novo ticket.
     *
     * @param eventId ID do evento para o qual o ticket é criado.
     * @param price Preço do ticket.
     * @param seat Assento associado ao ticket.
     * @return ID do ticket criado.
     */
    public String create(String eventId, Double price, String seat) {
        Ticket ticket = ticketController.create(UUID.fromString(eventId), price, seat);
        return ticket.getId().toString();
    }

    /**
     * Retorna o ID do evento associado ao ticket.
     *
     * @param id ID do ticket.
     * @return ID do evento.
     */
    public String getEventByTicketId(String id) {
        Ticket ticket = ticketController.getById(UUID.fromString(id));
        return ticket.getEventId().toString();
    }

    /**
     * Retorna o preço do ticket.
     *
     * @param id ID do ticket.
     * @return Preço do ticket.
     */
    public Double getPriceByTicketId(String id) {
        Ticket ticket = ticketController.getById(UUID.fromString(id));
        return ticket.getPrice();
    }

    /**
     * Cancela o ticket pelo seu ID.
     *
     * @param id ID do ticket.
     */
    public void cancelByTicketId(String id) {
        ticketController.cancelById(UUID.fromString(id));
    }

    /**
     * Retorna o código do assento associado ao ticket.
     *
     * @param id ID do ticket.
     * @return Código do assento.
     */
    public String getSeatByTicketId(String id) {
        Ticket ticket = ticketController.getById(UUID.fromString(id));
        return ticket.getCode();
    }

    /**
     * Reativa um ticket cancelado pelo seu ID.
     *
     * @param id ID do ticket.
     */
    public void reactiveById(String id) {
        ticketController.reactiveById(UUID.fromString(id));
    }

    /**
     * Retorna o ticket pelo seu ID.
     *
     * @param id ID do ticket.
     * @return Objeto Ticket correspondente ao ID.
     */
    public Ticket getById(String id) {
        return ticketController.getById(UUID.fromString(id));
    }

    /**
     * Verifica se o ticket está ativo.
     *
     * @param id ID do ticket.
     * @return {@code true} se o ticket estiver ativo, {@code false} caso contrário.
     */
    public boolean getIsActive(String id) {
        Ticket ticket = ticketController.getById(UUID.fromString(id));
        return ticket.isActive();
    }

    /**
     * Deleta todos os tickets.
     */
    public void deleteAllTickets() {
        ticketController.deleteAll();
    }
}
