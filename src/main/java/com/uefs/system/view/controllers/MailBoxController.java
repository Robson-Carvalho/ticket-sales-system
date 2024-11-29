package com.uefs.system.view.controllers;

import com.google.gson.JsonObject;
import com.uefs.system.Interface.ILanguageObserver;
import com.uefs.system.controller.EventController;
import com.uefs.system.controller.MailController;
import com.uefs.system.emun.SceneEnum;
import com.uefs.system.model.Card;
import com.uefs.system.model.Event;
import com.uefs.system.model.Mail;
import com.uefs.system.model.Ticket;
import com.uefs.system.utils.AccessibilityManager;
import com.uefs.system.utils.LanguageManager;
import com.uefs.system.utils.SessionManager;
import com.uefs.system.view.NavigationManager;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MailBoxController implements ILanguageObserver {
    private final NavigationManager navigationManager = new NavigationManager();
    private final AccessibilityManager accessibilityManager = new AccessibilityManager();
    private final MailController mailController = new MailController();
    private final SessionManager sessionManager;
    private final LanguageManager languageManager;

    public MailBoxController(LanguageManager languageManager, SessionManager sessionManager) {
        this.languageManager = languageManager;
        this.sessionManager = sessionManager;
    }

    @FXML private void initialize() {
        updateLanguage();
    }

    @FXML private Button homeNavBar;
    @FXML private Button mailBoxNavBar;
    @FXML private Button settingsNavBar;
    @FXML private Button cardsNavBar;
    @FXML private Button buysNavBar;
    @FXML private Text titleMain;
    @FXML private Button logoutButton;
    @FXML private VBox listMails;


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
        titleMain.setText(languageManager.getText("screens.mailBox.titleMain"));
        cardsNavBar.setText(languageManager.getText("components.navbar.cardsNavBar"));
        logoutButton.setText(languageManager.getText("components.navbar.logoutButton"));

        if (accessibilityIsActive) {
            setFontSize(18, 28, 16, 16, 16);
        }else{
            setFontSize(16, 24, 14, 14, 14);
        }

        getMails();
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


    private void getMails() {
        List<Mail> mails = new ArrayList<>();

        if(sessionManager.isSessionActive()){
            mails = mailController.getMailsByUserId(UUID.fromString(sessionManager.getID()));
        }

        listMails.getChildren().clear();

        ScrollPane mailsScrollPane = new ScrollPane();
        mailsScrollPane.setContent(listMails);
        mailsScrollPane.setFitToWidth(true);
        mailsScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        mailsScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        mailsScrollPane.setPrefHeight(Region.USE_COMPUTED_SIZE);

        mailsScrollPane.setStyle(
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

        double fontSizeSubjectName = accessibilityIsActive ? 18 : 16;
        double fontSizeContentDesc = accessibilityIsActive ? 16 : 14;

        if (mails.isEmpty()) {
            HBox mailContainer = new HBox();
            VBox textContainer = new VBox();
            textContainer.setSpacing(5);

            Label notFoundEmails = new Label(languageManager.getText("screens.mailBox.notFoundMails"));

            notFoundEmails.setStyle("-fx-font-size: " + fontSizeSubjectName + "px; -fx-font-weight: bold;");

            textContainer.getChildren().addAll(notFoundEmails);

            mailContainer.getChildren().addAll(textContainer);

            eventsVBox.getChildren().add(mailContainer);

        } else {
            for (Mail mail : mails.reversed()) {
                VBox mailContainer = new VBox();
                mailContainer.setPadding(new Insets(16));
                mailContainer.setSpacing(10);

                mailContainer.setStyle("-fx-background-color: rgba(222,222,222,0.5); -fx-background-radius: 5px;");

                Label eventName = new Label(mail.getSubject());
                eventName.setStyle("-fx-font-size: " + fontSizeSubjectName + "px; -fx-font-weight: bold;");

                Label eventDescription = new Label(mail.getContent());
                eventDescription.setStyle("-fx-font-size: " + fontSizeContentDesc + "px; -fx-text-fill: #292929; -fx-text-alignment: justify;");
                eventDescription.setWrapText(true);

                mailContainer.getChildren().addAll(eventName, eventDescription);
                eventsVBox.getChildren().add(mailContainer);
            }
        }

        mailsScrollPane.setContent(eventsVBox);

        listMails.getChildren().add(mailsScrollPane);
    }
}
