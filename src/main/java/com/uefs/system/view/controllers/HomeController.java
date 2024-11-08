package com.uefs.system.view.controllers;

import com.uefs.system.view.NavigationManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomeController {

    @FXML
    private Button backButton;

    // Método para lidar com o clique do botão de login
    @FXML
    private void handleBackToLogin() {
        // Aqui você pode fazer validação de login, por exemplo
        // Após o login, mude para a próxima tela
        NavigationManager.getInstance().setScene("signup");
    }
}
