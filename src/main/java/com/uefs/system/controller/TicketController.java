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

import com.uefs.system.model.Event;
import com.uefs.system.model.Ticket;
import com.uefs.system.service.EventService;
import com.uefs.system.service.TicketService;

import java.util.List;
import java.util.UUID;

public class TicketController {
    private final TicketService ticketService;
    private final EventService eventService;

    public TicketController() {
        this.ticketService = new TicketService();
        this.eventService = new EventService();
    }

    /**
     * Cria um novo ingresso para um evento com um assento específico.
     *
     * @param eventId O UUID do evento associado ao ingresso.
     * @param seat    O assento associado ao ingresso.
     * @return O ingresso criado.
     */
    public Ticket create(UUID eventId, String seat) {
        Ticket ticket = new Ticket(eventId, seat);
        ticketService.create(ticket);
        return ticket;
    }

    /**
     * Cria um novo ingresso para um evento, especificando preço e assento.
     *
     * @param eventId O UUID do evento associado ao ingresso.
     * @param price   O preço do ingresso.
     * @param seat    O assento associado ao ingresso.
     * @return O ingresso criado.
     * @throws IllegalArgumentException Se o assento já estiver cadastrado para o evento.
     */
    public Ticket create(UUID eventId, Double price, String seat) {
        Ticket ticket = new Ticket(eventId, price, seat);
        ticketService.create(ticket);

        /*
        Event _event = eventService.getById(eventId);

        if (_event.getSeats().contains(seat)) {
            throw new IllegalArgumentException("Não é possível cadastrar o mesmo assento duas vezes para um único evento.");
        }

        _event.addSeat(seat);
        eventService.update(_event);
         */

        return ticket;
    }

    /**
     * Obtém um ingresso pelo ID.
     *
     * @param id O UUID do ingresso a ser buscado.
     * @return O ingresso correspondente ao ID.
     */
    public Ticket getById(UUID id) {
        return this.ticketService.getById(id);
    }

    /**
     * Cancela um ingresso pelo ID.
     *
     * @param id O UUID do ingresso a ser cancelado.
     * @return Verdadeiro se o cancelamento foi bem-sucedido, falso caso contrário.
     */
    public Boolean cancelById(UUID id) {
        return ticketService.cancelById(id);
    }

    /**
     * Reativa um ingresso pelo ID.
     *
     * @param id O UUID do ingresso a ser reativado.
     */
    public void reactiveById(UUID id) {
        ticketService.reactiveById(id);
    }

    /**
     * Obtém todos os ingressos.
     *
     * @return Uma lista de todos os ingressos.
     */
    public List<Ticket> getAll() {
        return ticketService.getAll();
    }

    /**
     * Atualiza um ingresso existente.
     *
     * @param ticket O ingresso a ser atualizado.
     */
    public void update(Ticket ticket) {
        ticketService.update(ticket);
    }

    /**
     * Deleta um ingresso pelo ID.
     *
     * @param id O UUID do ingresso a ser deletado.
     */
    public void delete(UUID id) {
        ticketService.delete(id);
    }

    /**
     * Deleta todos os ingressos.
     */
    public void deleteAll() {
        ticketService.deleteAll();
    }
}
