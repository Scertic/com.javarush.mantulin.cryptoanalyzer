package com.javarush.mantulin.cryptoanalyzer.gui;

import com.javarush.mantulin.cryptoanalyzer.cipher.BruteForce;
import com.javarush.mantulin.cryptoanalyzer.cipher.CaesarCoder;
import com.javarush.mantulin.cryptoanalyzer.cipher.StatisticalAnalyzer;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import com.javarush.mantulin.cryptoanalyzer.alphabet.Alphabet;

public class HelloController {

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
                case 0: {
                    int key = -1;
                    try {
                        key = Integer.parseInt(this.key.getText());
                    } catch (NumberFormatException e) {
                        this.key.setPromptText("Введите целое число от 0 до " + (new Alphabet().getSize() - 1));
                        this.key.clear();
                    }
                    try {
                        new CaesarCoder().encrypt(srcFile, dstFile, key);
                    } catch (Exception e) {
                        Alert alarm = new Alert(Alert.AlertType.ERROR, e.getMessage());
                        alarm.show();
                    }
                    break;
                }
                case 1: {
                    int key = -1;
                    try {
                        key = Integer.parseInt(this.key.getText());
                    } catch (NumberFormatException e) {
                        this.key.setPromptText("Введите целое число от 0 до " + (new Alphabet().getSize() - 1));
                        this.key.clear();
                    }
                    try {
                        new CaesarCoder().decrypt(srcFile, dstFile, key);
                    } catch (Exception e) {
                        Alert alarm = new Alert(Alert.AlertType.ERROR, e.getMessage());
                        alarm.show();
                    }
                    break;
                }
                case 2: {
                    try {
                        new BruteForce().decrypt(srcFile, dstFile, rFile);
                    } catch (Exception e) {
                        Alert alarm = new Alert(Alert.AlertType.ERROR, e.getMessage());
                        alarm.show();
                    }
                    break;
                }
                case 3: {
                    try {
                        int key = new StatisticalAnalyzer().findMostLikelyShift(srcFile, rFile, new Alphabet());
                        new CaesarCoder().encrypt(srcFile, dstFile, key);
                    } catch (Exception e) {
                        Alert alarm = new Alert(Alert.AlertType.ERROR, e.getMessage());
                        alarm.show();
                    }
                    break;
                }
            }

        });

    }
}
