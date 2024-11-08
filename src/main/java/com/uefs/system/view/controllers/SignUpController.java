package com.uefs.system.view.controllers;

import com.uefs.system.controller.UserController;
import com.uefs.system.view.NavigationManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class SignUpController {

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLogin() {
        String login = loginField.getText();
        String password = passwordField.getText();

        System.out.println("Login: " + login);
        System.out.println("Senha: " + password);

        if (isValidLogin(login, password)) {
            loginField.setText("");
            passwordField.setText("");
            showLoginSuccessAlert();
            NavigationManager.getInstance().setScene("home");
        } else {
            showLoginErrorAlert();
        }
    }

    private boolean isValidLogin(String login, String password) {
        UserController userController = new UserController();
        return  userController.login(login, password);
    }

    private void showLoginSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login bem-sucedido");
        alert.setHeaderText(null);
        alert.setContentText("Você foi autenticado com sucesso!");
        alert.showAndWait();
    }

    private void showLoginErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro de Login");
        alert.setHeaderText(null);
        alert.setContentText("Usuário ou senha inválidos. Tente novamente.");
        alert.showAndWait();
    }
}
