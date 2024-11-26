package com.uefs.system.view.controllers;

import com.uefs.system.Interface.ILanguageObserver;
import com.uefs.system.controller.EventController;
import com.uefs.system.controller.TicketController;
import com.uefs.system.controller.UserController;
import com.uefs.system.emun.SceneEnum;
import com.uefs.system.model.Event;
import com.uefs.system.model.Ticket;
import com.uefs.system.model.User;
import com.uefs.system.utils.AccessibilityManager;
import com.uefs.system.utils.LanguageManager;
import com.uefs.system.utils.SessionManager;
import com.uefs.system.view.NavigationManager;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BuyController implements ILanguageObserver {
    private final NavigationManager navigationManager = new NavigationManager();
    private final AccessibilityManager accessibilityManager = new AccessibilityManager();
    private final EventController eventController = new EventController();
    private final UserController userController = new UserController();
    private final TicketController ticketController = new TicketController();

    private final LanguageManager languageManager;
    private final SessionManager sessionManager;

    public BuyController(LanguageManager languageManager, SessionManager sessionManager) {
        this.languageManager = languageManager;
        this.sessionManager = sessionManager;
    }

    @FXML private void initialize() {updateLanguage();}

    @FXML private Button homeNavBar;
    @FXML private Button mailBoxNavBar;
    @FXML private Button settingsNavBar;
    @FXML private Button cardsNavBar;
    @FXML private Button buysNavBar;
    @FXML private Text titleMain;
    @FXML private Button logoutButton;
    @FXML private VBox containerEvents;

    @FXML private void navigationToCards(){navigationManager.setScene(SceneEnum.CARDS);}
    @FXML private void navigationToHome(){navigationManager.setScene(SceneEnum.DASHBOARD);}
    @FXML private void navigationToMailBox(){navigationManager.setScene(SceneEnum.MAILBOX);}
    @FXML private void navigationToSettings(){navigationManager.setScene(SceneEnum.SETTINGS);}
    @FXML private void navigationToBuys(){navigationManager.setScene(SceneEnum.BUYS);}
    @FXML public void logout(){sessionManager.clearUserSession(); navigationManager.setScene(SceneEnum.SIGNIN);}

    @Override public void updateLanguage() {
        Boolean accessibilityIsActive = accessibilityManager.getAccessibilityPropertiesCurrent();

        homeNavBar.setText(languageManager.getText("components.navbar.homeNavBar"));
        mailBoxNavBar.setText(languageManager.getText("components.navbar.mailBoxNavBar"));
        settingsNavBar.setText(languageManager.getText("components.navbar.settingsNavBar"));
        buysNavBar.setText(languageManager.getText("components.navbar.buysNavBar"));
        titleMain.setText(languageManager.getText("screens.buys.titleMain"));
        cardsNavBar.setText(languageManager.getText("components.navbar.cardsNavBar"));
        logoutButton.setText(languageManager.getText("components.navbar.logoutButton"));

        if (accessibilityIsActive) {
            setFontSize(18, 28, 16, 16, 16);
        }else{
            setFontSize(16, 24, 14, 14, 14);
        }

        getEvents();
    }

    private void setFontSize(double fontSizeNavBar, double fontSizeTitleMain, double fontSizeLabel, double fontSizeField, double fontSizeButton) {
        homeNavBar.setStyle("-fx-font-size: " + fontSizeNavBar + "px;");
        mailBoxNavBar.setStyle("-fx-font-size: " + fontSizeNavBar + "px;");
        settingsNavBar.setStyle("-fx-font-size: " + fontSizeNavBar + "px;");
        buysNavBar.setStyle("-fx-font-size: " + fontSizeNavBar + "px;");
        cardsNavBar.setStyle("-fx-font-size: " + fontSizeNavBar + "px;");
        logoutButton.setStyle("-fx-font-size: " + fontSizeNavBar + "px;");

        titleMain.setStyle("-fx-font-size: " + fontSizeTitleMain + "px;");
    }

    private void getEvents() {
        List<Ticket> tickets = ticketController.getAll();

        List<Event> events = new ArrayList<>();

        List<Ticket> ticketsUser = new ArrayList<>();

        User user = null;

        if(sessionManager.isSessionActive()){
            user = userController.getById(UUID.fromString(sessionManager.getID()));
        }

        if (user != null && user.getTickets() != null) {
            if(!tickets.isEmpty()){
                for (Ticket ticket : tickets) {
                    if (user.getTickets().contains(ticket.getId())) {

                        ticketsUser.add(ticket);
                        Event event = eventController.getById(ticket.getEventId());

                        if(event != null){
                            events.add(event);
                        }
                    }
                }
            }
        }

        containerEvents.getChildren().clear();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
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

        Boolean accessibilityIsActive = accessibilityManager.getAccessibilityPropertiesCurrent();

        double fontSizeEventName = accessibilityIsActive ? 18 : 16;
        double fontSizeEventDesc = accessibilityIsActive ? 16 : 14;
        double fontSizeEventDate = accessibilityIsActive ? 16 : 14;

        if (events.isEmpty()) {
            HBox eventContainer = new HBox();
            VBox textContainer = new VBox();
            textContainer.setSpacing(5);

            Label eventName = new Label(languageManager.getText("screens.buys.notFoundTicket"));

            eventName.setStyle("-fx-font-size: " + fontSizeEventName + "px; -fx-font-weight: bold;");

            textContainer.getChildren().addAll(eventName);

            eventContainer.getChildren().addAll(textContainer);

            eventsVBox.getChildren().add(eventContainer);
        } else {
            for (Ticket ticket : ticketsUser.reversed()) {
                VBox eventContainer = new VBox();
                eventContainer.setPadding(new Insets(16));
                eventContainer.setSpacing(10);

                Event event = eventController.getById(ticket.getEventId());

                eventContainer.setStyle("-fx-background-color: rgba(222,222,222,0.5); -fx-background-radius: 5px;");

                eventContainer.setOnMouseEntered(e -> {
                    eventContainer.setStyle("-fx-background-color: #DEDEDE; -fx-background-radius: 5px; -fx-cursor: hand;");
                });

                eventContainer.setOnMouseExited(e -> {
                    eventContainer.setStyle("-fx-background-color: rgba(222,222,222,0.5); -fx-background-radius: 5px;");
                });

                eventContainer.setOnMouseClicked(e -> {
                    navigationManager.setScene(languageManager, sessionManager, event);
                });

                Label eventName = new Label(event.getName());
                eventName.setStyle("-fx-font-size: " + fontSizeEventName + "px; -fx-font-weight: bold;");

                Label eventDescription = new Label(event.getDescription());
                eventDescription.setStyle("-fx-font-size: " + fontSizeEventDesc + "px; -fx-text-fill: #292929; -fx-text-alignment: justify;");
                eventDescription.setWrapText(true);

                Label eventDate = new Label(dateFormat.format(event.getDate()));
                eventDate.setStyle("-fx-font-size: " + fontSizeEventDate + "px; -fx-text-fill: #666666;");

                Label seat = new Label(languageManager.getText("screens.buys.seatText")+": "+ticket.getCode());
                seat.setStyle("-fx-font-size: " + fontSizeEventName + "px; -fx-font-weight: bold;");

                Label priceEvent = new Label(languageManager.getText("screens.buys.price")+" R$ "+ticket.getPrice());
                priceEvent.setStyle("-fx-font-size: " + fontSizeEventName + "px; -fx-font-weight: bold;");

                HBox hbox = new HBox();
                hbox.setSpacing(10);
                hbox.getChildren().addAll(priceEvent,seat,eventDate);


                eventContainer.getChildren().addAll(eventName, eventDescription, hbox);

                eventsVBox.getChildren().add(eventContainer);
            }
        }

        eventsScrollPane.setContent(eventsVBox);

        containerEvents.getChildren().add(eventsScrollPane);
    }
}
