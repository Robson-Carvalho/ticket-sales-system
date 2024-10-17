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

package main.java.UEFS.system.controller;

import main.java.UEFS.system.model.Mail;
import main.java.UEFS.system.model.User;
import main.java.UEFS.system.service.UserService;

import java.util.List;
import java.util.UUID;

public class UserController {
    private final UserService userService;
    private final MailController mailController;

    public UserController() {
        this.userService = new UserService();
        this.mailController = new MailController();
    }

    public User create(String login, String password, String name, String cpf, String email, Boolean isAdmin) throws Exception {
        User user = new User(login, password, name, cpf, email, isAdmin);
        return userService.create(user);
    }

    public User getById(UUID id){
        return userService.getById(id);
    }

    public User getByLogin(String login){
        return userService.getByLogin(login);
    }

    public List<User> getAll(){
        return userService.getAll();
    }

    public void update(UUID id, String name, String email, String password) {
        User user = userService.getById(id);
    }

    public void update(User user) throws Exception {
        userService.update(user);
    }

    public void delete(UUID id) {
        userService.delete(id);
    }

    public User getByEmail(String email) {
        return userService.getByEmail(email);
    }

    public List<Mail> getMailBox(UUID id){
        return mailController.getMailsByUserId(id);
    }

    public boolean login(String login, String password) {
        return userService.login(login, password);
    }

    public void deleteAll() {
        userService.deleteAll();
    }
}
