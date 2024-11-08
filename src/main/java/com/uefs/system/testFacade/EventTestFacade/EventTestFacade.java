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

package com.uefs.system.testFacade.EventTestFacade;

import com.uefs.system.controller.CommentController;
import com.uefs.system.controller.EventController;
import com.uefs.system.controller.UserController;
import com.uefs.system.model.Comment;
import com.uefs.system.model.Event;
import com.uefs.system.model.User;
import com.uefs.system.service.EventService;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Facade para facilitar os testes com eventos, incluindo manipulação de assentos e comentários.
 */
public class EventTestFacade {
    private final EventController eventController;
    private final UserController userController;
    private final EventService eventService;
    private final CommentController commentController;

    /**
     * Construtor que inicializa os controladores necessários.
     */
    public EventTestFacade() {
        eventController = new EventController();
        userController = new UserController();
        eventService = new EventService();
        commentController = new CommentController();
    }

    /**
     * Remove um assento de um evento.
     *
     * @param seat Assento a ser removido.
     * @param id ID do evento.
     */
    public void removeSeatByEventId(String seat, String id) {
        Event event = eventController.getById(UUID.fromString(id));
        event.removeSeat(seat);
        eventController.update(event);
    }

    /**
     * Adiciona um assento a um evento.
     *
     * @param seat Assento a ser adicionado.
     * @param id ID do evento.
     */
    public void addSeatByEventId(String seat, String id) {
        Event event = eventController.getById(UUID.fromString(id));
        event.addSeat(seat);
        eventController.update(event);
    }

    /**
     * Cria um evento.
     *
     * @param loginAdmin SignUp do administrador que está criando o evento.
     * @param name Nome do evento.
     * @param description Descrição do evento.
     * @param date Data do evento.
     * @return ID do evento criado.
     */
    public String create(String loginAdmin, String name, String description, Date date) {
        User user = userController.getByLogin(loginAdmin);
        Event event = eventController.create(user, name, description, date);
        return event.getId().toString();
    }

    /**
     * Retorna um evento pelo seu ID.
     *
     * @param id ID do evento.
     * @return Evento correspondente ao ID.
     */
    public Event getById(String id) {
        return eventController.getById(UUID.fromString(id));
    }

    /**
     * Retorna o nome de um evento pelo seu ID.
     *
     * @param id ID do evento.
     * @return Nome do evento.
     */
    public String getNameByEventId(String id) {
        Event event = eventController.getById(UUID.fromString(id));
        return event.getName();
    }

    /**
     * Retorna a lista de assentos de um evento pelo seu ID.
     *
     * @param id ID do evento.
     * @return Lista de assentos.
     */
    public List<String> getSeatsByEventId(String id) {
        return eventController.getById(UUID.fromString(id)).getSeats();
    }

    /**
     * Retorna a descrição de um evento pelo seu ID.
     *
     * @param id ID do evento.
     * @return Descrição do evento.
     */
    public String getDescriptionByEventId(String id) {
        Event event = eventController.getById(UUID.fromString(id));
        return event.getDescription();
    }

    /**
     * Retorna o ano da data de um evento pelo seu ID.
     *
     * @param id ID do evento.
     * @return Ano da data do evento.
     */
    public int getYearByEventId(String id) {
        Event event = eventController.getById(UUID.fromString(id));
        return event.getDate().getYear();
    }

    /**
     * Retorna o mês da data de um evento pelo seu ID.
     *
     * @param id ID do evento.
     * @return Mês da data do evento.
     */
    public int getMonthByEventId(String id) {
        Event event = eventController.getById(UUID.fromString(id));
        return event.getDate().getMonth();
    }

    /**
     * Retorna o dia da data de um evento pelo seu ID.
     *
     * @param id ID do evento.
     * @return Dia da data do evento.
     */
    public int getDayByEventId(String id) {
        Event event = eventController.getById(UUID.fromString(id));
        return event.getDate().getDay();
    }

    /**
     * Retorna se o evento está ativo pelo seu ID.
     *
     * @param id ID do evento.
     * @return Status de atividade do evento.
     */
    public boolean getIsActiveByEventId(String id) {
        return eventController.getById(UUID.fromString(id)).isActive();
    }

    /**
     * Adiciona um evento no banco de dados com uma data passada.
     *
     * @param name Nome do evento.
     * @param description Descrição do evento.
     * @param date Data do evento.
     * @return ID do evento criado.
     */
    public String addEventInDataBaseWithPastDate(String name, String description, Date date) {
        Event event = new Event(name, description, date);
        Event _event = eventService.create(event);
        return _event.getId().toString();
    }

    /**
     * Retorna a quantidade de comentários associados a um evento pelo seu ID.
     *
     * @param id ID do evento.
     * @return Quantidade de comentários.
     */
    public int getCommentQuantityByEventId(String id) {
        List<Comment> comments = commentController.getCommentsByEventId(UUID.fromString(id));
        return comments.size();
    }

    /**
     * Deleta todos os eventos.
     */
    public void deleteAllEvents() {
        eventController.deleteAll();
    }
}
