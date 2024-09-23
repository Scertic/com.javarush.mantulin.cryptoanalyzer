package org.example.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.example.alphabet.Alphabet;
import org.example.cipher.BruteForce;
import org.example.cipher.CaesarCoder;
import org.example.cipher.StatisticalAnalyzer;

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
            String srcFile = this.srcFile.getText();
            String dstFile = this.dstFile.getText();
            String rFile = this.rFile.getText();
            int process = this.process.getSelectionModel().getSelectedIndex();
            switch (process) {
                case 0 : {
                    int key = -1;
                    try {
                        key = Integer.parseInt(this.key.getText());
                    } catch (NumberFormatException e) {
                        this.key.setPromptText("Введите целое число от 0 до " + (new Alphabet().getSize() - 1));
                        this.key.clear();
                    }
                    new CaesarCoder().encrypt(srcFile, dstFile, key);
                    break;
                }
                case 1 : {
                    int key = -1;
                    try {
                        key = Integer.parseInt(this.key.getText());
                    } catch (NumberFormatException e) {
                        this.key.setPromptText("Введите целое число от 0 до " + (new Alphabet().getSize() - 1));
                        this.key.clear();
                    }
                    new CaesarCoder().decrypt(srcFile, dstFile, key);
                    break;
                }
                case 2 : {
                    new BruteForce().decrypt(srcFile, dstFile, rFile);
                    break;
                }
                case 3 : {
                    int key = new StatisticalAnalyzer().findMostLikelyShift(srcFile, rFile, new Alphabet());
                    new CaesarCoder().decrypt(srcFile, dstFile, key);
                    break;
                }
            }

        });

    }
}
