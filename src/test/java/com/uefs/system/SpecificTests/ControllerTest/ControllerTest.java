package com.uefs.system.SpecificTests.ControllerTest;

import java.util.Date;
import java.util.Calendar;
import java.util.List;

import com.uefs.system.controller.Controller;
import java.lang.Exception;
import java.util.UUID;

import com.uefs.system.model.Event;
import com.uefs.system.model.Ticket;
import com.uefs.system.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

public class ControllerTest {
    private Controller controller;

    @Before
    public void setUp() {
        controller = new Controller();
    }

    @After
    public void tearDown() {
        controller.deleteDB();
    }

    @Test
    public void testRegisterEventByAdmin() throws Exception {
        User admin = controller.createUser("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.SEPTEMBER, 10);
        Date data = calendar.getTime();

        Event event = controller.createEvent(admin, "Show de Rock", "Banda XYZ", data);

        assertNotNull(event);
        assertEquals("Show de Rock", event.getName());
        assertEquals("Banda XYZ", event.getDescription());
        assertEquals(data, event.getDate());
    }

    @Test
    public void testRegisterEventByCommonUser() throws Exception {
        User user = controller.createUser("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.SEPTEMBER, 10);
        Date data = calendar.getTime();

        Exception exception = assertThrows(SecurityException.class, () -> {
            controller.createEvent(user, "Peça de Teatro", "Grupo ABC", data);
        });

        assertEquals("Somente administradores podem cadastrar eventos.", exception.getMessage());
    }

    @Test
    public void buyTicketTest() throws Exception {
        User user = controller.createUser("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.SEPTEMBER, 10);
        Date data = calendar.getTime();

        User admin = controller.createUser("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);
        controller.createEvent(admin, "Show de Rock 1", "Banda XYZ", data);
        controller.addSeatToEvent("Show de Rock 1", "A1");

        Ticket ticket = controller.buyTicket(user, "Show de Rock 1", "A1");

        UUID eventId = ticket.getEventId();

        assertNotNull(ticket);
        assertEquals("Show de Rock 1", controller.getEventById(eventId).getName());
        assertEquals("A1", ticket.getCode());
        assertTrue(user.getTickets().contains(ticket.getId()));
    }

    @Test
    public void cancelTicketTest() throws Exception {
        User user = controller.createUser("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.SEPTEMBER, 30);
        Date data = calendar.getTime();

        User admin = controller.createUser("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);
        controller.createEvent(admin, "Show de Rock", "Banda XYZ", data);
        controller.addSeatToEvent("Show de Rock", "A1");
        Ticket ticket = controller.buyTicket(user, "Show de Rock", "A1");

        boolean canceled = controller.cancelBuy(user, ticket);

        assertTrue(canceled);
        assertFalse(ticket.isActive());
        assertFalse(user.getTickets().contains(ticket.getId()));
    }

    @Test
    public void testListAvailableEvents() throws Exception {
        User admin = controller.createUser("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);

        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2025, Calendar.SEPTEMBER, 30);
        Date date1 = calendar1.getTime();

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2025, Calendar.SEPTEMBER, 25);
        Date date2 = calendar2.getTime();

        controller.createEvent(admin, "Show de Rock", "Banda XYZ", date1);
        controller.createEvent(admin, "Peça de Teatro", "Grupo ABC", date2);

        List<Event> event = controller.listAvailableEvents();

        assertEquals(2, event.size());
    }

    @Test
    public void testListPurchasedTickets() throws Exception {
        User user = controller.createUser("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.SEPTEMBER, 10);
        Date data = calendar.getTime();

        User admin = controller.createUser("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);
        controller.createEvent(admin, "Show de Rock", "Banda XYZ", data);
        controller.addSeatToEvent("Show de Rock", "A1");
        controller.buyTicket(user, "Show de Rock", "A1");

        List<Ticket> tickets = controller.listPurchasedTickets(user);

        assertEquals(1, tickets.size());
    }
}