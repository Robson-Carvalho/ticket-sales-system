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

package main.java.UEFS.system.controller;

import main.java.UEFS.system.model.Event;
import main.java.UEFS.system.model.User;
import main.java.UEFS.system.service.EventService;

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

    public Event create(User admin, String name, String description, Date date){
        Event event = new Event(name, description, date);

        User _admin = userController.getById(admin.getId());

        Date today = new Date();

        if(_admin == null || !_admin.isAdmin()) {
            throw new SecurityException("Somente administradores podem cadastrar eventos.");
        }

        if(today.after(date)){
            throw new SecurityException("Não é possível criar evento com data anterior a atual.");
        }

        return eventService.create(event);
    }

    public Event getById(UUID id){return eventService.getById(id);}

    public List<Event> getAll(){return eventService.getAll();}

    public void update(Event event) {eventService.update(event);}

    public void delete(UUID id) {eventService.delete(id);}

    public void deleteAll() {eventService.deleteAll();}
}
