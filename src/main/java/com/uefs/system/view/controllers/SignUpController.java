package com.uefs.system.view.controllers;

import com.uefs.system.Interface.ILanguageObserver;
import com.uefs.system.controller.UserController;
import com.uefs.system.emun.SceneEnum;
import com.uefs.system.model.User;
import com.uefs.system.utils.LanguageManager;
import com.uefs.system.utils.SessionManager;
import com.uefs.system.view.NavigationManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;

public class SignUpController implements ILanguageObserver {
    @FXML private Text titleScreenSignUp;
    @FXML private Text subtitleScreenSignUp;
    @FXML private Label loginLabel;
    @FXML private Label passwordLabel;
    @FXML private Label nameLabel;
    @FXML private Label cpfLabel;
    @FXML private Label emailLabel;

    @FXML private TextField loginField;
    @FXML private PasswordField passwordField;
    @FXML private TextField nameField;
    @FXML private TextField cpfField;
    @FXML private TextField emailField;

    @FXML private Button signUpButton;
    @FXML private Button signInButton;
    @FXML private Button toggleLanguageButton;

    private final  UserController userController = new UserController();
    private final NavigationManager navigationManager = new NavigationManager();
    private final LanguageManager languageManager;
    private final SessionManager sessionManager;

    public SignUpController(LanguageManager languageManager, SessionManager sessionManager) {
        this.languageManager = languageManager;
        this.sessionManager = sessionManager;
    }

    @FXML
    private void initialize() {
        updateLanguage();
    }

    @FXML
    private void handleSignUp() {
        try {
            if(userController.getByCPF(cpfField.getText()) != null || userController.getByEmail(emailField.getText()) != null || userController.getByLogin(loginField.getText()) != null) {
                messageAlert(Alert.AlertType.WARNING, languageManager.getText("messagesAlert.userAlreadyExists"));
                return;
            }

            User user = userController.create(loginField.getText(), passwordField.getText(), nameField.getText(), cpfField.getText(),emailField.getText(), false);

            if(isValidLogin(user.getLogin(), user.getPassword())){
                messageAlert(Alert.AlertType.INFORMATION, languageManager.getText("messagesAlert.userCreatedSuccess"));
                sessionManager.saveUserSession(user);
                navigationManager.setScene(SceneEnum.DASHBOARD);
            }else{
                messageAlert(Alert.AlertType.WARNING, languageManager.getText("messagesAlert.userCreatedError"));
            }
        }catch (Exception e) {
            messageAlert(Alert.AlertType.WARNING, languageManager.getText("messagesAlert.userCreatedError"));
        }

        languageManager.notifyObservers();
    }

    private void messageAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    private boolean isValidLogin(String login, String password) {
        UserController userController = new UserController();
        return userController.login(login, password);
    }


    @FXML
    private void navigationToSignIn(){navigationManager.setScene(SceneEnum.SIGNIN);}

    @FXML
    private void toggleLanguage() {languageManager.toggleLanguage();}

    @Override
    public void updateLanguage() {
        titleScreenSignUp.setText(languageManager.getText("screens.signUp.title"));
        subtitleScreenSignUp.setText(languageManager.getText("screens.signUp.subtitle"));
        subtitleScreenSignUp.setWrappingWidth(440);

        loginLabel.setText(languageManager.getText("screens.signUp.loginLabel"));
        nameLabel.setText(languageManager.getText("screens.signUp.nameLabel"));
        cpfLabel.setText(languageManager.getText("screens.signUp.cpfLabel"));
        emailLabel.setText(languageManager.getText("screens.signUp.emailLabel"));
        passwordLabel.setText(languageManager.getText("screens.signUp.passwordLabel"));

        loginField.setPromptText(languageManager.getText("screens.signUp.loginField"));
        nameField.setPromptText(languageManager.getText("screens.signUp.nameField"));
        cpfField.setPromptText(languageManager.getText("screens.signUp.cpfField"));
        emailField.setPromptText(languageManager.getText("screens.signUp.emailField"));
        passwordField.setPromptText(languageManager.getText("screens.signUp.passwordLabel"));

        signInButton.setText(languageManager.getText("screens.signUp.buttons.signIn"));
        signUpButton.setText(languageManager.getText(("screens.signUp.buttons.signUp")));
        toggleLanguageButton.setText(languageManager.getText("screens.signUp.buttons.toggleLanguage"));
    }
}
