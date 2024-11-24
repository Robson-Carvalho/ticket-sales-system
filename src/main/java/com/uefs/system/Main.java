package com.uefs.system;

import com.uefs.system.emun.SceneEnum;
import com.uefs.system.mock.Mock;
import com.uefs.system.utils.LanguageManager;
import com.uefs.system.utils.SessionManager;
import com.uefs.system.view.NavigationManager;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        new Mock();

        LanguageManager languageManager = new LanguageManager();
        NavigationManager navigationManager = new NavigationManager();
        SessionManager sessionManager = new SessionManager();

        languageManager.addObserver(navigationManager);

        navigationManager.initialize(primaryStage, languageManager);
        navigationManager.addScene(languageManager, sessionManager, SceneEnum.MAILBOX, "/fxml/mailBox/mailBox.fxml");
        navigationManager.addScene(languageManager, sessionManager, SceneEnum.DASHBOARD, "/fxml/dashboard/dashboard.fxml");
        navigationManager.addScene(languageManager, sessionManager, SceneEnum.SETTINGS, "/fxml/settings/settings.fxml");
        navigationManager.addScene(languageManager, sessionManager, SceneEnum.SIGNIN, "/fxml/signIn/signin.fxml");
        navigationManager.addScene(languageManager, sessionManager, SceneEnum.SIGNUP, "/fxml/signUp/signup.fxml");
        navigationManager.addScene(languageManager, sessionManager, SceneEnum.CARDS, "/fxml/cards/cards.fxml");
        navigationManager.addScene(languageManager, sessionManager, SceneEnum.BUYS, "/fxml/buys/buys.fxml");

        if(sessionManager.isSessionActive()) {
            navigationManager.setScene(SceneEnum.DASHBOARD);
        }else{
            navigationManager.setScene(SceneEnum.SIGNIN);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}