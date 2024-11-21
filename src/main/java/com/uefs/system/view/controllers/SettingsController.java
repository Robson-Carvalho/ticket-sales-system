package com.uefs.system.view.controllers;

import com.uefs.system.Interface.ILanguageObserver;
import com.uefs.system.controller.EventController;
import com.uefs.system.emun.SceneEnum;
import com.uefs.system.model.Event;
import com.uefs.system.utils.LanguageManager;
import com.uefs.system.view.NavigationManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SettingsController implements ILanguageObserver {
    private final NavigationManager navigationManager = new NavigationManager();
    private final LanguageManager languageManager;

    public SettingsController(LanguageManager languageManager) {
        this.languageManager = languageManager;
    }

    @FXML private Button logoutButton;
    @FXML private Button home;
    @FXML private Button mailBox;
    @FXML private Button settings;
    @FXML private TextField usernameField;
    @FXML private TextField emailField;
    @FXML private Button saveButton;
    @FXML private Button changeLanguageButton;

    @FXML private Button btnAddCard;
    @FXML
    private void cards(){
        navigationManager.setScene(SceneEnum.CARDS);
    }

    @FXML
    private void initialize() {
        updateLanguage();
    }

    @FXML
    private void home() {
        navigationManager.setScene(SceneEnum.DASHBOARD);
    }

    @FXML
    private void mailBox() {
        navigationManager.setScene(SceneEnum.MAILBOX);
    }

    @FXML
    private void settings() {
        navigationManager.setScene(SceneEnum.SETTINGS);
    }

    @FXML
    public void logout() {
        navigationManager.setScene(SceneEnum.SIGNIN);
    }

    @FXML
    private void save() {
        String username = usernameField.getText();
        String email = emailField.getText();
        // Aqui você pode salvar os dados ou realizar outras ações, por exemplo, enviar para o backend
        System.out.println("Dados salvos: " + username + ", " + email);
    }

    @FXML
    private void changeLanguage() {
        languageManager.toggleLanguage();
        updateLanguage();
    }

    @Override
    public void updateLanguage() {
        logoutButton.setText(languageManager.getText("screens.dashboard.logoutButton"));
        saveButton.setText(languageManager.getText("screens.settings.saveButton"));
        changeLanguageButton.setText(languageManager.getText("screens.settings.changeLanguageButton"));
    }
}
