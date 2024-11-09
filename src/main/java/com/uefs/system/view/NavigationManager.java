package com.uefs.system.view;

import com.uefs.system.Interface.ILanguageObserver;
import com.uefs.system.utils.LanguageManager;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class NavigationManager implements ILanguageObserver {
    private static Stage primaryStage;
    private static Map<String, Scene> scenes = new HashMap<>();
    private static LanguageManager languageManager;

    public NavigationManager() {}

    public void initialize(Stage primaryStage, LanguageManager languageManager) {
        NavigationManager.primaryStage = primaryStage;
        NavigationManager.languageManager = languageManager;
        primaryStage.setTitle(languageManager.getText("systemName"));
    }

    private void getScreenDimensions(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        primaryStage.setWidth(screenWidth);
        primaryStage.setHeight(screenHeight);
    }

    public void addScene(LanguageManager languageManager, String name, String fxmlFilePath) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFilePath));


            loader.setControllerFactory(controllerClass -> {
                try {
                    return controllerClass.getDeclaredConstructor(LanguageManager.class).newInstance(languageManager);
                } catch (InstantiationException | NoSuchMethodException | IllegalAccessException |
                         InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            });

            Parent root = loader.load();

            languageManager.addObserver(loader.getController());

            Scene scene = new Scene(root);

            scenes.put(name, scene);
        } catch (IOException e) {
            System.out.println("Erro ao carregar FXML: " + fxmlFilePath);
            e.printStackTrace();
        }
    }

    public void setScene(String name) {
        Scene scene = scenes.get(name);
        if (scene != null) {
            primaryStage.setScene(scene);
            this.getScreenDimensions();
            primaryStage.show();
        } else {
            System.out.println("Cena não encontrada: " + name);
        }
    }

    @Override
    public void updateLanguage() {
        primaryStage.setTitle(languageManager.getText("systemName"));
    }
}
