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

package main.java.UEFS.system.testFacade.EventTestFacade;

import main.java.UEFS.system.controller.CommentController;
import main.java.UEFS.system.controller.EventController;
import main.java.UEFS.system.controller.UserController;
import main.java.UEFS.system.model.Comment;
import main.java.UEFS.system.model.Event;
import main.java.UEFS.system.model.User;
import main.java.UEFS.system.service.CommentService;
import main.java.UEFS.system.service.EventService;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class EventTestFacade {
    private final EventController eventController;
    private final UserController userController ;
    private final EventService eventService;
    private final CommentController commentController;

    public EventTestFacade() {
        eventController = new EventController();
        userController = new UserController();
        eventService = new EventService();
        commentController = new CommentController();
    }

    public void removeSeatByEventId(String seat, String id){
        Event event = eventController.getById(UUID.fromString(id));
        event.removeSeat(seat);
        eventController.update(event);
    }

    public void addSeatByEventId(String seat, String id){
        Event event = eventController.getById(UUID.fromString(id));
        event.addSeat(seat);
        eventController.update(event);
    }

    public String create(String loginAdmin, String name, String description, Date date){
        User user = userController.getByLogin(loginAdmin);
        Event event = eventController.create(user, name, description, date);
        return event.getId().toString();
    }

    public Event getById(String id){
        return eventController.getById(UUID.fromString(id));
    }

    public String getNameByEventId(String id){
        Event event = eventController.getById(UUID.fromString(id));
        return event.getName();
    }

    public List<String> getSeatsByEventId(String id){
        return eventController.getById(UUID.fromString(id)).getSeats();
    }

    public String getDescriptionByEventId(String id){
        Event event = eventController.getById(UUID.fromString(id));
        return event.getDescription();
    }

    public int getYearByEventId(String id){
        Event event = eventController.getById(UUID.fromString(id));
        return event.getDate().getYear();
    }

    public int getMonthByEventId(String id){
        Event event = eventController.getById(UUID.fromString(id));
        return event.getDate().getMonth();
    }

    public int getDayByEventId(String id){
        Event event = eventController.getById(UUID.fromString(id));
        return event.getDate().getDay();
    }

    public boolean getIsActiveByEventId(String id){
        return eventController.getById(UUID.fromString(id)).isActive();
    }

    public String addEventInDataBaseWithPastDate(String name, String description, Date date){
        Event event = new Event(name, description, date);
        Event _event = eventService.create(event);
        return _event.getId().toString();
    }

    public int getCommentQuantityByEventId(String id){
        List<Comment> comments = commentController.getCommentsByEventId(UUID.fromString(id));
        return comments.size();

    }

    public void deleteAllEvents(){
        eventController.deleteAll();
    }
}
