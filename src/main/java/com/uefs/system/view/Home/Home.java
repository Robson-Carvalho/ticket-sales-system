package com.uefs.system.view.Home;


import com.uefs.system.view.ViewManager;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class Home {
    private final ViewManager viewManager;

    public Home(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void createUI() {
        Label welcomeLabel = new Label("Welcome to Home!");

        Button backButton = new Button("Back to Login");
        backButton.setOnAction(e -> viewManager.showLoginScreen()); // Ação de navegação para Login

        StackPane root = new StackPane();
        root.getChildren().addAll(welcomeLabel, backButton);

        Scene scene = new Scene(root, 1920, 1080);
        viewManager.primaryStage().setTitle("Home");
        viewManager.primaryStage().setScene(scene);
        viewManager.primaryStage().show();
    }
}