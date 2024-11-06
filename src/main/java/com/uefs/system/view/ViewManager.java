package com.uefs.system.view;

import com.uefs.system.view.Home.Home;
import com.uefs.system.view.Login.Login;

import javafx.stage.Stage;

public record ViewManager(Stage primaryStage) {
    public void showLoginScreen() {
        Login login = new Login(this);
        login.createUI();
    }

    public void showHomeScreen() {
        Home home = new Home(this);
        home.createUI();
    }

}