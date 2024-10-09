package main.java.UEFS.system.service;

import main.java.UEFS.system.exception.UserException;
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
    public User create(User user) throws UserException {
         if(this.getByEmail(user.getEmail()) != null || this.getByCpf(user.getCpf()) != null || this.getByEmail(user.getEmail()) != null){
             throw new UserException("Login, email e/ou cpf já está em uso.");
         }

        userRepository.save(user);
        return user;
    }

    @Override
    public List<User> getAll() {return userRepository.findAll();}

    @Override
    public User getById(UUID id) {return userRepository.findById(id);}

    @Override
    public void update(User user) throws UserException{
        User _user = this.getByEmail(user.getEmail());

        if(_user != null && !_user.getId().equals(user.getId())){
            throw new UserException("Email já está em uso.");
        }

        userRepository.update(user);
    }

    @Override
    public void delete(UUID id) {userRepository.delete(id);}

    @Override
    public void deleteAll() {userRepository.deleteAll();}

    public User getByLogin(String login) {
        List<User> users = this.getAll();

        for (User u : users) {
            if(u.getLogin().equals(login)) {
                return u;
            }
        }

        return null;
    }

    public User getByCpf(String cpf) {
        List<User> users = this.getAll();

        for (User u : users) {
            if(u.getCpf().equals(cpf)) {
                return u;
            }
        }

        return null;
    }

    public User getByEmail(String email) {
        List<User> users = this.getAll();

        for (User u : users) {
            if(u.getEmail().equals(email)) {
                return u;
            }
        }

        return null;
    }
}
