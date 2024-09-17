package org.example.gui;

import javafx.scene.control.Label;
import javafx.fxml.FXML;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
