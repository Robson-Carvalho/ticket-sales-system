package com.uefs.system;

import com.uefs.system.utils.LanguageManager;
import com.uefs.system.view.NavigationManager;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        LanguageManager languageManager = new LanguageManager();

        NavigationManager navigationManager = new NavigationManager();

        languageManager.addObserver(navigationManager);

        navigationManager.initialize(primaryStage, languageManager);

        navigationManager.addScene(languageManager,"signin", "/fxml/signin.fxml");
        navigationManager.addScene(languageManager,"signup", "/fxml/signup.fxml");
        navigationManager.addScene(languageManager, "home", "/fxml/home.fxml");

        navigationManager.setScene("signin");
    }

    public static void main(String[] args) {
        launch(args);
    }
}