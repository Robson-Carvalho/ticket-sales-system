package com.uefs.system.view.controllers;

import com.uefs.system.Interface.ILanguageObserver;
import com.uefs.system.controller.EventController;
import com.uefs.system.controller.UserController;
import com.uefs.system.emun.SceneEnum;
import com.uefs.system.model.Event;
import com.uefs.system.utils.AccessibilityManager;
import com.uefs.system.utils.LanguageManager;
import com.uefs.system.utils.SessionManager;
import com.uefs.system.view.NavigationManager;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SettingsController implements ILanguageObserver {
    private final NavigationManager navigationManager = new NavigationManager();
    private final SessionManager sessionManager;
    private final LanguageManager languageManager;
    private final UserController userController = new UserController();
    private final AccessibilityManager accessibilityManager = new AccessibilityManager();

    public SettingsController(LanguageManager languageManager, SessionManager sessionManager) {
        this.languageManager = languageManager;
        this.sessionManager = sessionManager;
    }

    //    Components
    @FXML private Button homeNavBar;
    @FXML private Button mailBoxNavBar;
    @FXML private Button settingsNavBar;
    @FXML private Button cardsNavBar;
    @FXML private Button buysNavBar;
    @FXML private Text titleMain;
    @FXML private Button logoutButton;

    @FXML private Label labelID;
    @FXML private Label labelName;
    @FXML private Label labelLogin;
    @FXML private Label labelEmail;
    @FXML private Label labelPassword;
    @FXML private Label labelCPF;

    @FXML private TextField idField;
    @FXML private TextField nameField;
    @FXML private TextField loginField;
    @FXML private TextField emailField;
    @FXML private TextField passwordField;
    @FXML private TextField cpfField;

    @FXML private Button submitButton;
    @FXML private Button toggleLanguageButton;
    @FXML private CheckBox accessibilityCheckBox;
    @FXML private Button cancelButton;

    //    Initialize
    @FXML
    private void initialize() {
        updateLanguage();
        setData();
    }

    private void setData(){
        idField.setText(sessionManager.getID());
        idField.setEditable(false);
        loginField.setText(sessionManager.getLogin());
        loginField.setEditable(false);
        cpfField.setText(applyCPFMask(sessionManager.getCPF()));
        cpfField.setEditable(false);

        nameField.setText(sessionManager.getName());
        emailField.setText(sessionManager.getEmail());
        passwordField.setText(sessionManager.getPassword());
        accessibilityCheckBox.setSelected(accessibilityManager.getAccessibilityPropertiesCurrent());
    }

    private String applyCPFMask(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() > 11) {
            cpf = cpf.substring(0, 11);
        }
        StringBuilder formattedCPF = new StringBuilder();

        for (int i = 0; i < cpf.length(); i++) {
            if (i == 3 || i == 6) {
                formattedCPF.append(".");
            } else if (i == 9) {
                formattedCPF.append("-");
            }
            formattedCPF.append(cpf.charAt(i));
        }

        return formattedCPF.toString();
    }

    private void messageAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML private void cancelUpdate(){
        setData();
    }

    @FXML private void toggleLanguage() {languageManager.toggleLanguage();}

    @FXML private void toggleAccessibility(){
        accessibilityManager.toggleAccessibilityProperties();
        languageManager.notifyObservers();
    }

    @FXML private void handleUpdateUser(){
        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        if(name.isEmpty() || email.isEmpty() || password.isEmpty()){
            messageAlert(Alert.AlertType.WARNING, "Por favor, preencha todos os campos.");
        }else{
            try{
                userController.update(UUID.fromString(sessionManager.getID()), name, email, password);
                sessionManager.updateSession(name, email, password);
                messageAlert(Alert.AlertType.INFORMATION, "Usuário atulizado com sucesso!");
            }catch (Exception e){
                messageAlert(Alert.AlertType.WARNING, "E-mail já está em uso!");
            }
        }

        languageManager.notifyObservers();
    }

    // Navigation
    @FXML private void navigationToCards(){navigationManager.setScene(SceneEnum.CARDS);}

    @FXML private void navigationToHome(){navigationManager.setScene(SceneEnum.DASHBOARD);}

    @FXML private void navigationToMailBox(){navigationManager.setScene(SceneEnum.MAILBOX);}

    @FXML private void navigationToSettings(){navigationManager.setScene(SceneEnum.SETTINGS);}

    @FXML private void navigationToBuys(){navigationManager.setScene(SceneEnum.BUYS);}

    // Life Cycle
    @Override
    public void updateLanguage() {
        String userName = sessionManager.getName();

        Boolean accessibilityIsActive = accessibilityManager.getAccessibilityPropertiesCurrent();

        homeNavBar.setText(languageManager.getText("components.navbar.homeNavBar"));
        mailBoxNavBar.setText(languageManager.getText("components.navbar.mailBoxNavBar"));
        settingsNavBar.setText(languageManager.getText("components.navbar.settingsNavBar"));
        buysNavBar.setText(languageManager.getText("components.navbar.buysNavBar"));
        cardsNavBar.setText(languageManager.getText("components.navbar.cardsNavBar"));
        logoutButton.setText(languageManager.getText("components.navbar.logoutButton"));

        titleMain.setText(languageManager.getText("screens.settings.titleMain") + ", " + userName.split(" ")[0] + "!");

        labelID.setText(languageManager.getText("screens.settings.labelID"));
        labelName.setText(languageManager.getText("screens.settings.labelName"));
        labelLogin.setText(languageManager.getText("screens.settings.labelLogin"));
        labelEmail.setText(languageManager.getText("screens.settings.labelEmail"));
        labelPassword.setText(languageManager.getText("screens.settings.labelPassword"));
        labelCPF.setText(languageManager.getText("screens.settings.labelCPF"));

        submitButton.setText(languageManager.getText("screens.settings.submitButton"));
        cancelButton.setText(languageManager.getText("screens.settings.cancelUpdate"));
        toggleLanguageButton.setText(languageManager.getText("screens.settings.toggleLanguageButton"));
        accessibilityCheckBox.setText(languageManager.getText("screens.settings.accessibilityCheckBox"));

        if (accessibilityIsActive) {
            setFontSize(18, 28, 16, 16, 16);
        }else{
            setFontSize(16, 24, 14, 14, 14);
        }

        setData();
    }

    private void setFontSize(double fontSizeNavBar, double fontSizeTitleMain, double fontSizeLabel, double fontSizeField, double fontSizeButton) {
        homeNavBar.setStyle("-fx-font-size: " + fontSizeNavBar + "px;");
        mailBoxNavBar.setStyle("-fx-font-size: " + fontSizeNavBar + "px;");
        settingsNavBar.setStyle("-fx-font-size: " + fontSizeNavBar + "px;");
        buysNavBar.setStyle("-fx-font-size: " + fontSizeNavBar + "px;");
        cardsNavBar.setStyle("-fx-font-size: " + fontSizeNavBar + "px;");
        logoutButton.setStyle("-fx-font-size: " + fontSizeNavBar + "px;");

        titleMain.setStyle("-fx-font-size: " + fontSizeTitleMain + "px;");

        labelID.setStyle("-fx-font-size: " + fontSizeLabel + "px;");
        labelName.setStyle("-fx-font-size: " + fontSizeLabel + "px;");
        labelLogin.setStyle("-fx-font-size: " + fontSizeLabel + "px;");
        labelEmail.setStyle("-fx-font-size: " + fontSizeLabel + "px;");
        labelPassword.setStyle("-fx-font-size: " + fontSizeLabel + "px;");

        idField.setStyle("-fx-font-size: " + fontSizeField + "px;");
        nameField.setStyle("-fx-font-size: " + fontSizeField + "px;");
        passwordField.setStyle("-fx-font-size: " + fontSizeField + "px;");
        cpfField.setStyle("-fx-font-size: " + fontSizeField + "px;");
        emailField.setStyle("-fx-font-size: " + fontSizeField + "px;");
        loginField.setStyle("-fx-font-size: " + fontSizeField + "px;");

        submitButton.setStyle("-fx-font-size: " + fontSizeButton + "px;");
        toggleLanguageButton.setStyle("-fx-font-size: " + fontSizeButton + "px;");
        accessibilityCheckBox.setStyle("-fx-font-size: " + fontSizeButton + "px;");
        cancelButton.setStyle("-fx-font-size: " + fontSizeButton + "px;");
    }

    // Logic
    @FXML
    public void logout(){
        sessionManager.clearUserSession();
        navigationManager.setScene(SceneEnum.SIGNIN);
    }
}
