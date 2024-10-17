package test.java.com.UEFS.system.SpecificTests.TicketTest;

import main.java.UEFS.system.controller.EventController;
import main.java.UEFS.system.controller.TicketController;
import main.java.UEFS.system.controller.UserController;
import java.lang.Exception;
import main.java.UEFS.system.model.Event;
import main.java.UEFS.system.model.Ticket;
import main.java.UEFS.system.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class TicketTest {
    private EventController eventController;
    private TicketController ticketController;
    private UserController userController;

    @Before
    public void setUp() {
        eventController = new EventController();
        ticketController = new TicketController();
        userController =  new UserController();
    }

    @After
    public void tearDown() {
        eventController.deleteAll();
        ticketController.deleteAll();
        userController.deleteAll();
    }

    @Test
    public void ticketCreateTest() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.SEPTEMBER, 30);
        Date data = calendar.getTime();

        User admin = userController.create("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);

        Event event = eventController.create(admin, "Show de Rock", "Banda XYZ", data);
        Ticket ticket = ticketController.create(event.getId(), 100.0, "A1");

        assertNotNull(ticket);
        assertEquals(event.getId(), ticket.getEventId());
        assertEquals(100.0, ticket.getPrice(), 0.0001);
        assertEquals("A1", ticket.getCode());
        assertTrue(ticket.isActive());
    }

    @Test
    public void ticketCancelTest() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.SEPTEMBER, 30);
        Date data = calendar.getTime();

        User admin = userController.create("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);

        Event event = eventController.create(admin , "Show de Rock 1", "Banda XYZ", data);
        Ticket ticket = ticketController.create(event.getId(), 100.0, "A1");

        assertTrue(ticketController.cancelById(ticket.getId()));
        assertFalse(ticket.isActive());
    }

    @Test(expected = SecurityException.class)
    public void testCancelTicketPastEvent() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.JANUARY, 10);
        Date data = calendar.getTime();

        User admin = userController.create("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);

        Event event = eventController.create(admin, "Show de Rock 2", "Banda XYZ", data);
        Ticket ticket = ticketController.create(event.getId(), 100.0, "A1");

        assertFalse(ticketController.cancelById(ticket.getId()));
        assertTrue(ticket.isActive());
    }

    @Test
    public void testReactivateTicket() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.SEPTEMBER, 30);
        Date data = calendar.getTime();

        User admin = userController.create("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);

        Event event = eventController.create(admin, "Show de Rock", "Banda XYZ", data);
        Ticket ticket = ticketController.create(event.getId(), 100.0, "A1");

        ticketController.cancelById(ticket.getId());
        assertFalse(ticket.isActive());

        ticketController.reactiveById(ticket.getId());
        assertTrue(ticket.isActive());
    }

    @Test
    public void duplicateTicketTest() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.SEPTEMBER, 10);
        Date data = calendar.getTime();

        User admin = userController.create("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);

        Event event = eventController.create(admin, "Show de Rock","Banda XYZ", data);
        ticketController.create(event.getId(), 100.0, "A1");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
             ticketController.create(event.getId(), 100.0, "A1");
        });

        assertEquals("Não é possível cadastrar o mesmo assento duas vezes para um único evento.", exception.getMessage());
    }
}
