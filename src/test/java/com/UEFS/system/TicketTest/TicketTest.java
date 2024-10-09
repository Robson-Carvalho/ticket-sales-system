package test.java.com.UEFS.system.TicketTest;

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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import java.util.Calendar;
import java.util.Date;

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
        calendar.set(2024, Calendar.SEPTEMBER, 30);
        Date data = calendar.getTime();

        User admin = userController.create("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);

        Event event = eventController.create(admin, "Show de Rock", "Banda XYZ", data);
        Ticket ticket = ticketController.create(event, 100.0, "A1");

        assertNotNull(ticket);
        assertEquals(event.getId(), ticket.getEvent().getId());
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
        Ticket ticket = ticketController.create(event, 100.0, "A1");

        assertTrue(ticket.cancel());
        assertFalse(ticket.isActive());
    }

    @Test
    public void testCancelTicketPastEvent() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.JANUARY, 10);
        Date data = calendar.getTime();

        User admin = userController.create("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);

        Event event = eventController.create(admin, "Show de Rock 2", "Banda XYZ", data);
        Ticket ticket = ticketController.create(event, 100.0, "A1");

        assertFalse(ticket.cancel());
        assertTrue(ticket.isActive());
    }

    @Test
    public void testReactivateTicket() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.SEPTEMBER, 30);
        Date data = calendar.getTime();

        User admin = userController.create("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);

        Event event = eventController.create(admin, "Show de Rock", "Banda XYZ", data);
        Ticket ticket = ticketController.create(event, 100.0, "A1");

        ticket.cancel();
        assertFalse(ticket.isActive());

        ticket.reactive();
        assertTrue(ticket.isActive());
    }

    @Test
    public void duplicateTicketTest() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.SEPTEMBER, 10);
        Date data = calendar.getTime();

        User admin = userController.create("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);

        Event event = eventController.create(admin, "Show de Rock","Banda XYZ", data);
        Ticket firstTicket = ticketController.create(event, 100.0, "A1");
        Ticket secondTicket = ticketController.create(event, 100.0, "A1");

        assertEquals(firstTicket, secondTicket);
        assertEquals(firstTicket.hashCode(), secondTicket.hashCode());
    }
}
