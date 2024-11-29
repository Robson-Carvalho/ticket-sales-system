package com.uefs.system.view.controllers;

import com.uefs.system.Interface.ILanguageObserver;
import com.uefs.system.controller.UserController;
import com.uefs.system.emun.SceneEnum;
import com.uefs.system.model.User;
import com.uefs.system.utils.AccessibilityManager;
import com.uefs.system.utils.LanguageManager;
import com.uefs.system.utils.SessionManager;
import com.uefs.system.view.NavigationManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;

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
    private final SessionManager sessionManager;
    private final AccessibilityManager accessibilityManager = new AccessibilityManager();

    public SignInController(LanguageManager languageManager, SessionManager sessionManager) {
        this.languageManager = languageManager;
        this.sessionManager = sessionManager;
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
            messageAlert(Alert.AlertType.INFORMATION, languageManager.getText("messagesAlert.loginSuccess"));
            navigationManager.setScene(SceneEnum.DASHBOARD);
            return;
        }
        messageAlert(Alert.AlertType.WARNING, languageManager.getText("messagesAlert.loginError"));
    }

    @FXML
    private void toggleLanguage() {languageManager.toggleLanguage();}

    @FXML
    private void navigationToSignUpScreen(){navigationManager.setScene(SceneEnum.SIGNUP);}

    private boolean isValidLogin(String login, String password) {
        UserController userController = new UserController();

        if(userController.login(login, password)){
            User user = userController.getByLogin(login);
            sessionManager.saveUserSession(user);
            languageManager.notifyObservers();
            return true;
        }else{
            return false;
        }
    }

    private void messageAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void updateLanguage() {
        Boolean accessibilityIsActive = accessibilityManager.getAccessibilityPropertiesCurrent();

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

        if (accessibilityIsActive) {
            setFontSize(18, 28, 16, 16, 16);
        }else{
            setFontSize(16, 24, 14, 14, 14);
        }
    }

    private void setFontSize(double fontSizeSubtitle, double fontSizeTitleMain, double fontSizeLabel, double fontSizeField, double fontSizeButton) {

    }
}
