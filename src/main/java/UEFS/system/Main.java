package main.java.UEFS.system;

import main.java.UEFS.system.controller.UserController;
import main.java.UEFS.system.exception.UserException;
import main.java.UEFS.system.model.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class Main {
    public static void main(String[] args) throws UserException {

        UserController userController = new UserController();

        userController.deleteAll();

        userController.create("rob", "senha123", "Admin User", "00000000000", "rob@example.com", true);

        try{
            userController.create("rob", "senha123", "Admin User", "00000000000", "rob@example.com", true);
        }catch (UserException e){
            System.out.println(e.getMessage());
        }

        userController.create("rob1", "senha123", "Comm User", "1000000000", "robs@example.com", false);




    }
}
