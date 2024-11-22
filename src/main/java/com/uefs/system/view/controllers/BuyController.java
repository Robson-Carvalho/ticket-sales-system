package com.uefs.system.view.controllers;

import com.uefs.system.Interface.ILanguageObserver;
import com.uefs.system.emun.SceneEnum;
import com.uefs.system.utils.LanguageManager;
import com.uefs.system.utils.SessionManager;
import com.uefs.system.view.NavigationManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class BuyController implements ILanguageObserver {
    private final NavigationManager navigationManager = new NavigationManager();
    private final LanguageManager languageManager;
    private final SessionManager sessionManager;

    public BuyController(LanguageManager languageManager, SessionManager sessionManager) {
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


    // Logic
    @FXML
    public void logout(){
        sessionManager.clearUserSession();
        navigationManager.setScene(SceneEnum.SIGNIN);
    }

    // Life Cycle
    @Override
    public void updateLanguage() {
        homeNavBar.setText(languageManager.getText("components.navbar.homeNavBar"));
        mailBoxNavBar.setText(languageManager.getText("components.navbar.mailBoxNavBar"));
        settingsNavBar.setText(languageManager.getText("components.navbar.settingsNavBar"));
        buysNavBar.setText(languageManager.getText("components.navbar.buysNavBar"));
        titleMain.setText(languageManager.getText("screens.buys.titleMain"));
        cardsNavBar.setText(languageManager.getText("components.navbar.cardsNavBar"));
        logoutButton.setText(languageManager.getText("components.navbar.logoutButton"));
    }

}
