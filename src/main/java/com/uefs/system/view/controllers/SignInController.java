package com.uefs.system.view.controllers;

import com.uefs.system.Interface.ILanguageObserver;
import com.uefs.system.controller.UserController;
import com.uefs.system.utils.LanguageManager;
import com.uefs.system.view.NavigationManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

public class SignInController implements ILanguageObserver {
    @FXML private Text titleScreenLogin;
    @FXML private Text subtitleScreenLogin ;
    @FXML private TextField loginField;
    @FXML private PasswordField passwordField;
    @FXML private Label loginLabel;
    @FXML private Label passwordLabel;
    @FXML private Button signInButton;
    @FXML private Button toggleLanguageButton;

    private final NavigationManager navigationManager = new NavigationManager();
    private final LanguageManager languageManager;

    public SignInController(LanguageManager languageManager) {
        this.languageManager = languageManager;
    }

    @FXML
    private void initialize() {
        updateLanguage();
    }

    @FXML
    private void handleSignIn() {
        String login = loginField.getText();
        String password = passwordField.getText();

        System.out.println("Login: " + login);
        System.out.println("Senha: " + password);

        if (isValidLogin(login, password)) {
            loginField.setText("");
            passwordField.setText("");
            showLoginSuccessAlert();
            navigationManager.setScene("home");
        } else {
            showLoginErrorAlert();
        }
    }

    @FXML
    private void toggleLanguage() {
        languageManager.toggleLanguage();
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

    @Override
    public void updateLanguage() {
        titleScreenLogin.setText(languageManager.getText("titleScreenLogin"));
        subtitleScreenLogin.setText(languageManager.getText("subtitleScreenLogin"));

        loginLabel.setText(languageManager.getText("loginLabel"));
        passwordLabel.setText(languageManager.getText("passwordLabel"));

        loginField.setPromptText(languageManager.getText("loginField"));
        passwordField.setPromptText(languageManager.getText("passwordField"));

        signInButton.setText(languageManager.getText("signInButton"));
        toggleLanguageButton.setText(languageManager.getText("toggleLanguageButton"));
    }
}
