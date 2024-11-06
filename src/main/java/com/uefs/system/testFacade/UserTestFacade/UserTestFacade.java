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

package com.uefs.system.testFacade.UserTestFacade;

import com.uefs.system.controller.UserController;
import com.uefs.system.model.User;

/**
 * Facade para testes de usuários, oferecendo uma interface simplificada para a criação, consulta, e manipulação de usuários.
 */
public class UserTestFacade {
    private final UserController userController;

    /**
     * Construtor que inicializa o controlador de usuários.
     */
    public UserTestFacade() {
        this.userController = new UserController();
    }

    /**
     * Cria um novo usuário.
     *
     * @param login Login do usuário.
     * @param password Senha do usuário.
     * @param name Nome do usuário.
     * @param cpf CPF do usuário.
     * @param email Email do usuário.
     * @param isAdmin Define se o usuário é administrador.
     * @return Retorna true se o usuário foi criado com sucesso, false caso contrário.
     */
    public boolean create(String login, String password, String name, String cpf, String email, Boolean isAdmin) {
        User user = null;
        try {
            user = userController.create(login, password, name, cpf, email, isAdmin);
        } catch (Exception e) {
            throw new SecurityException(e.getMessage());
        }
        return user != null;
    }

    /**
     * Atualiza o nome de um usuário baseado em seu email.
     *
     * @param name Novo nome do usuário.
     * @param email Email do usuário.
     */
    public void setNameByUserEmail(String name, String email) {
        User user = userController.getByEmail(email);
        user.setName(name);

        try {
            userController.update(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Atualiza a senha de um usuário baseado em seu email.
     *
     * @param password Nova senha do usuário.
     * @param email Email do usuário.
     */
    public void setPasswordByUserEmail(String password, String email) {
        User user = userController.getByEmail(email);
        user.setPassword(password);

        try {
            userController.update(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Atualiza o email de um usuário baseado em seu email atual.
     *
     * @param newEmail Novo email do usuário.
     * @param email Email atual do usuário.
     */
    public void setEmailByUserEmail(String newEmail, String email) {
        User user = userController.getByEmail(email);
        user.setEmail(newEmail);

        try {
            userController.update(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retorna o ID de um usuário baseado em seu email.
     *
     * @param email Email do usuário.
     * @return ID do usuário.
     */
    public String getUserIdByEmail(String email) {
        User user = userController.getByEmail(email);
        return user.getId().toString();
    }

    /**
     * Retorna o login de um usuário baseado em seu email.
     *
     * @param email Email do usuário.
     * @return Login do usuário.
     */
    public String getLoginByUserEmail(String email) {
        User user = userController.getByEmail(email);
        return user.getLogin();
    }

    /**
     * Retorna o nome de um usuário baseado em seu email.
     *
     * @param email Email do usuário.
     * @return Nome do usuário.
     */
    public String getNameByUserEmail(String email) {
        User user = userController.getByEmail(email);
        return user.getName();
    }

    /**
     * Retorna o email de um usuário baseado em seu email.
     *
     * @param email Email do usuário.
     * @return Email do usuário.
     */
    public String getEmailByUserEmail(String email) {
        User user = userController.getByEmail(email);
        return user.getEmail();
    }

    /**
     * Retorna a senha de um usuário baseado em seu email.
     *
     * @param email Email do usuário.
     * @return Senha do usuário.
     */
    public String getPasswordByUserEmail(String email) {
        User user = userController.getByEmail(email);
        return user.getPassword();
    }

    /**
     * Retorna o CPF de um usuário baseado em seu email.
     *
     * @param email Email do usuário.
     * @return CPF do usuário.
     */
    public String getCpfByUserEmail(String email) {
        User user = userController.getByEmail(email);
        return user.getCpf();
    }

    /**
     * Verifica se o usuário com o email fornecido é um administrador.
     *
     * @param email Email do usuário.
     * @return True se o usuário é administrador, false caso contrário.
     */
    public boolean getIsAdminByUserEmail(String email) {
        User user = userController.getByEmail(email);
        return user.isAdmin();
    }

    /**
     * Verifica se existe um usuário com o email fornecido.
     *
     * @param email Email do usuário.
     * @return True se o usuário existe, false caso contrário.
     */
    public boolean thereIsUserWithEmail(String email) {
        User user = userController.getByEmail(email);
        return user != null;
    }

    /**
     * Retorna a quantidade total de usuários.
     *
     * @return Número total de usuários.
     */
    public int getSizeUsers() {
        return userController.getAll().size();
    }

    /**
     * Deleta um usuário baseado em seu email.
     *
     * @param email Email do usuário a ser deletado.
     */
    public void deleteByUserEmail(String email) {
        User user = userController.getByEmail(email);
        userController.delete(user.getId());
    }

    /**
     * Realiza o login de um usuário baseado no login e senha.
     *
     * @param login Login do usuário.
     * @param password Senha do usuário.
     * @return True se o login foi bem-sucedido, false caso contrário.
     */
    public boolean login(String login, String password) {
        return userController.login(login, password);
    }

    /**
     * Deleta todos os usuários.
     */
    public void deleteAllUsers() {
        userController.deleteAll();
    }
}
