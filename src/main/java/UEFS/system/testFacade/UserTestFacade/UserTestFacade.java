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

package main.java.UEFS.system.testFacade.UserTestFacade;

import main.java.UEFS.system.controller.UserController;
import main.java.UEFS.system.model.User;

public class UserTestFacade {
    private final UserController userController;

    public UserTestFacade() {
        this.userController = new UserController();
    }

    public boolean create(String login, String password, String name, String cpf, String email, Boolean isAdmin) {
        User user = null;

        try {
            user = userController.create(login, password, name, cpf, email, isAdmin);
        } catch (Exception e) {
            throw new SecurityException(e.getMessage());
        }
        return user != null;
    }

    public void setNameByUserEmail(String name, String email) {
        User user = userController.getByEmail(email);

        user.setName(name);

        try {
            userController.update(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setPasswordByUserEmail(String password, String email) {
        User user = userController.getByEmail(email);

        user.setPassword(password);

        try {
            userController.update(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setEmailByUserEmail(String newEmail, String email) {
        User user = userController.getByEmail(email);

        user.setEmail(newEmail);

        try {
            userController.update(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getUserIdByEmail(String email) {
        User user = userController.getByEmail(email);
        return user.getId().toString();
    }

    public String getLoginByUserEmail(String email) {
        User user = userController.getByEmail(email);
        return user.getLogin();
    }

    public String getNameByUserEmail(String email) {
        User user = userController.getByEmail(email);
        return user.getName();
    }

    public String getEmailByUserEmail(String email) {
        User user = userController.getByEmail(email);
        return user.getEmail();
    }

    public String getPasswordByUserEmail(String email) {
        User user = userController.getByEmail(email);
        return user.getPassword();
    }

    public String getCpfByUserEmail(String email) {
        User user = userController.getByEmail(email);
        return user.getCpf();
    }

    public boolean getIsAdminByUserEmail(String email) {
        User user = userController.getByEmail(email);
        return user.isAdmin();
    }

    public boolean thereIsUserWithEmail(String email)  {
        User user = userController.getByEmail(email);
        return user != null;
    }

    public int getSizeUsers(){
        return userController.getAll().size();
    }

    public void deleteByUserEmail(String email){
        User user = userController.getByEmail(email);
        userController.delete(user.getId());
    }

    public boolean login(String login, String password){
        return userController.login(login, password);
    }

    public void deleteAllUsers(){
        userController.deleteAll();
    }
}
