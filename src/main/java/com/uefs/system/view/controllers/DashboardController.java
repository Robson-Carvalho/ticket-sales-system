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
    private final SessionManager sessionManager = new SessionManager();
    private final LanguageManager languageManager;
    private final EventController eventController = new EventController();

    public DashboardController(LanguageManager languageManager) {this.languageManager = languageManager;}

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
    @FXML private Text titleMainDashboard;
    @FXML private VBox containerEvents;
    @FXML private Button logoutButton;

    // Navigation
    @FXML private void navigationToCards(){navigationManager.setScene(SceneEnum.CARDS);}

    @FXML private void navigationToHome(){
        navigationManager.setScene(SceneEnum.DASHBOARD);
    }

    @FXML private void navigationToMailBox(){
        navigationManager.setScene(SceneEnum.MAILBOX);
    }

    @FXML private void navigationToSettings(){navigationManager.setScene(SceneEnum.SETTINGS);}

    @FXML private void navigationToBuys(){navigationManager.setScene(SceneEnum.DASHBOARD);}

    // Life Cycle
    @Override
    public void updateLanguage() {
        homeNavBar.setText(languageManager.getText("screens.dashboard.homeNavBar"));
        mailBoxNavBar.setText(languageManager.getText("screens.dashboard.mailBoxNavBar"));
        settingsNavBar.setText(languageManager.getText("screens.dashboard.settingsNavBar"));
        buysNavBar.setText(languageManager.getText("screens.dashboard.buysNavBar"));
        titleMainDashboard.setText(languageManager.getText("screens.dashboard.titleMainDashboard"));
        cardsNavBar.setText(languageManager.getText("screens.dashboard.cardsNavBar"));
        logoutButton.setText(languageManager.getText("screens.dashboard.logoutButton"));
    }


    private void getEvents() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        List<Event> events = eventController.getAll();
        containerEvents.getChildren().clear();

        // Criação do ScrollPane para permitir rolagem no containerEvents
        ScrollPane eventsScrollPane = new ScrollPane();
        eventsScrollPane.setContent(containerEvents); // Adiciona o containerEvents no ScrollPane
        eventsScrollPane.setFitToWidth(true); // Ajusta o ScrollPane para a largura da tela
        eventsScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Nunca mostra a barra de rolagem vertical
        eventsScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Nunca mostra a barra de rolagem horizontal
        eventsScrollPane.setPrefHeight(Region.USE_COMPUTED_SIZE); // Altura ajustável

// Remove bordas, fundo e barras de rolagem
        eventsScrollPane.setStyle(
                "-fx-background-color: transparent; " + // Fundo transparente
                        "-fx-background-insets: 0; " + // Remove margens internas do fundo
                        "-fx-padding: 0; " + // Remove padding
                        "-fx-border-color: transparent; " + // Remove borda
                        "-fx-border-width: 0; " + // Remove largura da borda
                        "-fx-scrollbar-track-color: transparent; " + // Remove a trilha das barras
                        "-fx-scrollbar-thumb: transparent; " // Remove o "thumb" das barras
        );

        // Criando um VBox para armazenar todos os eventos
        VBox eventsVBox = new VBox();
        eventsVBox.setSpacing(10);  // Espaçamento entre os eventos
        VBox.setVgrow(eventsVBox, Priority.ALWAYS);  // Permite que o VBox de eventos cresça e ocupe o espaço disponível

        if (events.isEmpty()) {
            // Caso não haja eventos
            HBox eventContainer = new HBox();
            VBox textContainer = new VBox();
            textContainer.setSpacing(5);

            Label eventName = new Label("Não há eventos");
            eventName.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

            textContainer.getChildren().addAll(eventName);

            eventContainer.getChildren().addAll(textContainer);

            eventsVBox.getChildren().add(eventContainer);
        } else {
            // Para cada evento
            for (Event event : events) {
                // Criando o container para o evento
                VBox eventContainer = new VBox();
                eventContainer.setPadding(new Insets(10));
                eventContainer.setSpacing(10);
                eventContainer.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 5px;");

                // Adicionando efeito de hover
                eventContainer.setOnMouseEntered(e -> {
                    eventContainer.setStyle("-fx-background-color: #ccc; -fx-background-radius: 5px; -fx-cursor: hand;");
                });

                eventContainer.setOnMouseExited(e -> {
                    eventContainer.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 5px;");
                });

                eventContainer.setOnMouseClicked(e -> {
                    System.out.println("Clique no evento: " + event.getName());
                });

                // Nome do evento
                Label eventName = new Label(event.getName());
                eventName.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

                // Descrição do evento com quebra de linha
                Label eventDescription = new Label(event.getDescription());
                eventDescription.setStyle("-fx-font-size: 12px; -fx-text-fill: #666666; -fx-text-alignment: justify;");
                eventDescription.setWrapText(true);  // Permite quebra de linha na descrição
                //eventDescription.setMaxWidth(400);  // Define a largura máxima para o texto

                // Data do evento
                Label eventDate = new Label(dateFormat.format(event.getDate()));
                eventDate.setStyle("-fx-font-size: 14px; -fx-text-fill: #666666;");

                // Criando o botão de comprar à direita
                Button buyButton = new Button("Comprar");
                buyButton.setStyle(
                        "-fx-background-color: linear-gradient(to bottom, #4CAF50, #2E7D32);" + // Gradiente de verde
                                "-fx-text-fill: white;" +                                           // Texto branco
                                "-fx-font-size: 14px;" +                                            // Tamanho da fonte
                                "-fx-font-weight: bold;" +                                          // Fonte em negrito
                                "-fx-background-radius: 8px;" +                                    // Bordas arredondadas
                                "-fx-padding: 10 20 10 20;" +                                       // Padding interno
                                "-fx-cursor: hand;" +                                               // Cursor de mão
                                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 5, 0, 0, 1);"    // Sombra
                );
                buyButton.setOnAction(e -> {
                    // Lógica de compra
                    System.out.println("Comprando ingresso para " + event.getName());
                });

// Criando o ComboBox para selecionar o método de pagamento
                ComboBox<String> paymentMethodComboBox = new ComboBox<>();
                paymentMethodComboBox.getItems().add("Boleto");
                List<String> cards = new ArrayList<>(); // Chamando o controller para pegar os cartões
                if (cards != null && !cards.isEmpty()) {
                    paymentMethodComboBox.getItems().addAll(cards);
                }
                paymentMethodComboBox.setValue("Boleto"); // Valor padrão
                paymentMethodComboBox.setStyle(
                        "-fx-background-color: #ffffff;" +                                 // Fundo branco
                                "-fx-border-color: #4CAF50;" +                                     // Borda verde
                                "-fx-border-radius: 8px;" +                                       // Bordas arredondadas
                                "-fx-background-radius: 15px;" +                                   // Bordas arredondadas
                                "-fx-padding: 5 15 5 15;" +                                        // Padding interno
                                "-fx-font-size: 14px;" +                                           // Tamanho da fonte
                                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 3, 0, 0, 1);"    // Sombra
                );

// Configurando layout do evento
                HBox hbox = new HBox();
                hbox.setSpacing(10);
                hbox.getChildren().addAll(paymentMethodComboBox, buyButton); // Adicionando o ComboBox e o botão na mesma linha


                // Organizando os componentes dentro do eventContainer
                eventContainer.getChildren().addAll(eventName, eventDescription, eventDate, hbox);

                // Adicionando o container do evento ao VBox
                eventsVBox.getChildren().add(eventContainer);
            }
        }

        // Definindo o VBox de eventos dentro do ScrollPane
        eventsScrollPane.setContent(eventsVBox);

        // Adicionando o ScrollPane ao container principal
        containerEvents.getChildren().add(eventsScrollPane);
    }


//    private void getEvents() {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        List<Event> events = eventController.getAll();
//        containerEvents.getChildren().clear();
//
//        if (events.isEmpty()) {
//            HBox eventContainer = new HBox();
//            VBox textContainer = new VBox();
//            textContainer.setSpacing(5);
//
//            Label eventName = new Label("Não há eventos");
//            eventName.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
//
//            textContainer.getChildren().addAll(eventName);
//
//            eventContainer.getChildren().addAll(textContainer);
//
//            containerEvents.getChildren().add(eventContainer);
//        } else {
//            for (Event event : events) {
//                VBox eventContainer = new VBox();
//                eventContainer.setPadding(new Insets(10));
//                eventContainer.setSpacing(10);
//                eventContainer.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-radius: 5px; -fx-background-radius: 5px;");
//
//                eventContainer.setOnMouseEntered(e -> {
//                    eventContainer.setStyle(
//                            "-fx-background-color: #f4f4f4; -fx-border-color: #cccccc; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-cursor: hand;"
//                    );
//                });
//
//                eventContainer.setOnMouseExited(e -> {
//                    eventContainer.setStyle(
//                            "-fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-radius: 5px; -fx-background-radius: 5px;"
//                    );
//                });
//
//                eventContainer.setOnMouseClicked(e -> {
//                    System.out.println("oi");
//                });
//
//
//                Label eventName = new Label(event.getName());
//                eventName.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
//
//
//                Label eventDescription = new Label(event.getDescription());
//                eventDescription.setStyle("-fx-font-size: 12px; -fx-text-fill: #666666;");
//
//
//                Label eventDate = new Label(dateFormat.format(event.getDate()));
//                eventDate.setStyle("-fx-font-size: 14px; -fx-text-fill: #666666;");
//
//                eventContainer.getChildren().addAll(eventName, eventDescription, eventDate);
//
//                containerEvents.getChildren().add(eventContainer);
//            }
//        }
//    }

    // Logic
    @FXML
    public void logout(){
        sessionManager.clearUserSession();
        navigationManager.setScene(SceneEnum.SIGNIN);
    }
}
