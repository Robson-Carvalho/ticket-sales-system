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
import com.uefs.system.model.User;
import com.uefs.system.service.EventService;
import com.uefs.system.service.UserService;


import java.util.Date;
import java.util.List;
import java.util.UUID;

public class EventController {
    private final EventService eventService;
    private final UserController userController;

    public EventController() {
        this.eventService = new EventService();
        this.userController = new UserController();
    }

    /**
     * Cria um novo evento, se o usuário fornecido for um administrador e a data for válida.
     *
     * @param admin       O usuário que está criando o evento, deve ser um administrador.
     * @param name        O nome do evento.
     * @param description A descrição do evento.
     * @param date        A data do evento.
     * @return O evento criado.
     * @throws SecurityException Se o usuário não for um administrador ou se a data for anterior à atual.
     */
    public Event create(User admin, String name, String description, Date date) {
        Event event = new Event(name, description, date);

        User _admin = userController.getById(admin.getId());

        Date today = new Date();

        if (_admin == null || !_admin.isAdmin()) {
            throw new SecurityException("Somente administradores podem cadastrar eventos.");
        }

        if (today.after(date)) {
            throw new SecurityException("Não é possível criar evento com data anterior a atual.");
        }

        return eventService.create(event);
    }

    /**
     * Obtém um evento pelo seu ID.
     *
     * @param id O UUID do evento a ser buscado.
     * @return O evento correspondente ao ID fornecido, ou null se não encontrado.
     */
    public Event getById(UUID id) {
        return eventService.getById(id);
    }

    /**
     * Obtém todos os eventos disponíveis.
     *
     * @return Uma lista de eventos.
     */
    public List<Event> getAll() {
        return eventService.getAll();
    }

    /**
     * Atualiza as informações de um evento existente.
     *
     * @param event O evento a ser atualizado.
     */
    public void update(Event event) {
        eventService.update(event);
    }

    /**
     * Deleta um evento pelo seu ID.
     *
     * @param id O UUID do evento a ser deletado.
     */
    public void delete(UUID id) {
        eventService.delete(id);
    }


    /**
     * Deleta todos os eventos.
     */
    public void deleteAll() {
        eventService.deleteAll();
    }
}
