package com.uefs.system.view.controllers;

import com.uefs.system.Interface.ILanguageObserver;
import com.uefs.system.utils.LanguageManager;
import com.uefs.system.view.NavigationManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomeController implements ILanguageObserver {
    private final NavigationManager navigationManager = new NavigationManager();
    private final LanguageManager languageManager;

    public HomeController(LanguageManager languageManager) {this.languageManager = languageManager;}

    @FXML
    private void initialize() {updateLanguage();}

    @FXML
    private Button backButton;

    @FXML
    private void Logout() {
        navigationManager.setScene("signin");
    }

    @Override
    public void updateLanguage() {languageManager.loadLanguage();}
}
