package com.uefs.system.view;

import com.uefs.system.Interface.ILanguageObserver;
import com.uefs.system.emun.SceneEnum;
import com.uefs.system.model.Event;
import com.uefs.system.utils.LanguageManager;
import com.uefs.system.utils.SessionManager;
import javafx.fxml.LoadException;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class NavigationManager implements ILanguageObserver {
    private static Stage primaryStage;
    private static final Map<String, Scene> scenes = new HashMap<>();
    private static LanguageManager languageManager;

    public NavigationManager() {}

    public void initialize(Stage primaryStage, LanguageManager languageManager) {
        NavigationManager.primaryStage = primaryStage;
        NavigationManager.languageManager = languageManager;
        primaryStage.setTitle(languageManager.getText("system.titleBar"));
    }

    private void getScreenDimensions(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        primaryStage.setWidth(screenWidth);
        primaryStage.setHeight(screenHeight);
        primaryStage.setResizable(false);
    }

    public void addScene(LanguageManager languageManager, SessionManager sessionManager, SceneEnum screen, String fxmlFilePath) {
        String name = screen.getValue();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFilePath));

            loader.setControllerFactory(controllerClass -> {
                try {
                    return controllerClass.getDeclaredConstructor(LanguageManager.class, SessionManager.class).newInstance(languageManager, sessionManager);
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
            System.out.println("Erro ao carregar FXML: " + fxmlFilePath + "\n" + e.getMessage()+ "\n" + e.getCause());
        }
    }

    public void setScene(SceneEnum screen) {
        String name = screen.getValue();

        Scene scene = scenes.get(name);
        if (scene != null) {
            primaryStage.setScene(scene);
            this.getScreenDimensions();
            primaryStage.show();
        } else {
            System.out.println("Cena não encontrada: " + name);
        }
    }

    public void setScene(LanguageManager languageManager, SessionManager sessionManager, Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/event/event.fxml"));

            loader.setControllerFactory(controllerClass -> {
                try {
                    Constructor<?> constructor = controllerClass.getDeclaredConstructor(LanguageManager.class, SessionManager.class, Event.class);
                    constructor.setAccessible(true);
                    return constructor.newInstance(languageManager, sessionManager, event);
                } catch (InstantiationException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException("Erro ao criar controlador para a tela de evento: " + event.getName());
                }
            });

            Parent root = loader.load();

            languageManager.addObserver(loader.getController());

            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            this.getScreenDimensions();
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateLanguage() {
        primaryStage.setTitle(languageManager.getText("system.titleBar"));
    }
}
