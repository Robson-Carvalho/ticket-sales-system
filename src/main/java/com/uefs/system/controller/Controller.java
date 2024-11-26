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

import com.uefs.system.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Controlador principal do sistema que gerencia as interações entre usuários,
 * eventos, ingressos e outros controladores específicos.
 */
public class Controller {
    private final UserController userController;
    private final EventController eventController;
    private final TicketController ticketController;
    private final CardController cardController;
    private final TransactionController transactionController;
    private final MailController mailBoxController;
    private final CommentController commentController;

    /**
     * Construtor do Controller.
     * Inicializa todos os controladores necessários para a operação do sistema.
     */
    public Controller() {
        this.userController = new UserController();
        this.eventController = new EventController();
        this.ticketController = new TicketController();
        this.cardController = new CardController();
        this.transactionController = new TransactionController();
        this.mailBoxController = new MailController();
        this.commentController = new CommentController();
    }

    /**
     * Obtém um evento pelo nome.
     *
     * @param eventName Nome do evento a ser buscado.
     * @return O evento correspondente ao nome fornecido ou null se não encontrado.
     */
    private Event getEventByName(String eventName) {
        List<Event> events = eventController.getAll();

        for (Event event : events) {
            if (event.getName().equals(eventName)) {
                return event;
            }
        }

        return null;
    }

    /**
     * Obtém um evento pelo seu ID.
     *
     * @param eventId ID do evento a ser obtido.
     * @return O evento correspondente ao ID fornecido.
     */
    public Event getEventById(UUID eventId) {
        return eventController.getById(eventId);
    }

    /**
     * Cria um novo usuário.
     *
     * @param login    Nome de login do usuário.
     * @param password Senha do usuário.
     * @param name     Nome completo do usuário.
     * @param cpf      CPF do usuário.
     * @param email    Email do usuário.
     * @param isAdmin  Indica se o usuário é um administrador.
     * @return O usuário criado.
     * @throws Exception Se houver um erro durante a criação do usuário.
     */
    public User createUser(String login, String password, String name, String cpf, String email, Boolean isAdmin) throws Exception {
        return userController.create(login, password, name, cpf, email, isAdmin);
    }

    /**
     * Cria um novo evento.
     *
     * @param admin       O usuário que está criando o evento.
     * @param name        Nome do evento.
     * @param description Descrição do evento.
     * @param date        Data do evento.
     * @param price       O preço do evento.
     * @return O evento criado.
     * @throws SecurityException Se o usuário não for um administrador.
     */
    public Event createEvent(User admin, String name, String description, Date date, Double price) {
        if (admin.isAdmin()) {
            return eventController.create(admin, name, description, date, price);
        }

        throw new SecurityException("Somente administradores podem cadastrar eventos.");
    }

    /**
     * Adiciona um assento a um evento existente.
     *
     * @param eventName Nome do evento ao qual o assento será adicionado.
     * @param seat      Identificador do assento a ser adicionado.
     */
    public void addSeatToEvent(String eventName, String seat) {
        Event event = getEventByName(eventName);

        if (event != null) {
            event.addSeat(seat);
            eventController.update(event);
        }
    }

    /**
     * Compra um ingresso para um evento específico.
     *
     * @param user     O usuário que está comprando o ingresso.
     * @param eventName Nome do evento para o qual o ingresso está sendo comprado.
     * @param seat      Assento associado ao ingresso.
     * @return O ingresso comprado ou null se a compra não foi realizada.
     * @throws Exception Se houver um erro durante a compra do ingresso.
     */
    public Ticket buyTicket(User user, String eventName, String seat) throws Exception {
        if (userController.getById(user.getId()) == null) {
            return null;
        }

        Event event = getEventByName(eventName);

        if (event != null) {
            Ticket ticket = ticketController.create(event.getId(),seat);
            user.addTicket(ticket);
            userController.update(user);
            return ticket;
        }

        return null;
    }

    public void purchase(User user, UUID eventId, String seat) throws Exception {
        try{
            transactionController.create(user.getEmail(), eventId, seat);

            Event event = getEventById(eventId);

            Ticket ticket = ticketController.create(eventId, event.getPrice() , seat);

            User u = userController.getById(user.getId());

            u.addTicket(ticket);

            userController.update(u);
        }catch (Exception e) {
            System.out.println(e);
        }
    }

    public void purchase(User user, UUID eventId, String seat, String cardNumber) throws Exception {
        try{
            Card card = cardController.getCardByNumber(cardNumber);

            transactionController.create(user.getEmail(), eventId, card.getId(), seat);

            Event event = getEventById(eventId);

            Ticket ticket = ticketController.create(eventId, event.getPrice(), seat);

            User u = userController.getById(user.getId());

            u.addTicket(ticket);

            userController.update(u);
        }catch (Exception e){
            System.out.println(e);
        }
    }


    /**
     * Cancela a compra de um ingresso.
     *
     * @param _user  O usuário que deseja cancelar a compra.
     * @param _ticket O ingresso a ser cancelado.
     * @return true se a compra foi cancelada com sucesso, false caso contrário.
     * @throws Exception Se houver um erro durante o cancelamento.
     */
    public Boolean cancelBuy(User _user, Ticket _ticket) throws Exception {
        User user = userController.getById(_user.getId());
        Ticket ticket = ticketController.getById(_ticket.getId());

        if (user != null && ticket != null) {
            _user.removeTicket(_ticket);
            userController.update(_user);
            ticketController.cancelById(_ticket.getId());
            ticketController.update(_ticket);
            return true;
        }

        return false;
    }

    /**
     * Lista todos os eventos disponíveis.
     *
     * @return Lista de eventos disponíveis.
     */
    public List<Event> listAvailableEvents() {
        return eventController.getAll();
    }

    /**
     * Lista todos os ingressos comprados por um usuário específico.
     *
     * @param user O usuário cujos ingressos comprados devem ser listados.
     * @return Lista de ingressos comprados pelo usuário.
     */
    public List<Ticket> listPurchasedTickets(User user) {
        List<Ticket> tickets = new ArrayList<Ticket>();

        User _user = userController.getById(user.getId());
        List<UUID> ticketIDs = _user.getTickets();

        for (UUID id : ticketIDs) {
            Ticket t = ticketController.getById(id);
            tickets.add(t);
        }

        return tickets;
    }

    /**
     * Deleta todos os dados do banco de dados.
     * Utilizado para limpeza completa dos dados do sistema.
     */
    public void deleteDB() {
        userController.deleteAll();
        eventController.deleteAll();
        ticketController.deleteAll();
        mailBoxController.deleteAll();
        transactionController.deleteAll();
        cardController.deleteAll();
        commentController.deleteAll();
    }
}
