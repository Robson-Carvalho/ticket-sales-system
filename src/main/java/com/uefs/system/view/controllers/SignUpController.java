package com.uefs.system.view.controllers;

import com.uefs.system.Interface.ILanguageObserver;
import com.uefs.system.utils.LanguageManager;
import com.uefs.system.view.NavigationManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

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

    NavigationManager navigationManager = new NavigationManager();
    LanguageManager languageManager;

    public SignUpController(LanguageManager languageManager) {
        this.languageManager = languageManager;
    }

    @FXML
    private void initialize() {
        updateLanguage();
    }

    @FXML
    private void handleSignUp(){

    }

    @FXML
    private void navigationToSignInScreen(){navigationManager.setScene("signin");}

    @FXML
    private void toggleLanguage() {languageManager.toggleLanguage();}

    @Override
    public void updateLanguage() {
        titleScreenSignUp.setText(languageManager.getText("titleScreenSignUp"));
        subtitleScreenSignUp.setText(languageManager.getText("subtitleScreenSignUp"));
        subtitleScreenSignUp.setWrappingWidth(440);

        loginLabel.setText(languageManager.getText("loginLabel"));
        nameLabel.setText(languageManager.getText("nameLabel"));
        cpfLabel.setText(languageManager.getText("cpfLabel"));
        emailLabel.setText(languageManager.getText("emailLabel"));
        passwordLabel.setText(languageManager.getText("passwordLabel"));

        loginField.setPromptText(languageManager.getText("loginField"));
        nameField.setPromptText(languageManager.getText("nameField"));
        cpfField.setPromptText(languageManager.getText("cpfField"));
        emailField.setPromptText(languageManager.getText("emailField"));
        passwordField.setPromptText(languageManager.getText("passwordField"));

        signInButton.setText(languageManager.getText("signInButton"));
        signUpButton.setText(languageManager.getText(("signUpButton")));
        toggleLanguageButton.setText(languageManager.getText("toggleLanguageButton"));
    }
}
