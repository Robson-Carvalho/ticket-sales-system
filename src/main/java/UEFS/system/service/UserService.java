package main.java.UEFS.system.service;

import main.java.UEFS.system.model.User;
import main.java.UEFS.system.repository.UserRepository;
import main.java.UEFS.system.interfaces.IService;

import java.util.List;
import java.util.UUID;

public class UserService implements IService<User> {
    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    @Override
    public User create(User user) {userRepository.save(user); return user;}

    @Override
    public List<User> getAll() {return userRepository.findAll();}

    @Override
    public User getById(UUID id) {return userRepository.findById(id);}

    @Override
    public void update(User user) {userRepository.update(user);}

    @Override
    public void delete(UUID id) {userRepository.delete(id);}

    @Override
    public void deleteAll() {userRepository.deleteAll();}
}
