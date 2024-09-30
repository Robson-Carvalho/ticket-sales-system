package tests.TicketTest;

import controllers.Controller;
import controllers.EventController;
import controllers.TicketController;
import entitys.Event;
import entitys.Ticket;
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
    private  EventController eventController;
    private TicketController ticketController;

    @Before
    public void setUp() {
        eventController = new EventController();
        ticketController = new TicketController();
    }

    @After
    public void tearDown() {
        eventController.deleteAll();
        ticketController.deleteAll();
    }

    @Test
    public void ticketCreateTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.SEPTEMBER, 30);
        Date data = calendar.getTime();

        Event event = eventController.create("Show de Rock", "Banda XYZ", data);
        Ticket ticket = ticketController.create(event, 100.0, "A1");

        assertNotNull(ticket);
        assertEquals(event.getId(), ticket.getEvent().getId());
        assertEquals(100.0, ticket.getPrice(), 0.0001);
        assertEquals("A1", ticket.getCode());
        assertTrue(ticket.isActive());
    }

    @Test
    public void ticketCancelTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.SEPTEMBER, 30);
        Date data = calendar.getTime();

        Event event = eventController.create("Show de Rock 1", "Banda XYZ", data);
        Ticket ticket = ticketController.create(event, 100.0, "A1");

        assertTrue(ticket.cancel());
        assertFalse(ticket.isActive());
    }

    @Test
    public void testCancelTicketPastEvent() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.JANUARY, 10);
        Date data = calendar.getTime();

        Event event = eventController.create("Show de Rock 2", "Banda XYZ", data);
        Ticket ticket = ticketController.create(event, 100.0, "A1");

        assertFalse(ticket.cancel());
        assertTrue(ticket.isActive());
    }

    @Test
    public void testReactivateTicket() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.SEPTEMBER, 30);
        Date data = calendar.getTime();

        Event event = eventController.create("Show de Rock", "Banda XYZ", data);
        Ticket ticket = ticketController.create(event, 100.0, "A1");

        ticket.cancel();
        assertFalse(ticket.isActive());

        ticket.reactive();
        assertTrue(ticket.isActive());
    }

    @Test
    public void duplicateTicketTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.SEPTEMBER, 10);
        Date data = calendar.getTime();

        Event event = eventController.create("Show de Rock", "Banda XYZ", data);
        Ticket firstTicket = ticketController.create(event, 100.0, "A1");
        Ticket secondTicket = ticketController.create(event, 100.0, "A1");

        assertEquals(firstTicket, secondTicket);
        assertEquals(firstTicket.hashCode(), secondTicket.hashCode());
    }
}
