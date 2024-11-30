package com.uefs.system.view.controllers;

import com.uefs.system.Interface.ILanguageObserver;
import com.uefs.system.controller.CardController;
import com.uefs.system.emun.SceneEnum;
import com.uefs.system.model.Card;
import com.uefs.system.utils.AccessibilityManager;
import com.uefs.system.utils.LanguageManager;
import com.uefs.system.utils.SessionManager;
import com.uefs.system.view.NavigationManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CreditCardController implements ILanguageObserver {
    private final NavigationManager navigationManager = new NavigationManager();
    private final CardController cardController = new CardController();
    private final AccessibilityManager accessibilityManager = new AccessibilityManager();

    private final LanguageManager languageManager;
    private final SessionManager sessionManager;

    public CreditCardController(LanguageManager languageManager, SessionManager sessionManager) {
        this.languageManager = languageManager;
        this.sessionManager = sessionManager;
    }

    private ObservableList<Card> cards;

    @FXML private Button homeNavBar;
    @FXML private Button mailBoxNavBar;
    @FXML private Button settingsNavBar;
    @FXML private Button cardsNavBar;
    @FXML private Button cancelButton;
    @FXML private Button buysNavBar;
    @FXML private Text titleMain;
    @FXML private Button logoutButton;
    @FXML private Button submitButton;
    @FXML private Label labelAccountNumber;
    @FXML private Label labelCardNumber;
    @FXML private Label labelExpirationDate;
    @FXML private Label labelCvv;
    @FXML private Label labelSelector;

    @FXML private TextField fieldAccountNumber;
    @FXML private TextField fieldCardNumber;
    @FXML private DatePicker expirationDate;
    @FXML private TextField fieldCvv;
    @FXML private ComboBox selectorBrand;
    @FXML private ListView<Card> listView;

    @FXML private void initialize() {updateLanguage();setConfig();}
    @FXML private void navigationToCards(){navigationManager.setScene(SceneEnum.CARDS);}
    @FXML private void navigationToHome(){navigationManager.setScene(SceneEnum.DASHBOARD);}
    @FXML private void navigationToMailBox(){navigationManager.setScene(SceneEnum.MAILBOX);}
    @FXML private void navigationToSettings(){navigationManager.setScene(SceneEnum.SETTINGS);}
    @FXML private void navigationToBuys(){navigationManager.setScene(SceneEnum.BUYS);}
    @FXML private void handleRegisterCard(){
        if (fieldAccountNumber != null && fieldCardNumber != null && expirationDate != null && fieldCvv != null && selectorBrand != null) {
            String accountNumber = fieldAccountNumber.getText();
            String cardNumber = fieldCardNumber.getText();
            LocalDate localDate = expirationDate.getValue();

            if(localDate == null) {
                messageAlert(Alert.AlertType.WARNING, languageManager.getText("messagesAlert.fieldNotWrite"));
                return;
            }

            Date expirationDateValue = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            String cvv = fieldCvv.getText();
            String cardBrand = (String) selectorBrand.getValue();

            if (accountNumber.isEmpty() || cardNumber.isEmpty() || cvv.isEmpty() || cardBrand == null) {
                messageAlert(Alert.AlertType.WARNING, languageManager.getText("messagesAlert.fieldNotWrite"));
            } else {
                try{
                    cardController.create(UUID.fromString(sessionManager.getID()), sessionManager.getName(), cardBrand, cardNumber, accountNumber, expirationDateValue, cvv);
                    messageAlert(Alert.AlertType.INFORMATION, languageManager.getText("messagesAlert.cardCreatedSuccess"));
                }catch (IllegalArgumentException e){
                    messageAlert(Alert.AlertType.WARNING, languageManager.getText("messagesAlert.cardCreatedError"));
                }

            }
        } else {
            messageAlert(Alert.AlertType.WARNING, languageManager.getText("messagesAlert.operationError"));
        }

        languageManager.notifyObservers();
    }
    @FXML private void cancelSubmit(){
        fieldCvv.setText("");
        fieldAccountNumber.setText("");
        fieldCardNumber.setText("");
        expirationDate.setValue(null);
    }

    @FXML
    public void logout(){
        sessionManager.clearUserSession();
        navigationManager.setScene(SceneEnum.SIGNIN);
    }

    @Override
    public void updateLanguage() {
        Boolean accessibilityIsActive = accessibilityManager.getAccessibilityPropertiesCurrent();

        homeNavBar.setText(languageManager.getText("components.navbar.homeNavBar"));
        mailBoxNavBar.setText(languageManager.getText("components.navbar.mailBoxNavBar"));
        settingsNavBar.setText(languageManager.getText("components.navbar.settingsNavBar"));
        buysNavBar.setText(languageManager.getText("components.navbar.buysNavBar"));
        logoutButton.setText(languageManager.getText("components.navbar.logoutButton"));
        cardsNavBar.setText(languageManager.getText("components.navbar.cardsNavBar"));
        logoutButton.setText(languageManager.getText("components.navbar.logoutButton"));

        titleMain.setText(languageManager.getText("screens.cards.titleMain"));

        submitButton.setText(languageManager.getText("screens.cards.submitButton"));
        labelAccountNumber.setText(languageManager.getText("screens.cards.labelAccountNumber"));
        labelCardNumber.setText(languageManager.getText("screens.cards.labelCardNumber"));
        labelExpirationDate.setText(languageManager.getText("screens.cards.labelExpirationDate"));
        labelCvv.setText(languageManager.getText("screens.cards.labelCvv"));
        labelSelector.setText(languageManager.getText("screens.cards.labelSelector"));
        cancelButton.setText(languageManager.getText("screens.cards.cancelButton"));

        if (accessibilityIsActive) {
            setFontSize(18, 28, 16, 16, 16);
        }else{
            setFontSize(16, 24, 14, 14, 14);
        }

        getCards();
    }

    private void setFontSize(double fontSizeNavBar, double fontSizeTitleMain, double fontSizeLabel, double fontSizeField, double fontSizeButton) {
        homeNavBar.setStyle("-fx-font-size: " + fontSizeNavBar + "px;");
        mailBoxNavBar.setStyle("-fx-font-size: " + fontSizeNavBar + "px;");
        settingsNavBar.setStyle("-fx-font-size: " + fontSizeNavBar + "px;");
        buysNavBar.setStyle("-fx-font-size: " + fontSizeNavBar + "px;");
        cardsNavBar.setStyle("-fx-font-size: " + fontSizeNavBar + "px;");
        logoutButton.setStyle("-fx-font-size: " + fontSizeNavBar + "px;");

        titleMain.setStyle("-fx-font-size: " + fontSizeTitleMain + "px;");

        labelAccountNumber.setStyle("-fx-font-size: " + fontSizeLabel + "px;");
        labelCvv.setStyle("-fx-font-size: " + fontSizeLabel + "px;");
        labelSelector.setStyle("-fx-font-size: " + fontSizeLabel + "px;");
        labelCardNumber.setStyle("-fx-font-size: " + fontSizeLabel + "px;");
        labelExpirationDate.setStyle("-fx-font-size: " + fontSizeLabel + "px;");

        fieldAccountNumber.setStyle("-fx-font-size: " + fontSizeField + "px;");
        fieldCardNumber.setStyle("-fx-font-size: " + fontSizeField + "px;");
        expirationDate.setStyle("-fx-font-size: " + fontSizeField + "px;");
        fieldCvv.setStyle("-fx-font-size: " + fontSizeField + "px;");
        selectorBrand.setStyle("-fx-font-size: " + fontSizeField + "px;");

        submitButton.setStyle("-fx-font-size: " + fontSizeButton + "px;");
        cancelButton.setStyle("-fx-font-size: " + fontSizeButton + "px;");
    }

    private void setConfig(){
        fieldCvv.setTextFormatter(new TextFormatter<String>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d{0,3}")) {
                return change;
            }
            return null;
        }));

        fieldAccountNumber.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            return (newText.matches("\\d{0,12}")) ? change : null;
        }));


        fieldCardNumber.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            return (newText.matches("\\d{0,16}")) ? change : null;
        }));
    }

    public void getCards() {
        List<Card> cc = new ArrayList<>();

        for (Card c : cardController.getAll()) {
            UUID _id = null;

            if(sessionManager.isSessionActive()){
                _id = UUID.fromString(sessionManager.getID());
            }

            if(c.getUserId().equals(_id)) {
                cc.add(c);
            }
        }

        cards = FXCollections.observableArrayList(cc);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(listView);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        Boolean accessibilityIsActive = accessibilityManager.getAccessibilityPropertiesCurrent();

        double fontSizeButton = accessibilityIsActive ? 16 : 14;
        double fontSizeField = accessibilityIsActive ? 16 : 14;

        listView.setStyle("-fx-background-color: #f4f4f4; -fx-border-color: #ddd; -fx-border-width: 1px;");

        listView.setCellFactory(param -> new ListCell<Card>() {
            @Override
            protected void updateItem(Card card, boolean empty) {
                super.updateItem(card, empty);

                if (card == null || empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Button removeButton = new Button(languageManager.getText("screens.cards.deleteCard"));
                    removeButton.setStyle(
                            "-fx-background-color: #ff574d; " +
                                    "-fx-text-fill: white; " +
                                    "-fx-padding:  8px 48px; " +
                                    "-fx-font-weight: bold;" +
                                    "-fx-border-radius: 5px; " +
                                    "-fx-font-size: "+fontSizeButton+"px;"+
                                    "-fx-background-radius: 8px");

                    removeButton.setOnMouseEntered(e -> {
                        removeButton.setStyle(
                                "-fx-background-color: #ff574d; " +
                                        "-fx-text-fill: white; " +
                                        "-fx-padding:  8px 48px; " +
                                        "-fx-font-weight: bold;" +
                                        "-fx-border-radius: 5px; " +
                                        "-fx-font-size: "+fontSizeButton+"px;"+
                                        "-fx-background-radius: 8px;" +
                                        "-fx-cursor: hand");
                    });

                    removeButton.setOnMouseExited(e -> {
                        removeButton.setStyle(
                                "-fx-background-color: #ff574d; " +
                                        "-fx-text-fill: white; " +
                                        "-fx-padding:  8px 48px; " +
                                        "-fx-font-weight: bold;" +
                                        "-fx-border-radius: 5px; " +
                                        "-fx-background-radius: 8px;"+
                                        "-fx-font-size: "+fontSizeButton+"px;"
                        );
                    });


                    removeButton.setOnAction(e -> {
                        cardController.delete(card.getId());
                        cards.remove(card);
                        messageAlert(Alert.AlertType.INFORMATION, languageManager.getText("messagesAlert.cardDeletedSuccess"));
                        languageManager.notifyObservers();
                    });

                    Label cardLabel = new Label(maskCardNumber(card.getCardNumber()));
                    cardLabel.setStyle("-fx-font-size:"+ fontSizeField+"px;" + "-fx-padding: 5px; -fx-text-fill: #333;");

                    HBox hBox = new HBox(10);
                    hBox.getChildren().add(cardLabel);

                    Region spacer = new Region();
                    HBox.setHgrow(spacer, Priority.ALWAYS);
                    hBox.getChildren().addAll(spacer, removeButton);

                    hBox.setStyle("-fx-background-color: #ffffff; -fx-padding: 10px; -fx-border-radius: 6px;");
                    setGraphic(hBox);
                }
            }
        });


        listView.setItems(cards);
    }

    private String maskCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.length() < 4) {
            return "Número inválido";
        }
        return "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
    }

    private void messageAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
