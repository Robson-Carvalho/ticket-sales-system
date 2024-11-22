package com.uefs.system.view.controllers;

import com.google.gson.JsonObject;
import com.uefs.system.Interface.ILanguageObserver;
import com.uefs.system.controller.EventController;
import com.uefs.system.emun.SceneEnum;
import com.uefs.system.model.Event;
import com.uefs.system.utils.LanguageManager;
import com.uefs.system.utils.SessionManager;
import com.uefs.system.view.NavigationManager;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MailBoxController implements ILanguageObserver {
    private final NavigationManager navigationManager = new NavigationManager();
    private final SessionManager sessionManager;
    private final LanguageManager languageManager;

    public MailBoxController(LanguageManager languageManager, SessionManager sessionManager) {
        this.languageManager = languageManager;
        this.sessionManager = sessionManager;
    }

    //    Initialize
    @FXML
    private void initialize() {
        updateLanguage();
    }

    //    Components
    @FXML private Button homeNavBar;
    @FXML private Button mailBoxNavBar;
    @FXML private Button settingsNavBar;
    @FXML private Button cardsNavBar;
    @FXML private Button buysNavBar;
    @FXML private Text titleMain;
    @FXML private Button logoutButton;

    // Navigation
    @FXML private void navigationToCards(){navigationManager.setScene(SceneEnum.CARDS);}

    @FXML private void navigationToHome(){navigationManager.setScene(SceneEnum.DASHBOARD);}

    @FXML private void navigationToMailBox(){navigationManager.setScene(SceneEnum.MAILBOX);}

    @FXML private void navigationToSettings(){navigationManager.setScene(SceneEnum.SETTINGS);}

    @FXML private void navigationToBuys(){navigationManager.setScene(SceneEnum.BUYS);}

    // Life Cycle
    @Override
    public void updateLanguage() {
        homeNavBar.setText(languageManager.getText("components.navbar.homeNavBar"));
        mailBoxNavBar.setText(languageManager.getText("components.navbar.mailBoxNavBar"));
        settingsNavBar.setText(languageManager.getText("components.navbar.settingsNavBar"));
        buysNavBar.setText(languageManager.getText("components.navbar.buysNavBar"));
        titleMain.setText(languageManager.getText("screens.mailBox.titleMain"));
        cardsNavBar.setText(languageManager.getText("components.navbar.cardsNavBar"));
        logoutButton.setText(languageManager.getText("components.navbar.logoutButton"));
    }

    // Logic
    @FXML
    public void logout(){
        sessionManager.clearUserSession();
        navigationManager.setScene(SceneEnum.SIGNIN);
    }
}
