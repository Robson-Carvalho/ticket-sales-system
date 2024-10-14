package test.java.com.UEFS.system.SpecificTests.UserTest;

import main.java.UEFS.system.controller.EventController;
import main.java.UEFS.system.controller.TicketController;
import main.java.UEFS.system.controller.UserController;
import java.lang.Exception;
import java.util.List;

import main.java.UEFS.system.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
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
    public void registerUserTest() {
        User user = new User("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);

        assertNotNull(user);
        assertEquals("johndoe", user.getLogin());
        assertEquals("John Doe", user.getName());
        assertEquals("12345678901", user.getCpf());
        assertEquals("john.doe@example.com", user.getEmail());
        assertFalse(user.isAdmin());
    }

    @Test
    public void registerAdminUserTest() {
        User admin = new User("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);

        assertNotNull(admin);
        assertEquals("admin", admin.getLogin());
        assertEquals("Admin User", admin.getName());
        assertEquals("00000000000", admin.getCpf());
        assertEquals("admin@example.com", admin.getEmail());
        assertTrue(admin.isAdmin());
    }

    @Test
    public void loginTest() {
        User user = new User("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);

        assertTrue(user.login("johndoe", "senha123"));
        assertFalse(user.login("johndoe", "senhaErrada"));
    }

    @Test
    public void passwordUpdateTest() {
        User user = new User("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);

        user.setPassword("novaSenha123");
        assertTrue(user.login("johndoe", "novaSenha123"));
        assertFalse(user.login("johndoe", "senha123"));
    }

    @Test
    public void userDataTest() {
        User user = new User("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);

        user.setName("Jonathan Doe");
        user.setCpf("10987654321");
        user.setEmail("jon.doe@example.com");

        assertEquals("Jonathan Doe", user.getName());
        assertEquals("10987654321", user.getCpf());
        assertEquals("jon.doe@example.com", user.getEmail());
    }
}
