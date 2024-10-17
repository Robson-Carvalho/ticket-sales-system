package test.java.com.UEFS.system.CommonTests.ControllerTest;

import main.java.UEFS.system.testFacade.ControllerTestFacade.ControllerTestFacade;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

public class ControllerTest {
    private ControllerTestFacade controllerTestFacade;

    @Before
    public void setUp() {
        controllerTestFacade = new ControllerTestFacade();
    }

    @After
    public void tearDown() {
        controllerTestFacade.deleteDataBase();
    }

    @Test
    public void testPurchaseFlow() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.SEPTEMBER, 10);
        String name = "Show de Rock";
        String description = "Banda XYZ";
        Date date = calendar.getTime();

        String _login = "LS@";
        String _nameUser = "Lia Silva";
        String _email = "lia@example.com";
        String _password = "teste123";
        String _cpf = "987654321";
        boolean _isAdmin = true;

        String login = "Lr@";
        String nameUser = "Lara";
        String email = "lr@example.com";
        String password = "teste123";
        String cpf = "123456789";
        boolean isAdmin = false;

        controllerTestFacade.createUser(_login, _password, _nameUser, _cpf, _email, _isAdmin);
        controllerTestFacade.createUser(login, password, nameUser, cpf, email, isAdmin);

        String commonUserId = controllerTestFacade.getUserIdByEmail(_email);

        String eventId = controllerTestFacade.createEvent(_login, name, description, date);

        controllerTestFacade.buyTicket(commonUserId, eventId, "A1");

        // Verificar se há ticket com eventId
        // Verificar se há transação com ticketId
        // Verificar se há email com o id da transação
    }

}
