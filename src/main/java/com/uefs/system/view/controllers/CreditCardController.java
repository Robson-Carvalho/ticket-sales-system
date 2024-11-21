package com.uefs.system.view.controllers;

import com.uefs.system.Interface.ILanguageObserver;
import com.uefs.system.emun.SceneEnum;
import com.uefs.system.utils.LanguageManager;
import com.uefs.system.view.NavigationManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class CreditCardController implements ILanguageObserver {
    private final NavigationManager navigationManager = new NavigationManager();
    private final LanguageManager languageManager;

    public CreditCardController(LanguageManager languageManager) {
        this.languageManager = languageManager;
    }

    @FXML private Button logoutButton;
    @FXML private Button btnAddCard;

    @FXML private Button  card;
    @FXML private VBox containerCards;

    @FXML
    private ListView<String> lvCreditCards;

    @FXML
    private Button btnDeleteCard;

    @FXML
    private TextField tfCardNumber;

    @FXML
    private TextField tfCardHolder;

    @FXML
    private void home(){
        navigationManager.setScene(SceneEnum.DASHBOARD);
    }

    @FXML
    private void cards(){
        navigationManager.setScene(SceneEnum.CARDS);
    }

    @FXML
    private void mailBox(){
        navigationManager.setScene(SceneEnum.MAILBOX);
    }

    @FXML
    private void settings(){
        navigationManager.setScene(SceneEnum.SETTINGS);
    }

    @FXML
    private void logout(){
        navigationManager.setScene(SceneEnum.SIGNIN);
    }

    // Lista observável de cartões
    private final ObservableList<String> creditCards = FXCollections.observableArrayList();

    // Inicialização
    @FXML
    public void initialize() {
        // Inicializa a ListView com a lista de cartões
        lvCreditCards.setItems(creditCards);

        // Configura o comportamento do botão de excluir
        btnDeleteCard.setDisable(true);

        // Adiciona um listener para habilitar o botão de excluir apenas quando um cartão for selecionado
        lvCreditCards.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDeleteCard.setDisable(newValue == null);
        });
    }

    // Adicionar um novo cartão
    public void addCard() {
        String cardNumber = tfCardNumber.getText();
        String cardHolder = tfCardHolder.getText();

        if (!cardNumber.isEmpty() && !cardHolder.isEmpty()) {
            // Adiciona o cartão à lista (aqui você pode usar um formato mais complexo, se necessário)
            creditCards.add("Número do Cartão: " + cardNumber + " - Titular: " + cardHolder);
            tfCardNumber.clear();
            tfCardHolder.clear();
        }
    }

    // Excluir cartão
    @FXML
    public void deleteCreditCard() {
        String selectedCard = lvCreditCards.getSelectionModel().getSelectedItem();

        if (selectedCard != null) {
            // Remove o cartão selecionado da lista
            creditCards.remove(selectedCard);
        }
    }

    @Override
    public void updateLanguage() {

    }
}
