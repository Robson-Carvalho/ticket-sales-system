package main.java.UEFS.system.controller;

import main.java.UEFS.system.exception.UserException;
import main.java.UEFS.system.model.User;
import main.java.UEFS.system.service.UserService;

import java.util.List;
import java.util.UUID;

public class UserController {
    private final UserService userService;

    public UserController() {
        this.userService = new UserService();
    }

    public User create(String login, String password, String name, String cpf, String email, Boolean isAdmin) throws UserException {
        User user = new User(login, password, name, cpf, email, isAdmin);
        return userService.create(user);
    }

    public User getById(UUID id){
        return userService.getById(id);
    }

    public List<User> getAll(){
        return userService.getAll();
    }

    public void update(UUID id, String name, String email, String password) {
        User user = userService.getById(id);
    }

    public void update(User user) throws UserException {
        userService.update(user);
    }

    public void delete(UUID id) {
        userService.delete(id);
    }

    public User getByEmail(String email) {
        return userService.getByEmail(email);
    }

    public void getMailBox(){

    }

    public void getLastMail(){

    }

    public void deleteAll() {
        userService.deleteAll();
    }
}
