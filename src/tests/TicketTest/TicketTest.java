package tests.TicketTest;

import models.EventModel;
import models.TicketModel;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import java.util.Calendar;
import java.util.Date;

public class TicketTest {

    @Test
    public void ticketCreateTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.SEPTEMBER, 30);
        Date data = calendar.getTime();

        EventModel event = new EventModel("Show de Rock", "Banda XYZ", data);
        TicketModel ticket = new TicketModel(event, 100.0, "A1");

        assertNotNull(ticket);
        assertEquals(event, ticket.getEvent());
        assertEquals(100.0, ticket.getPrice(), 0.0001);
        assertEquals("A1", ticket.getCode());
        assertTrue(ticket.isActive());
    }

    @Test
    public void ticketCancelTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.SEPTEMBER, 30);
        Date data = calendar.getTime();

        EventModel event = new EventModel("Show de Rock", "Banda XYZ", data);
        TicketModel ticket = new TicketModel(event, 100.0, "A1");

        assertTrue(ticket.cancel());
        assertFalse(ticket.isActive());
    }

    @Test
    public void testCancelTicketPastEvent() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.JANUARY, 10);
        Date data = calendar.getTime();

        EventModel event = new EventModel("Show de Rock", "Banda XYZ", data);
        TicketModel ticket = new TicketModel(event, 100.0, "A1");


        assertFalse(ticket.cancel());
        assertTrue(ticket.isActive());
    }

    @Test
    public void testReactivateTicket() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.SEPTEMBER, 30);
        Date data = calendar.getTime();

        EventModel event = new EventModel("Show de Rock", "Banda XYZ", data);
        TicketModel ticket = new TicketModel(event, 100.0, "A1");

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

        EventModel event = new EventModel("Show de Rock", "Banda XYZ", data);
        TicketModel firstTicket = new TicketModel(event, 100.0, "A1");
        TicketModel secondTicket = new TicketModel(event, 100.0, "A1");

        assertEquals(firstTicket, secondTicket);
        assertEquals(firstTicket.hashCode(), secondTicket.hashCode());
    }
}
