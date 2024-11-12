package com.uefs.system.view.controllers;

import com.uefs.system.Interface.ILanguageObserver;
import com.uefs.system.controller.UserController;
import com.uefs.system.emun.SceneEnum;
import com.uefs.system.model.User;
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
    @FXML private Button signUpButton;

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
        if(isValidLogin(loginField.getText(), passwordField.getText())) {
            loginField.setText("");
            passwordField.setText("");
            showLoginSuccessAlert();
            navigationManager.setScene(SceneEnum.DASHBOARD);
            return;
        }
       showLoginErrorAlert();
    }

    @FXML
    private void toggleLanguage() {languageManager.toggleLanguage();}

    @FXML
    private void navigationToSignUpScreen(){navigationManager.setScene(SceneEnum.SIGNUP);}

    private boolean isValidLogin(String login, String password) {
        UserController userController = new UserController();
        return userController.login(login, password);
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
        titleScreenLogin.setText(languageManager.getText("screens.signIn.title"));
        subtitleScreenLogin.setText(languageManager.getText("screens.signIn.subtitle"));
        subtitleScreenLogin.setWrappingWidth(440);

        loginLabel.setText(languageManager.getText("screens.signIn.loginLabel"));
        passwordLabel.setText(languageManager.getText("screens.signIn.passwordLabel"));

        loginField.setPromptText(languageManager.getText("screens.signIn.loginField"));
        passwordField.setPromptText(languageManager.getText("screens.signIn.loginField"));

        signInButton.setText(languageManager.getText("screens.signIn.buttons.signIn"));
        signUpButton.setText(languageManager.getText(("screens.signIn.buttons.register")));
        toggleLanguageButton.setText(languageManager.getText("screens.signIn.buttons.toggleLanguage"));
    }
}
