package com.uefs.system;

import com.uefs.system.view.ViewManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {new ViewManager(primaryStage).showLoginScreen();}

    public static void main(String[] args) {launch(args);}
}