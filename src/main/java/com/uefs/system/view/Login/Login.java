package com.uefs.system.view.Login;

import com.uefs.system.utils.LanguageManager;
import com.uefs.system.view.ViewManager;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.geometry.Insets;

public class Login {
    private final ViewManager viewManager;
    private final LanguageManager languageManager;
    private Label greetingLabel;
    private Button languageToggleButton;
    private TextField usernameField;
    private PasswordField passwordField;

    public Login(ViewManager viewManager) {
        this.viewManager = viewManager;
        this.languageManager = new LanguageManager();
        this.languageManager.setScreen("signUpScreen");
    }

    public void createUI() {
        // Label de saudação
        greetingLabel = new Label(languageManager.getTranslation("greeting"));
        greetingLabel.setFont(new Font(24));
        greetingLabel.setTextFill(Color.DARKSLATEGRAY);

        // Campos de usuário e senha
        usernameField = new TextField();
        usernameField.setPromptText(languageManager.getTranslation("username"));
        usernameField.setStyle("-fx-padding: 10px; -fx-font-size: 14px;");

        passwordField = new PasswordField();
        passwordField.setPromptText(languageManager.getTranslation("password"));
        passwordField.setStyle("-fx-padding: 10px; -fx-font-size: 14px;");

        // Botão de login
        Button loginButton = new Button(languageManager.getTranslation("login"));
        loginButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px;");
        loginButton.setOnAction(e -> viewManager.showHomeScreen());

        // Botão de alternar idioma
        languageToggleButton = new Button(languageManager.getTranslation("languageToggle"));
        languageToggleButton.setStyle("-fx-background-color: #f1f1f1; -fx-text-fill: #333333; -fx-font-size: 14px; -fx-padding: 8px;");
        languageToggleButton.setOnAction(e -> toggleLanguage());

        // Layout GridPane
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.add(greetingLabel, 0, 0, 2, 1);
        grid.add(usernameField, 0, 1, 2, 1);
        grid.add(passwordField, 0, 2, 2, 1);
        grid.add(languageToggleButton, 0, 3);
        grid.add(loginButton, 1, 3);

        // Contêiner com largura máxima de 1440px
        StackPane root = new StackPane();
        HBox container = new HBox();
        container.setMaxWidth(1440);  // Largura máxima
        container.setAlignment(Pos.CENTER);
        container.setPadding(new Insets(20));
        container.getChildren().add(grid);

        root.getChildren().add(container);

        // Cena e exibição
        Scene scene = new Scene(root, 1920, 1080);
        viewManager.primaryStage().setTitle(languageManager.getTranslation("title"));
        viewManager.primaryStage().setScene(scene);
        viewManager.primaryStage().show();
    }

    private void toggleLanguage() {
        if (languageManager.getCurrentLanguage().equals("pt")) {
            languageManager.setLanguage("en");
        } else {
            languageManager.setLanguage("pt");
        }
        updateUI();
    }

    private void updateUI() {
        greetingLabel.setText(languageManager.getTranslation("greeting"));
        languageToggleButton.setText(languageManager.getTranslation("languageToggle"));
        viewManager.primaryStage().setTitle(languageManager.getTranslation("title"));
        usernameField.setPromptText(languageManager.getTranslation("username"));
        passwordField.setPromptText(languageManager.getTranslation("password"));
    }
}
