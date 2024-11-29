package com.uefs.system.view.controllers;

import com.uefs.system.Interface.ILanguageObserver;
import com.uefs.system.controller.*;
import com.uefs.system.controller.EventController;
import com.uefs.system.emun.SceneEnum;
import com.uefs.system.model.Card;
import com.uefs.system.model.Comment;
import com.uefs.system.model.Event;
import com.uefs.system.model.Ticket;
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
import javafx.stage.StageStyle;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class DashboardController implements ILanguageObserver {
    private final NavigationManager navigationManager = new NavigationManager();
    private final AccessibilityManager accessibilityManager = new AccessibilityManager();
    private final EventController eventController = new EventController();
    private final CardController cardController = new CardController();
    private final CommentController commentController = new CommentController();
    private final TicketController ticketController = new TicketController();
    private final Controller controller = new Controller();

    private final SessionManager sessionManager;
    private final LanguageManager languageManager;

    public DashboardController(LanguageManager languageManager, SessionManager sessionManager) {
        this.languageManager = languageManager;
        this.sessionManager = sessionManager;
    }

    @FXML
    private void initialize() {updateLanguage();}

    @FXML private Button homeNavBar;
    @FXML private Button mailBoxNavBar;
    @FXML private Button settingsNavBar;
    @FXML private Button cardsNavBar;
    @FXML private Button buysNavBar;
    @FXML private Text titleMain;
    @FXML private VBox containerEvents;
    @FXML private Button logoutButton;

    @FXML private void navigationToCards(){navigationManager.setScene(SceneEnum.CARDS);}
    @FXML private void navigationToHome(){navigationManager.setScene(SceneEnum.DASHBOARD);}
    @FXML private void navigationToMailBox(){navigationManager.setScene(SceneEnum.MAILBOX);}
    @FXML private void navigationToSettings(){navigationManager.setScene(SceneEnum.SETTINGS);}
    @FXML private void navigationToBuys(){navigationManager.setScene(SceneEnum.BUYS);}

    @FXML public void logout(){
        sessionManager.clearUserSession();
        navigationManager.setScene(SceneEnum.SIGNIN);
    }

    @Override public void updateLanguage() {
        Boolean accessibilityIsActive = accessibilityManager.getAccessibilityPropertiesCurrent();

        homeNavBar.setText(languageManager.getText("components.navbar.homeNavBar"));
        mailBoxNavBar.setText(languageManager.getText("components.navbar.mailBoxNavBar"));
        settingsNavBar.setText(languageManager.getText("components.navbar.settingsNavBar"));
        buysNavBar.setText(languageManager.getText("components.navbar.buysNavBar"));
        cardsNavBar.setText(languageManager.getText("components.navbar.cardsNavBar"));
        logoutButton.setText(languageManager.getText("components.navbar.logoutButton"));

        titleMain.setText(languageManager.getText("screens.dashboard.titleMain"));

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

    private void messageAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void buyTicket(Event event, String seat, String paymentMethod, int seatsPurchased)  {
        int seats = event.getSeats().size();

        if(seatsPurchased == seats && seats != 0){
            messageAlert(Alert.AlertType.WARNING, languageManager.getText("screens.dashboard.messagesAlert.seatsSoldOut"));
        }else if(seats == 0){
            messageAlert(Alert.AlertType.WARNING, languageManager.getText("screens.dashboard.messagesAlert.unregisteredSeats"));
        }else if(Objects.equals(seat, "Selecione") || Objects.equals(seat, "Select")){
            messageAlert(Alert.AlertType.WARNING, languageManager.getText("screens.dashboard.messagesAlert.pleaseSelectAllFields"));
        }else{
            try {
                if(Objects.equals(paymentMethod, "Ticket") || Objects.equals(paymentMethod, "Boleto")){
                    controller.purchase(sessionManager.loadUserSession(), event.getId(), seat);
                }else{
                    controller.purchase(sessionManager.loadUserSession(), event.getId(), seat, paymentMethod);
                }

                messageAlert(Alert.AlertType.INFORMATION, languageManager.getText("screens.dashboard.messagesAlert.purchaseSuccessful")+event.getName()+" - "+seat);
            }catch (Exception e){
                System.out.println("Error: " + e);
                messageAlert(Alert.AlertType.ERROR, "Erro ao comprar ingresso.");
            }
        }

        languageManager.notifyObservers();
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

        Boolean accessibilityIsActive = accessibilityManager.getAccessibilityPropertiesCurrent();

        double fontSizeEventName = accessibilityIsActive ? 18 : 16;
        double fontSizeEventDesc = accessibilityIsActive ? 16 : 14;
        double fontSizeEventDate = accessibilityIsActive ? 16 : 14;
        double fontSizeButton = accessibilityIsActive ? 18 : 16;

        if (events.isEmpty()) {
            HBox eventContainer = new HBox();
            VBox textContainer = new VBox();
            textContainer.setSpacing(5);

            Label eventName = new Label(languageManager.getText("screens.dashboard.notFoundEvents"));

            eventName.setStyle("-fx-font-size: " + fontSizeEventName + "px; -fx-font-weight: bold;");

            textContainer.getChildren().addAll(eventName);

            eventContainer.getChildren().addAll(textContainer);

            eventsVBox.getChildren().add(eventContainer);
        } else {
            for (Event event : events.reversed()) {
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
                    navigationManager.setScene(languageManager, sessionManager, event);
                });

                Label eventName = new Label(event.getName());
                eventName.setStyle("-fx-font-size: " + fontSizeEventName + "px; -fx-font-weight: bold;");

                Label eventDescription = new Label(event.getDescription());
                eventDescription.setStyle("-fx-font-size: " + fontSizeEventDesc + "px; -fx-text-fill: #292929; -fx-text-alignment: justify;");
                eventDescription.setWrapText(true);

                Button buyButton = new Button(languageManager.getText("screens.dashboard.buyButton"));
                buyButton.setStyle(
                        "-fx-background-color: #2E7D32;" +
                                "-fx-text-fill: white;" +
                                "-fx-font-size: " + fontSizeButton + "px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-background-radius: 8px;" +
                                "-fx-padding: 8px 32px;"+
                                "-fx-cursor: hand;"
                );

                ComboBox<String> seatsComboBox = new ComboBox<>();

                List<Ticket> tickets = ticketController.getAll();

                List<Ticket> ticketByCurrentEvent = new ArrayList<>();

                for (Ticket ticket : tickets) {
                    if(ticket.getEventId().equals(event.getId())){
                        ticketByCurrentEvent.add(ticket);
                    }
                }

                List<String> seatsBuys = new ArrayList<>();

                for(Ticket ticket : ticketByCurrentEvent){
                    seatsBuys.add(ticket.getCode());
                }

                for(String seat: event.getSeats()){
                    if(!seatsBuys.contains(seat)){
                        seatsComboBox.getItems().add(seat);
                    }
                }

                seatsComboBox.setValue(languageManager.getText("screens.dashboard.selectOption"));

                seatsComboBox.setStyle(
                        "-fx-background-color: #ffffff;" +
                                "-fx-border-color: #4CAF50;" +
                                "-fx-border-radius: 8px;" +
                                "-fx-font-size: " + fontSizeButton + "px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-background-radius: 8px;" +
                                "-fx-padding: 3px 24px;"+
                                "-fx-cursor: hand;"
                );


                List<Card> cards = cardController.getAll();
                ComboBox<String> paymentMethodComboBox = new ComboBox<>();

                for (Card card : cards) {
                    if(card.getUserId().equals(UUID.fromString(sessionManager.getID()))) {
                        paymentMethodComboBox.getItems().add(card.getCardNumber());
                    }
                }

                paymentMethodComboBox.getItems().add(languageManager.getText("screens.dashboard.paymentMethodDefault"));
                paymentMethodComboBox.setValue(languageManager.getText("screens.dashboard.paymentMethodDefault"));

                paymentMethodComboBox.setStyle(
                        "-fx-background-color: #ffffff;" +
                                "-fx-border-color: #4CAF50;" +
                                "-fx-border-radius: 8px;" +
                                "-fx-font-size: " + fontSizeButton + "px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-background-radius: 8px;" +
                                "-fx-padding: 3px 24px;"+
                                "-fx-cursor: hand;"
                );

                buyButton.setOnAction(e -> {
                    buyTicket(event, seatsComboBox.getValue(), paymentMethodComboBox.getValue(), seatsBuys.size());
                });

                HBox hbox = new HBox();
                hbox.setSpacing(10);
                hbox.getChildren().addAll(paymentMethodComboBox, seatsComboBox, buyButton);

                Label rating = new Label(languageManager.getText("screens.dashboard.ratingText")+": "+calculateRating(event.getId())+"/5");
                rating.setStyle("-fx-font-size: " + fontSizeEventDate + "px; -fx-font-weight: bold;");

                Label price = new Label(languageManager.getText("screens.dashboard.priceText")+": R$ "+event.getPrice());
                price.setStyle("-fx-font-size: " + fontSizeEventDate + "px; -fx-font-weight: bold;");

                Label eventDate = new Label(languageManager.getText("screens.dashboard.dateText")+": "+dateFormat.format(event.getDate()));
                eventDate.setStyle("-fx-font-size: " + fontSizeEventDate + "px; -fx-text-fill: #666666;");

                HBox hboxTwo = new HBox();
                hboxTwo.setSpacing(10);
                hboxTwo.getChildren().addAll(rating, price,eventDate);

                eventContainer.getChildren().addAll(eventName, eventDescription, hboxTwo, hbox);

                eventsVBox.getChildren().add(eventContainer);
            }
        }

        eventsScrollPane.setContent(eventsVBox);

        containerEvents.getChildren().add(eventsScrollPane);
    }

    public String calculateRating(UUID eventID) {
        List<Comment> comments = commentController.getCommentsByEventId(eventID);

        if(comments != null && !comments.isEmpty()){
            int sum = 0;

            for(Comment comment : comments){
                sum += comment.getRating();
            }

            double result = (double) sum / comments.size();

            return formatWithOneDecimalPlace(result);

        }else{
            return "0";
        }
    }

    public static String formatWithOneDecimalPlace(double number) {
        DecimalFormat df = new DecimalFormat("#.#");
        return df.format(number);
    }
}
