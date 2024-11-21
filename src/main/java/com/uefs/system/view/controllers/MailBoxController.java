package com.uefs.system.view.controllers;

import com.google.gson.JsonObject;
import com.uefs.system.Interface.ILanguageObserver;
import com.uefs.system.controller.EventController;
import com.uefs.system.emun.SceneEnum;
import com.uefs.system.model.Event;
import com.uefs.system.utils.LanguageManager;
import com.uefs.system.view.NavigationManager;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class MailBoxController implements ILanguageObserver {
    private final NavigationManager navigationManager = new NavigationManager();
    private final LanguageManager languageManager;

    public MailBoxController(LanguageManager languageManager) {this.languageManager = languageManager;}

    @FXML private Button logoutButton;
    @FXML private Button home;
    @FXML private Button mailBox;
    @FXML private Button settings;
    @FXML private VBox containerEvents;

    @FXML
    private void initialize() {
        updateLanguage();
    }

    @FXML private Button btnAddCard;
    @FXML
    private void cards(){
        navigationManager.setScene(SceneEnum.CARDS);
    }

    @FXML
    private void home(){
        navigationManager.setScene(SceneEnum.DASHBOARD);
    }

    @FXML
    private void mailBox(){
        navigationManager.setScene(SceneEnum.MAILBOX);
    }

    @FXML
    private void settings(){
        navigationManager.setScene(SceneEnum.SETTINGS);
    }

    @FXML
    public void logout(){
        navigationManager.setScene(SceneEnum.SIGNIN);
    }

    @Override
    public void updateLanguage() {
        logoutButton.setText(languageManager.getText("screens.dashboard.logoutButton"));
    }
}
