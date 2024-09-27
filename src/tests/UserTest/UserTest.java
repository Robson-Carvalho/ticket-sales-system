package tests.UserTest;

import models.UserModel;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class UserTest {
    @Test
    public void registerUserTest() {
        UserModel user = new UserModel("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);

        assertNotNull(user);
        assertEquals("johndoe", user.getLogin());
        assertEquals("John Doe", user.getName());
        assertEquals("12345678901", user.getCpf());
        assertEquals("john.doe@example.com", user.getEmail());
        assertFalse(user.isAdmin());
    }

    @Test
    public void registerAdminUserTest() {
        UserModel admin = new UserModel("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);

        assertNotNull(admin);
        assertEquals("admin", admin.getLogin());
        assertEquals("Admin User", admin.getName());
        assertEquals("00000000000", admin.getCpf());
        assertEquals("admin@example.com", admin.getEmail());
        assertTrue(admin.isAdmin());
    }

    @Test
    public void loginTest() {
        UserModel user = new UserModel("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);

        assertTrue(user.login("johndoe", "senha123"));
        assertFalse(user.login("johndoe", "senhaErrada"));
    }

    @Test
    public void passwordUpdateTest() {
        UserModel user = new UserModel("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);

        user.setPassword("novaSenha123");
        assertTrue(user.login("johndoe", "novaSenha123"));
        assertFalse(user.login("johndoe", "senha123"));
    }

    @Test
    public void userDataTest() {
        UserModel user = new UserModel("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);

        user.setName("Jonathan Doe");
        user.setCpf("10987654321");
        user.setEmail("jon.doe@example.com");

        assertEquals("Jonathan Doe", user.getName());
        assertEquals("10987654321", user.getCpf());
        assertEquals("jon.doe@example.com", user.getEmail());
    }

    @Test
    public void duplicateUserTest() {
        UserModel firstUser = new UserModel("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);
        UserModel secondUser = new UserModel("johndoe", "senha456", "John Doe", "12345678901", "john.doe@example.com", false);

        assertEquals(firstUser, secondUser);
    }
}
