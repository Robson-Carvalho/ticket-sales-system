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

package main.java.com.UEFS.system.Services;

import main.java.com.UEFS.system.Models.Event;
import main.java.com.UEFS.system.Models.Ticket;
import main.java.com.UEFS.system.Repositories.EventRepository;
import main.java.com.UEFS.system.Repositories.TicketRepository;
import main.java.com.UEFS.system.Interfaces.IService;

import java.util.List;
import java.util.UUID;

/**
 * Classe de serviço para gerenciar operações relacionadas a ingressos.
 * Implementa a interface IService para a entidade Ticket.
 */
public class TicketService implements IService<Ticket> {
    private final TicketRepository ticketRepository;
    private final EventRepository eventRepository;

    /**
     * Construtor que inicializa os repositórios de ingresso e evento.
     */
    public TicketService() {
        this.ticketRepository = new TicketRepository();
        this.eventRepository = new EventRepository();
    }

    /**
     * Cria um novo ingresso e o salva no repositório.
     *
     * @param ticket Ingresso a ser criado.
     * @return O ingresso criado.
     */
    @Override
    public Ticket create(Ticket ticket) {
        ticketRepository.save(ticket);
        return ticket;
    }

    /**
     * Retorna todos os ingressos salvos no repositório.
     *
     * @return Lista de todos os ingressos.
     */
    @Override
    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    /**
     * Busca um ingresso pelo seu ID.
     *
     * @param id ID do ingresso a ser buscado.
     * @return Ingresso encontrado ou null se não existir.
     */
    @Override
    public Ticket getById(UUID id) {
        return ticketRepository.findById(id);
    }

    /**
     * Cancela um ingresso se o evento relacionado estiver ativo.
     *
     * @param id ID do ingresso a ser cancelado.
     * @return true se o ingresso foi cancelado, false se não foi possível.
     */
    public boolean cancelById(UUID id) {
        Ticket ticket = this.getById(id);
        Event event = this.eventRepository.findById(ticket.getEventId());

        if (event.isActive()) {
            ticket.setStatus(false);
            this.update(ticket);
            return true;
        }
        return false;
    }

    /**
     * Reativa um ingresso se o evento relacionado estiver ativo.
     *
     * @param id ID do ingresso a ser reativado.
     */
    public void reactiveById(UUID id) {
        Ticket ticket = this.getById(id);
        Event event = this.eventRepository.findById(ticket.getEventId());

        if (event.isActive()) {
            ticket.setStatus(true);
            this.update(ticket);
        }
    }

    /**
     * Atualiza um ingresso existente.
     *
     * @param ticket Ingresso a ser atualizado.
     */
    @Override
    public void update(Ticket ticket) {
        ticketRepository.update(ticket);
    }

    /**
     * Deleta um ingresso pelo seu ID.
     *
     * @param id ID do ingresso a ser deletado.
     */
    @Override
    public void delete(UUID id) {
        ticketRepository.delete(id);
    }

    /**
     * Deleta todos os ingressos do repositório.
     */
    @Override
    public void deleteAll() {
        ticketRepository.deleteAll();
    }
}
