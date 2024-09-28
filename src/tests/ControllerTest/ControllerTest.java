package tests.ControllerTest;

import java.util.Date;
import java.util.Calendar;
import java.util.List;

import controllers.Controller;
import models.EventModel;
import models.TicketModel;
import models.UserModel;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

public class ControllerTest {

    @Test
    public void testRegisterEventByAdmin() {
        Controller controller = new Controller();
        UserModel admin = controller.createUser("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.SEPTEMBER, 10);
        Date data = calendar.getTime();

        EventModel event = controller.createEvent(admin, "Show de Rock", "Banda XYZ", data);

        assertNotNull(event);
        assertEquals("Show de Rock", event.getName());
        assertEquals("Banda XYZ", event.getDescription());
        assertEquals(data, event.getDate());
    }

    @Test
    public void testRegisterEventByCommonUser() {
        Controller controller = new Controller();
        UserModel user = controller.createUser("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.SEPTEMBER, 10);
        Date data = calendar.getTime();

        Exception exception = assertThrows(SecurityException.class, () -> {
            controller.createEvent(user, "Peça de Teatro", "Grupo ABC", data);
        });

        assertEquals("Somente administradores podem cadastrar eventos.", exception.getMessage());
    }

    @Test
    public void testComprarIngresso() {
        Controller controller = new Controller();
        UserModel usuario = new UserModel("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.SEPTEMBER, 10);
        Date data = calendar.getTime();

        UserModel admin = controller.createUser("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);
        controller.createEvent(admin, "Show de Rock", "Banda XYZ", data);
        controller.addSeatToEvent("Show de Rock", "A1");

        TicketModel ticket = controller.buyTicket(usuario, "Show de Rock", "A1");

        assertNotNull(ticket);
        assertEquals("Show de Rock", ticket.getEvent().getName());
        assertEquals("A1", ticket.getCode());
        assertTrue(usuario.getTickets().contains(ticket));
    }

    @Test
    public void testCancelarCompra() {
        Controller controller = new Controller();
        UserModel user = new UserModel("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.SEPTEMBER, 30);
        Date data = calendar.getTime();

        UserModel admin = controller.createUser("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);
        controller.createEvent(admin, "Show de Rock", "Banda XYZ", data);
        controller.addSeatToEvent("Show de Rock", "A1");
        TicketModel ticket = controller.buyTicket(user, "Show de Rock", "A1");

        boolean canceled = controller.cancelBuy(user, ticket);

        assertTrue(canceled);
        assertFalse(ticket.isActive());
        assertFalse(user.getTickets().contains(ticket));
    }

    @Test
    public void testListarEventosDisponiveis() {
        Controller controller = new Controller();
        UserModel admin = controller.createUser("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);

        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2025, Calendar.SEPTEMBER, 30);
        Date date1 = calendar1.getTime();

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2025, Calendar.SEPTEMBER, 25);
        Date date2 = calendar2.getTime();

        controller.createEvent(admin, "Show de Rock", "Banda XYZ", date1);
        controller.createEvent(admin, "Peça de Teatro", "Grupo ABC", date2);

        List<EventModel> event = controller.listAvailableEvents();

        assertEquals(2, event.size());
    }

    @Test
    public void testListarIngressosComprados() {
        Controller controller = new Controller();
        UserModel user = new UserModel("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.SEPTEMBER, 10);
        Date data = calendar.getTime();

        UserModel admin = controller.createUser("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);
        controller.createEvent(admin, "Show de Rock", "Banda XYZ", data);
        controller.addSeatToEvent("Show de Rock", "A1");
        controller.buyTicket(user, "Show de Rock", "A1");

        List<TicketModel> tickets = controller.listPurchasedTickets(user);

        assertEquals(1, tickets.size());
    }
}