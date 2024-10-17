/***************************
 * Autor: Robson Carvalho de Souza
 * Componente Curricular: MI de Programação
 * Concluído em: 16/09/2024
 * Declaro que este código foi elaborado por mim de forma individual e não contém nenhum
 * trecho de código de outro colega ou de outro autor, tais como provindos de livros e
 * apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
 * de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
 * do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
 ******************************/

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
    public User create(User user) throws Exception {
         if(this.getByEmail(user.getEmail()) != null || this.getByCpf(user.getCpf()) != null || this.getByEmail(user.getEmail()) != null){
             throw new SecurityException("Login, email e/ou cpf já está em uso.");
         }

        userRepository.save(user);
        return user;
    }

    @Override
    public List<User> getAll() {return userRepository.findAll();}

    @Override
    public User getById(UUID id) {return userRepository.findById(id);}

    @Override
    public void update(User user) throws Exception{
        User _user = this.getByEmail(user.getEmail());

        if(_user != null && !_user.getId().equals(user.getId())){
            throw new Exception("Email já está em uso.");
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

    public boolean login(String login, String password){
        User user = this.getByLogin(login);
        return user != null && user.getPassword().equals(password);
    }
}
