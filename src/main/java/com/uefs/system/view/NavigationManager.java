package com.uefs.system.view;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NavigationManager {

    private static Stage primaryStage;
    private static Map<String, Scene> scenes = new HashMap<>();
    private static NavigationManager instance;

    private NavigationManager() {}

    public static NavigationManager getInstance() {
        if (instance == null) {
            instance = new NavigationManager();
        }
        return instance;
    }

    public void initialize(Stage primaryStage) {
        NavigationManager.primaryStage = primaryStage;
        primaryStage.setTitle("Sistema de Venda de Ingressos");
    }

    public void addScene(String name, String fxmlFilePath) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFilePath));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            scenes.put(name, scene);
        } catch (IOException e) {
            System.out.println("Erro ao carregar FXML: " + fxmlFilePath);
            e.printStackTrace(); // Exibe o stack trace completo
        }
    }

    public void setScene(String name) {
        Scene scene = scenes.get(name);
        if (scene != null) {
            primaryStage.setScene(scene);
            primaryStage.setWidth(1920);
            primaryStage.setHeight(1080);
            primaryStage.show();
        } else {
            System.out.println("Cena não encontrada: " + name);
        }
    }
}
