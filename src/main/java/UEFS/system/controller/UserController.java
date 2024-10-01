package main.java.UEFS.system.controller;

import main.java.UEFS.system.entity.User;
import main.java.UEFS.system.service.UserService;

import java.util.List;
import java.util.UUID;

public class UserController {
    private final UserService userService;

    public UserController() {
        this.userService = new UserService();
    }

    public User create(String login, String password, String name, String cpf, String email, Boolean isAdmin){
        User user = new User(login, password, name, cpf, email, isAdmin);
        userService.create(user);
        return user;
    }

    public User getById(UUID id){
        return userService.getById(id);
    }

    public List<User> getAll(){
        return userService.getAll();
    }

    public void update(User user) {
        userService.update(user);
    }

    public void delete(UUID id) {
        userService.delete(id);
    }

    public void deleteAll() {
        userService.deleteAll();
    }
}
