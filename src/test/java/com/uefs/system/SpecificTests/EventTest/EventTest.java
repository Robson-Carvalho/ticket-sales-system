package com.uefs.system.SpecificTests.EventTest;

import java.util.Date;
import java.util.List;
import java.util.Calendar;

import com.uefs.system.model.Event;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class EventTest {
    @Test
    public void eventCreateTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.SEPTEMBER, 10);
        Date data = calendar.getTime();

        Event event = new Event("Show de Rock", "Banda XYZ", data,10.2);

        assertNotNull(event);
        assertEquals("Show de Rock", event.getName());
        assertEquals("Banda XYZ", event.getDescription());
        assertEquals(data, event.getDate());
    }

    @Test
    public void addSeatTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.SEPTEMBER, 10);
        Date data = calendar.getTime();

        Event event = new Event("Show de Rock", "Banda XYZ", data,10.2);
        event.addSeat("A1");

        List<String> seats = event.getSeats();
        assertTrue(seats.contains("A1"));
    }

    @Test
    public void removeSeatTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.SEPTEMBER, 10);
        Date data = calendar.getTime();

        Event event = new Event("Show de Rock", "Banda XYZ", data,10.2);
        event.addSeat("A1");
        event.removeSeat("A1");

        List<String> seats = event.getSeats();
        assertFalse(seats.contains("A1"));
    }

    @Test
    public void activeEventTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.SEPTEMBER, 1);
        Date data = calendar.getTime();

        Event event = new Event("Show de Rock", "Banda XYZ", data,10.2);

        assertTrue(event.isActive());
    }

    @Test
    public void inactiveEventTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.JANUARY, 10);
        Date data = calendar.getTime();

        Event event = new Event("Show de Rock", "Banda XYZ", data,10.2);

        assertFalse(event.isActive());
    }
}