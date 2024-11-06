package com.javarush.mantulin.cryptoanalyzer.controller.gui;

import com.javarush.mantulin.cryptoanalyzer.service.cipher.BruteForce;
import com.javarush.mantulin.cryptoanalyzer.service.cipher.CaesarCoder;
import com.javarush.mantulin.cryptoanalyzer.service.cipher.StatisticalAnalyzer;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.javarush.mantulin.cryptoanalyzer.service.Alphabet;
import javafx.stage.FileChooser;

import java.io.File;
import java.nio.file.Path;

public class HelloController {

    @FXML
    private Button button;

    @FXML
    private TextField dstFile;

    @FXML
    private TextField key;

    @FXML
    private ComboBox<?> process;

    @FXML
    private TextField rFile;

    @FXML
    private TextField srcFile;

    @FXML
    void onMouseFileButtonClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = fileChooser.showOpenDialog(this.key.getScene().getWindow());
        if (file != null) {
            Path path = file.getAbsoluteFile().toPath();
            srcFile.setText(path.toString());
            if (dstFile.getText().isBlank()) {
                dstFile.setText(path.getParent() + File.separator + "CRYPTO_" + path.getFileName());
            }
        }
    }

    @FXML
    void onMouseFileRepButtonClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = fileChooser.showOpenDialog(this.key.getScene().getWindow());
        if (file != null) {
            Path path = file.getAbsoluteFile().toPath();
            rFile.setText(path.toString());
        }
    }


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
                        this.key.clear();
                        this.key.setPromptText("Введите целое число от 0 до " + (new Alphabet().getSize() - 1));
                    }
                    try {
                        new CaesarCoder().encrypt(srcFile, dstFile, key);
                    } catch (Exception e) {
                        if (e.getMessage().equals("The key is invalid")) {
                            this.key.clear();
                            this.key.setPromptText("Введите целое число от 0 до " + (new Alphabet().getSize() - 1));
                        }
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
                        this.key.clear();
                        this.key.setPromptText("Введите целое число от 0 до " + (new Alphabet().getSize() - 1));
                    }
                    try {
                        new CaesarCoder().decrypt(srcFile, dstFile, key);
                    } catch (Exception e) {
                        if (e.getMessage().equals("The key is invalid")) {
                            this.key.clear();
                            this.key.setPromptText("Введите целое число от 0 до " + (new Alphabet().getSize() - 1));
                        }
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
                default: {
                    Alert alarm = new Alert(Alert.AlertType.INFORMATION, "Выберите способ!");
                    alarm.show();
                }
            }

        });

    }
}
