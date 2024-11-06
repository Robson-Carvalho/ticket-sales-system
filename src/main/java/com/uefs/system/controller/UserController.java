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

package com.uefs.system.controller;

import com.uefs.system.model.Mail;
import com.uefs.system.model.User;
import com.uefs.system.service.UserService;

import java.util.List;
import java.util.UUID;

public class UserController {
    private final UserService userService;
    private final MailController mailController;

    public UserController() {
        this.userService = new UserService();
        this.mailController = new MailController();
    }

    /**
     * Cria um novo usuário.
     *
     * @param login    O login do usuário.
     * @param password A senha do usuário.
     * @param name     O nome do usuário.
     * @param cpf      O CPF do usuário.
     * @param email    O email do usuário.
     * @param isAdmin  Indica se o usuário é administrador.
     * @return O usuário criado.
     * @throws Exception Se ocorrer um erro ao criar o usuário.
     */
    public User create(String login, String password, String name, String cpf, String email, Boolean isAdmin) throws Exception {
        User user = new User(login, password, name, cpf, email, isAdmin);
        return userService.create(user);
    }

    /**
     * Obtém um usuário pelo ID.
     *
     * @param id O UUID do usuário a ser buscado.
     * @return O usuário correspondente ao ID.
     */
    public User getById(UUID id) {
        return userService.getById(id);
    }

    /**
     * Obtém um usuário pelo login.
     *
     * @param login O login do usuário a ser buscado.
     * @return O usuário correspondente ao login.
     */
    public User getByLogin(String login) {
        return userService.getByLogin(login);
    }

    /**
     * Obtém todos os usuários.
     *
     * @return Uma lista de todos os usuários.
     */
    public List<User> getAll() {
        return userService.getAll();
    }

    /**
     * Atualiza as informações de um usuário.
     *
     * @param id      O UUID do usuário a ser atualizado.
     * @param name    O novo nome do usuário.
     * @param email   O novo email do usuário.
     * @param password A nova senha do usuário.
     */
    public void update(UUID id, String name, String email, String password) {
        User user = userService.getById(id);
        // Aqui você pode definir os novos valores para o usuário
        // user.setName(name);
        // user.setEmail(email);
        // user.setPassword(password);
        // userService.update(user);
    }

    /**
     * Atualiza as informações de um usuário.
     *
     * @param user O usuário a ser atualizado.
     * @throws Exception Se ocorrer um erro ao atualizar o usuário.
     */
    public void update(User user) throws Exception {
        userService.update(user);
    }

    /**
     * Deleta um usuário pelo ID.
     *
     * @param id O UUID do usuário a ser deletado.
     */
    public void delete(UUID id) {
        userService.delete(id);
    }

    /**
     * Obtém um usuário pelo email.
     *
     * @param email O email do usuário a ser buscado.
     * @return O usuário correspondente ao email.
     */
    public User getByEmail(String email) {
        return userService.getByEmail(email);
    }

    /**
     * Obtém a caixa de entrada de e-mails de um usuário.
     *
     * @param id O UUID do usuário.
     * @return Uma lista de e-mails do usuário.
     */
    public List<Mail> getMailBox(UUID id) {
        return mailController.getMailsByUserId(id);
    }

    /**
     * Realiza o login de um usuário.
     *
     * @param login    O login do usuário.
     * @param password A senha do usuário.
     * @return true se o login for bem-sucedido, false caso contrário.
     */
    public boolean login(String login, String password) {
        return userService.login(login, password);
    }

    /**
     * Deleta todos os usuários.
     */
    public void deleteAll() {
        userService.deleteAll();
    }
}
