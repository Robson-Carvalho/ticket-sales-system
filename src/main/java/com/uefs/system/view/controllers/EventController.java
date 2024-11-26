package com.uefs.system.view.controllers;

import com.uefs.system.Interface.ILanguageObserver;
import com.uefs.system.controller.CommentController;
import com.uefs.system.controller.TicketController;
import com.uefs.system.controller.UserController;
import com.uefs.system.emun.SceneEnum;
import com.uefs.system.model.*;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class EventController implements ILanguageObserver {
    private final NavigationManager navigationManager = new NavigationManager();
    private final AccessibilityManager accessibilityManager = new AccessibilityManager();
    private final CommentController commentController = new CommentController();
    private final UserController userController = new UserController();
    private final TicketController ticketController = new TicketController();
    private final LanguageManager languageManager;
    private final SessionManager sessionManager;
    private final Event event;

    public EventController(LanguageManager languageManager, SessionManager sessionManager, Event event) {
        this.languageManager = languageManager;
        this.sessionManager = sessionManager;
        this.event = event;
    }

    @FXML private void initialize() {
        updateLanguage();
        getComments();
    }

    @FXML private Button homeNavBar;
    @FXML private Button mailBoxNavBar;
    @FXML private Button settingsNavBar;
    @FXML private Button cardsNavBar;
    @FXML private Button buysNavBar;
    @FXML private Text titleMain;
    @FXML private Text subtitle;
    @FXML private Button logoutButton;

    @FXML private VBox listComments;
    @FXML private Label labelComment;
    @FXML private TextField commentField;

    @FXML private Label labelSelector;
    @FXML private ComboBox selectorRating;
    @FXML private Button submitButton;
    @FXML private Button cancelButton;

    @FXML private void navigationToCards(){navigationManager.setScene(SceneEnum.CARDS);}
    @FXML private void navigationToHome(){navigationManager.setScene(SceneEnum.DASHBOARD);}
    @FXML private void navigationToMailBox(){navigationManager.setScene(SceneEnum.MAILBOX);}
    @FXML private void navigationToSettings(){navigationManager.setScene(SceneEnum.SETTINGS);}
    @FXML private void navigationToBuys(){navigationManager.setScene(SceneEnum.BUYS);}

    @FXML
    public void logout(){
        sessionManager.clearUserSession();
        navigationManager.setScene(SceneEnum.SIGNIN);
    }

    @FXML private void handleComment(){
        List<Ticket> tickets = ticketController.getAll();

        boolean isBuy = false;

        User user = userController.getById(UUID.fromString(sessionManager.getID()));

        if (user.getTickets() != null) {
            for (Ticket ticket : tickets) {
                if (user.getTickets().contains(ticket.getId()) && ticket.getEventId().equals(event.getId())) {
                    isBuy = true;
                    break;
                }
            }
        }

        if(isBuy){
            if(Objects.equals(commentField.getText(), "")){
                messageAlert(Alert.AlertType.WARNING, "Escreva algo para comentar.");
                return;
            }

            String ratingString = (String) selectorRating.getValue();
            int rating = Integer.parseInt(ratingString);

            try{
                commentController.create(UUID.fromString(sessionManager.getID()), event.getId(), rating, commentField.getText());
                messageAlert(Alert.AlertType.INFORMATION, "Comentário adicionado com sucesso.");
                this.cancelComment();
            }catch (SecurityException e){
                messageAlert(Alert.AlertType.WARNING, e.getMessage());
            }

            languageManager.notifyObservers();
        }else{
            messageAlert(Alert.AlertType.WARNING, "É necessário ter comprado o ingresso para comentar.");
        }
    }

    @FXML private void cancelComment(){
        commentField.setText("");
        selectorRating.setValue("1");
    }

    @Override public void updateLanguage() {
        Boolean accessibilityIsActive = accessibilityManager.getAccessibilityPropertiesCurrent();

        homeNavBar.setText(languageManager.getText("components.navbar.homeNavBar"));
        mailBoxNavBar.setText(languageManager.getText("components.navbar.mailBoxNavBar"));
        settingsNavBar.setText(languageManager.getText("components.navbar.settingsNavBar"));
        buysNavBar.setText(languageManager.getText("components.navbar.buysNavBar"));
        cardsNavBar.setText(languageManager.getText("components.navbar.cardsNavBar"));
        logoutButton.setText(languageManager.getText("components.navbar.logoutButton"));

        titleMain.setText(languageManager.getText("screens.event.titleMain") +" - "+event.getName());
        subtitle.setText(languageManager.getText("screens.event.subtitle"));

        labelComment.setText(languageManager.getText("screens.event.labelComment"));
        labelSelector.setText(languageManager.getText("screens.event.labelSelector"));

        submitButton.setText(languageManager.getText("screens.event.submitButton"));
        cancelButton.setText(languageManager.getText("screens.event.cancelButton"));

        if (accessibilityIsActive) {
            setFontSize(18, 28, 24,16, 16, 16);
        }else{
            setFontSize(16, 24, 18,14, 14, 14);
        }

        getComments();
    }

    private void setFontSize(double fontSizeNavBar, double fontSizeTitleMain, double fontSizeSubtitle,double fontSizeLabel, double fontSizeField, double fontSizeButton) {
        homeNavBar.setStyle("-fx-font-size: " + fontSizeNavBar + "px;");
        mailBoxNavBar.setStyle("-fx-font-size: " + fontSizeNavBar + "px;");
        settingsNavBar.setStyle("-fx-font-size: " + fontSizeNavBar + "px;");
        buysNavBar.setStyle("-fx-font-size: " + fontSizeNavBar + "px;");
        cardsNavBar.setStyle("-fx-font-size: " + fontSizeNavBar + "px;");
        logoutButton.setStyle("-fx-font-size: " + fontSizeNavBar + "px;");

        titleMain.setStyle("-fx-font-size: " + fontSizeTitleMain + "px;");
        subtitle.setStyle("-fx-font-size: " + fontSizeSubtitle + "px;");

        labelComment.setStyle("-fx-font-size: " + fontSizeLabel + "px;");
        labelSelector.setStyle("-fx-font-size: " + fontSizeLabel + "px;");

        submitButton.setStyle("-fx-font-size: " + fontSizeButton + "px;");
        cancelButton.setStyle("-fx-font-size: " + fontSizeButton + "px;");
    }

    private void messageAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void getComments(){
        List<Comment> comments = commentController.getCommentsByEventId(event.getId());

        listComments.getChildren().clear();

        ScrollPane commentsScrollPane = new ScrollPane();
        commentsScrollPane.setContent(listComments);
        commentsScrollPane.setFitToWidth(true);
        commentsScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        commentsScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        commentsScrollPane.setPrefHeight(Region.USE_COMPUTED_SIZE);

        commentsScrollPane.setStyle(
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

        double fontSizeNameUser = accessibilityIsActive ? 16 : 14;
        double fontSizeContent = accessibilityIsActive ? 16 : 14;

        if (comments.isEmpty()) {
            HBox commentContainer = new HBox();
            VBox textContainer = new VBox();
            textContainer.setSpacing(5);

            Label notFoundComments = new Label("Não há comentários");

            notFoundComments.setStyle("-fx-font-size: " + fontSizeNameUser + "px;");

            textContainer.getChildren().addAll(notFoundComments);

            commentContainer.getChildren().addAll(textContainer);

            eventsVBox.getChildren().add(commentContainer);
        } else {
            for (Comment comment : comments) {
                VBox commentContainer = new VBox();
                commentContainer.setPadding(new Insets(16));
                commentContainer.setSpacing(10);

                commentContainer.setStyle("-fx-background-color: rgba(222,222,222,0.5); -fx-background-radius: 5px;");

                User user = userController.getById(comment.getUserID());

                Label commentUserName = new Label(user.getName());
                commentUserName.setStyle("-fx-font-size: " + fontSizeNameUser + "px; -fx-font-weight: bold;");

                Label commentContent = new Label(comment.getContent());
                commentContent.setStyle("-fx-font-size: " + fontSizeContent + "px; -fx-text-fill: #292929; -fx-text-alignment: justify;");
                commentContent.setWrapText(true);

                Label commentRating = new Label(languageManager.getText("screens.event.ratingName")+ ": "+comment.getRating());
                commentRating.setStyle("-fx-font-size: " + fontSizeContent + "px;");

                commentContainer.getChildren().addAll(commentUserName, commentContent, commentRating);
                eventsVBox.getChildren().add(commentContainer);
            }
        }

        commentsScrollPane.setContent(eventsVBox);

        listComments.getChildren().add(commentsScrollPane);
    }
}
