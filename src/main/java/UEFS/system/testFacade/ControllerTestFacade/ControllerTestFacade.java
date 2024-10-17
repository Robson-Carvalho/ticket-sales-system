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

package main.java.UEFS.system.testFacade.ControllerTestFacade;

import main.java.UEFS.system.testFacade.CardTestFacade.CardTestFacade;
import main.java.UEFS.system.testFacade.CommentTestFacade.CommentTestFacade;
import main.java.UEFS.system.testFacade.EventTestFacade.EventTestFacade;
import main.java.UEFS.system.testFacade.TicketTestFacade.TicketTestFacade;
import main.java.UEFS.system.testFacade.UserTestFacade.UserTestFacade;

import java.util.Date;

public class ControllerTestFacade {
    private final UserTestFacade userTestFacade;
    private final EventTestFacade eventTestFacade;
    private final CardTestFacade cardTestFacade;
    private final TicketTestFacade ticketTestFacade;
    private final CommentTestFacade commentTestFacade;

    public ControllerTestFacade() {
        this.userTestFacade = new UserTestFacade();
        this.eventTestFacade = new EventTestFacade();
        this.cardTestFacade = new CardTestFacade();
        this.ticketTestFacade = new TicketTestFacade();
        this.commentTestFacade = new CommentTestFacade();
    }

    public void deleteDataBase(){
        this.userTestFacade.deleteAllUsers();
        this.eventTestFacade.deleteAllEvents();
        this.cardTestFacade.deleteAllCards();
        this.ticketTestFacade.deleteAllTickets();
        this.commentTestFacade.deleteAllComments();
    }

    public void createUser(String login, String password, String name, String cpf, String email, boolean isAdmin){
        userTestFacade.create(login, password, name, cpf, email, isAdmin);
    }

    public String createEvent(String login, String name, String description, Date date){
        return eventTestFacade.create(login, name, description, date);
    }

    public String createTicket(String eventId, Double price, String seat){
        return ticketTestFacade.create(eventId, price, seat);
    }

    public void addSeatInEventById(String seat, String id){
        eventTestFacade.addSeatByEventId(seat, id);
    }

    public String getUserIdByEmail(String email){
        return userTestFacade.getUserIdByEmail(email);
    }

    public void buyTicket(String commonUserId, String eveentId, String seat){

    }


}
