package main.java.UEFS.system.services;

import main.java.UEFS.system.entitys.User;
import main.java.UEFS.system.repositories.UserRepository;

import java.util.List;
import java.util.UUID;

public class UserService {
    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public void create(User user) {
        userRepository.save(user);
    }

    public User getById(UUID id) {
        return userRepository.findById(id);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void update(User user) {
        userRepository.update(user);
    }

    public void delete(UUID id) {
        userRepository.delete(id);
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }
}