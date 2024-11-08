package com.uefs.system;

import com.uefs.system.view.NavigationManager;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    // quando o main for executado colocar alguns usuários no arquivo .json
    // sempre que um testes for executado, limpar todos os database

    @Override
    public void start(Stage primaryStage) throws IOException {
        NavigationManager navigationManager = NavigationManager.getInstance();
        navigationManager.initialize(primaryStage);

        navigationManager.addScene("signup", "/fxml/signup.fxml");
        navigationManager.addScene("home", "/fxml/home.fxml");

        navigationManager.setScene("signup");
    }

    public static void main(String[] args) {
        launch(args);
    }
}