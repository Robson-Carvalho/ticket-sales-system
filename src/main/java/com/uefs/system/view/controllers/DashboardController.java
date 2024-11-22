package com.uefs.system.view.controllers;

import com.uefs.system.Interface.ILanguageObserver;
import com.uefs.system.controller.EventController;
import com.uefs.system.emun.SceneEnum;
import com.uefs.system.model.Event;
import com.uefs.system.utils.LanguageManager;
import com.uefs.system.utils.SessionManager;
import com.uefs.system.view.NavigationManager;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DashboardController implements ILanguageObserver {
    private final NavigationManager navigationManager = new NavigationManager();
    private final SessionManager sessionManager;
    private final LanguageManager languageManager;
    private final EventController eventController = new EventController();

    public DashboardController(LanguageManager languageManager, SessionManager sessionManager) {
        this.languageManager = languageManager;
        this.sessionManager = sessionManager;
    }

    //    Initialize
    @FXML
    private void initialize() {
        updateLanguage();
        getEvents();
    }

    //    Components
    @FXML private Button homeNavBar;
    @FXML private Button mailBoxNavBar;
    @FXML private Button settingsNavBar;
    @FXML private Button cardsNavBar;
    @FXML private Button buysNavBar;
    @FXML private Text titleMain;
    @FXML private VBox containerEvents;
    @FXML private Button logoutButton;

    // Navigation
    @FXML private void navigationToCards(){navigationManager.setScene(SceneEnum.CARDS);}

    @FXML private void navigationToHome(){navigationManager.setScene(SceneEnum.DASHBOARD);}

    @FXML private void navigationToMailBox(){navigationManager.setScene(SceneEnum.MAILBOX);}

    @FXML private void navigationToSettings(){navigationManager.setScene(SceneEnum.SETTINGS);}

    @FXML private void navigationToBuys(){navigationManager.setScene(SceneEnum.BUYS);}

    // Life Cycle
    @Override
    public void updateLanguage() {
        homeNavBar.setText(languageManager.getText("components.navbar.homeNavBar"));
        mailBoxNavBar.setText(languageManager.getText("components.navbar.mailBoxNavBar"));
        settingsNavBar.setText(languageManager.getText("components.navbar.settingsNavBar"));
        buysNavBar.setText(languageManager.getText("components.navbar.buysNavBar"));
        titleMain.setText(languageManager.getText("screens.dashboard.titleMain"));
        cardsNavBar.setText(languageManager.getText("components.navbar.cardsNavBar"));
        logoutButton.setText(languageManager.getText("components.navbar.logoutButton"));
    }

    // Logic
    @FXML
    public void logout(){
        sessionManager.clearUserSession();
        navigationManager.setScene(SceneEnum.SIGNIN);
    }

    private void getEvents() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        List<Event> events = eventController.getAll();

        containerEvents.getChildren().clear();

        ScrollPane eventsScrollPane = new ScrollPane();
        eventsScrollPane.setContent(containerEvents);
        eventsScrollPane.setFitToWidth(true);
        eventsScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        eventsScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        eventsScrollPane.setPrefHeight(Region.USE_COMPUTED_SIZE);



        eventsScrollPane.setStyle(
                "-fx-background-color: transparent; " +
                        "-fx-background-insets: 0; " +
                        "-fx-padding: 0; " +
                        "-fx-border-color: transparent; " +
                        "-fx-border-width: 0; " +
                        "-fx-scrollbar-track-color: transparent; " +
                        "-fx-scrollbar-thumb: transparent; "
        );


        VBox eventsVBox = new VBox();
        eventsVBox.setSpacing(16);
        eventsVBox.setStyle(
                "-fx-background-color: #fff; " +
                "-fx-border-color: transparent; " +
                "-fx-border-width: 0;" +
                "-fx-padding: 0 0 64px 0"
        );

        VBox.setVgrow(eventsVBox, Priority.ALWAYS);



        if (events.isEmpty()) {
            HBox eventContainer = new HBox();
            VBox textContainer = new VBox();
            textContainer.setSpacing(5);

            Label eventName = new Label("Não há eventos");
            eventName.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

            textContainer.getChildren().addAll(eventName);

            eventContainer.getChildren().addAll(textContainer);

            eventsVBox.getChildren().add(eventContainer);
        } else {
            for (Event event : events) {
                VBox eventContainer = new VBox();
                eventContainer.setPadding(new Insets(16));
                eventContainer.setSpacing(10);

                eventContainer.setStyle("-fx-background-color: rgba(222,222,222,0.5); -fx-background-radius: 5px;");


                eventContainer.setOnMouseEntered(e -> {
                    eventContainer.setStyle("-fx-background-color: #DEDEDE; -fx-background-radius: 5px; -fx-cursor: hand;");
                });

                eventContainer.setOnMouseExited(e -> {
                    eventContainer.setStyle("-fx-background-color: rgba(222,222,222,0.5); -fx-background-radius: 5px;");
                });

                eventContainer.setOnMouseClicked(e -> {
                    System.out.println("Clique no evento: " + event.getName());
                });

                Label eventName = new Label(event.getName());
                eventName.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

                Label eventDescription = new Label(event.getDescription());
                eventDescription.setStyle("-fx-font-size: 14px; -fx-text-fill: #292929; -fx-text-alignment: justify;");
                eventDescription.setWrapText(true);

                Label eventDate = new Label(dateFormat.format(event.getDate()));
                eventDate.setStyle("-fx-font-size: 14px; -fx-text-fill: #666666;");

                Button buyButton = new Button("Comprar");
                buyButton.setStyle(
                        "-fx-background-color: #2E7D32;" +
                                "-fx-text-fill: white;" +
                                "-fx-font-size: 16px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-background-radius: 8px;" +
                                "-fx-padding: 8px 32px;"+
                                "-fx-cursor: hand;"
                );
                buyButton.setOnAction(e -> {
                    System.out.println("Comprando ingresso para " + event.getName());
                });

                ComboBox<String> paymentMethodComboBox = new ComboBox<>();
                paymentMethodComboBox.getItems().add("Boleto");
                List<String> cards = new ArrayList<>();

                paymentMethodComboBox.setValue("Boleto");
                paymentMethodComboBox.setStyle(
                        "-fx-background-color: #ffffff;" +
                                "-fx-border-color: #4CAF50;" +
                                "-fx-border-radius: 8px;" +
                                "-fx-font-size: 16px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-background-radius: 8px;" +
                                "-fx-padding: 3px 24px;"+
                                "-fx-cursor: hand;"

                );


                HBox hbox = new HBox();
                hbox.setSpacing(10);
                hbox.getChildren().addAll(paymentMethodComboBox, buyButton);

                eventContainer.getChildren().addAll(eventName, eventDescription, eventDate, hbox);

                eventsVBox.getChildren().add(eventContainer);
            }
        }

        eventsScrollPane.setContent(eventsVBox);

        containerEvents.getChildren().add(eventsScrollPane);
    }


}
