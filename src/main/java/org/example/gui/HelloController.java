package org.example.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.example.alphabet.Alphabet;

public class HelloController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button button;

    @FXML
    private TextField dstFile;

    @FXML
    private ComboBox<?> process;

    @FXML
    private TextField rFile;

    @FXML
    private TextField srcFile;

    @FXML
    private TextField key;


    @FXML
    void initialize() {
        button.setOnAction(actionEvent -> {
            String srcFile = "";
            String dstFile = "";
            String rFile = "";
            int key = -1;
            try {
                key = Integer.parseInt(this.key.getText());
            } catch (NumberFormatException e) {
                this.key.setPromptText("Введите целое число от 0 до " + (new Alphabet().getSize() - 1));
                this.key.clear();
            }
        });

    }
}
