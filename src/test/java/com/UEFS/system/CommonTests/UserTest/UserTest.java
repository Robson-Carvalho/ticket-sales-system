package test.java.com.UEFS.system.CommonTests.UserTest;

import main.java.UEFS.system.testFacade.UserTestFacade.UserTestFacade;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class UserTest {
    private UserTestFacade userFacade;

    @Before
    public void setUp() {
        userFacade = new UserTestFacade();
    }

    @After
    public void tearDown() {
        userFacade.deleteAllUsers();
    }

    @Test
    public void createUserTest() {
        String login = "login";
        String name = "testUser";
        String email = "test@example.com";
        String password = "teste123";
        String cpf = "123456789";
        Boolean isAdmin = false;

        boolean userExists = userFacade.thereIsUserWithEmail(email);
        assertFalse(userExists);

        boolean createdUser = userFacade.create(login, password, name, cpf, email, isAdmin);
        assertTrue(createdUser);

        String createdUserLogin = userFacade.getLoginByUserEmail(email);
        String createdUserName = userFacade.getNameByUserEmail(email);
        String createdUserEmail = userFacade.getEmailByUserEmail(email);
        String createdUserPassword = userFacade.getPasswordByUserEmail(email);
        String createdUserCPF = userFacade.getCpfByUserEmail(email);
        boolean createdUserIsAdmin = userFacade.getIsAdminByUserEmail(email);

        assertEquals(login, createdUserLogin);
        assertEquals(name, createdUserName);
        assertEquals(email, createdUserEmail);
        assertEquals(password, createdUserPassword);
        assertEquals(cpf, createdUserCPF);
        assertEquals(isAdmin, createdUserIsAdmin);
    }
}
