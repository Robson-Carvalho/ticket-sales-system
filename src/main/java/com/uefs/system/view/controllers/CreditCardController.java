package com.uefs.system.view.controllers;

import com.uefs.system.Interface.ILanguageObserver;
import com.uefs.system.controller.CardController;
import com.uefs.system.emun.SceneEnum;
import com.uefs.system.model.Card;
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

public class CreditCardController implements ILanguageObserver {
    private final NavigationManager navigationManager = new NavigationManager();
    private final LanguageManager languageManager;
    private final SessionManager sessionManager;
    private final CardController cardController = new CardController();

    public CreditCardController(LanguageManager languageManager, SessionManager sessionManager) {
        this.languageManager = languageManager;
        this.sessionManager = sessionManager;
    }

    //    Components
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

    private ObservableList<Card> cards;

    //    Initialize
    @FXML private void initialize() {
        updateLanguage();
        getCards();
        setConfig();
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
            if(c.getUserId().equals(sessionManager.getID())) {
                cc.add(c);
            }
        }

        cards = FXCollections.observableArrayList(cc);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(listView);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);


        listView.setStyle("-fx-background-color: #f4f4f4; -fx-border-color: #ddd; -fx-border-width: 1px;");

        listView.setCellFactory(param -> new ListCell<Card>() {
            @Override
            protected void updateItem(Card card, boolean empty) {
                super.updateItem(card, empty);

                if (card == null || empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Button removeButton = new Button("Remover");
                    removeButton.setStyle(
                            "-fx-background-color: #ff574d; " +
                            "-fx-text-fill: white; " +
                            "-fx-padding:  8px 48px; " +
                            "-fx-font-weight: bold;" +
                            "-fx-border-radius: 5px; " +
                            "-fx-font-size: 16px;"+
                            "-fx-background-radius: 8px");

                    removeButton.setOnMouseEntered(e -> {
                        removeButton.setStyle(
                                "-fx-background-color: #ff574d; " +
                                        "-fx-text-fill: white; " +
                                        "-fx-padding:  8px 48px; " +
                                        "-fx-font-weight: bold;" +
                                        "-fx-border-radius: 5px; " +
                                        "-fx-font-size: 16px;"+
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
                                        "-fx-font-size: 16px;");
                    });


                    removeButton.setOnAction(e -> {
                        cardController.delete(card.getId());
                        cards.remove(card);
                        messageAlert(Alert.AlertType.INFORMATION , "O cartão foi removido com sucesso.");
                        languageManager.notifyObservers();
                    });

                    Label cardLabel = new Label(maskCardNumber(card.getCardNumber()));
                    cardLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px; -fx-text-fill: #333;");

                    HBox hBox = new HBox(10);
                    hBox.getChildren().add(cardLabel);

                    Region spacer = new Region();
                    HBox.setHgrow(spacer, Priority.ALWAYS); // Faz o "spacer" crescer para preencher o espaço disponível
                    hBox.getChildren().addAll(spacer, removeButton);

                    hBox.setStyle("-fx-background-color: #ffffff; -fx-padding: 10px; -fx-border-radius: 6px;");
                    setGraphic(hBox);
                }
            }
        });


// Aplicando a lista de cartões
        listView.setItems(cards);


//        listView.setCellFactory(param -> new ListCell<Card>() {
//            @Override
//            protected void updateItem(Card card, boolean empty) {
//                super.updateItem(card, empty);
//                if (card == null || empty) {
//                    setText(null);
//                    setGraphic(null);
//                } else {
//                    Button removeButton = new Button("Remover");
//                    removeButton.setOnAction(e -> {
//                        cardController.delete(card.getId());
//                        cards.remove(card);
//                        messageAlert(Alert.AlertType.INFORMATION , "O cartão foi removido com sucesso.");
//                        languageManager.notifyObservers();
//                    });
//
//                    Label cardLabel = new Label(maskCardNumber(card.getCardNumber()));
//                    cardLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px; -fx-text-fill: #333;");
//
//                    HBox hBox = new HBox(10);
//                    hBox.getChildren().addAll(cardLabel, removeButton);
//                    setGraphic(hBox);
//                }
//            }
//        });
//
//        listView.setItems(cards);
    }

    private String maskCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.length() < 4) {
            return "Número inválido";
        }
        return "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
    }

    // Navigation
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
                messageAlert(Alert.AlertType.WARNING, "Por favor, preencha todos os campos.");
                return;
            }

            Date expirationDateValue = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            String cvv = fieldCvv.getText();
            String cardBrand = (String) selectorBrand.getValue();

            if (accountNumber.isEmpty() || cardNumber.isEmpty() || cvv.isEmpty() || cardBrand == null) {
                messageAlert(Alert.AlertType.WARNING, "Por favor, preencha todos os campos.");
            } else {
                try{
                    cardController.create(sessionManager.getID(), sessionManager.getName(), cardBrand, cardNumber, accountNumber, expirationDateValue, cvv);
                    getCards();
                    languageManager.notifyObservers();
                    messageAlert(Alert.AlertType.INFORMATION, "Cartão adicionando com suscesso.");
                }catch (IllegalArgumentException e){
                    messageAlert(Alert.AlertType.WARNING, "Informações já eatão em uso.");
                }

            }
        } else {
            messageAlert(Alert.AlertType.ERROR,"Alguns campos não foram inicializados corretamente.");
        }
    }

    @FXML private void cancelSubmit(){
        fieldCvv.setText("");
        fieldAccountNumber.setText("");
        fieldCardNumber.setText("");
        expirationDate.setValue(null);
    }

    private void messageAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Logic
    @FXML
    public void logout(){
        sessionManager.clearUserSession();
        navigationManager.setScene(SceneEnum.SIGNIN);
    }

    // Life Cycle
    @Override
    public void updateLanguage() {
        homeNavBar.setText(languageManager.getText("components.navbar.homeNavBar"));
        mailBoxNavBar.setText(languageManager.getText("components.navbar.mailBoxNavBar"));
        settingsNavBar.setText(languageManager.getText("components.navbar.settingsNavBar"));
        buysNavBar.setText(languageManager.getText("components.navbar.buysNavBar"));
        titleMain.setText(languageManager.getText("screens.cards.titleMain"));
        cardsNavBar.setText(languageManager.getText("components.navbar.cardsNavBar"));
        logoutButton.setText(languageManager.getText("components.navbar.logoutButton"));
        submitButton.setText(languageManager.getText("screens.cards.submitButton"));
        labelAccountNumber.setText(languageManager.getText("screens.cards.labelAccountNumber"));
        labelCardNumber.setText(languageManager.getText("screens.cards.labelCardNumber"));
        labelExpirationDate.setText(languageManager.getText("screens.cards.labelExpirationDate"));
        labelCvv.setText(languageManager.getText("screens.cards.labelCvv"));
        labelSelector.setText(languageManager.getText("screens.cards.labelSelector"));
        cancelButton.setText(languageManager.getText("screens.cards.cancelButton"));
    }

}
