package com.uefs.system.view.controllers;

import com.uefs.system.Interface.ILanguageObserver;
import com.uefs.system.emun.SceneEnum;
import com.uefs.system.utils.LanguageManager;
import com.uefs.system.view.NavigationManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DashboardController implements ILanguageObserver {
    private final NavigationManager navigationManager = new NavigationManager();
    private final LanguageManager languageManager;

    public DashboardController(LanguageManager languageManager) {this.languageManager = languageManager;}

    @FXML
    private void initialize() {updateLanguage();}

    @FXML
    private Button backButton;

    @FXML
    private void Logout() {
        navigationManager.setScene(SceneEnum.SIGNIN);
    }

    @Override
    public void updateLanguage() {languageManager.loadLanguage();}
}
