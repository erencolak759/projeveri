package com.example.veriyapilariprojee;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class müşterikaydıcontroller {

    @FXML
    private Label normalGirisLabel;

    @FXML
    private Label oncelikliGirisLabel;

    @FXML
    public void initialize() {
        // Normal giriş label tıklanınca normalgiris.fxml açılacak
        normalGirisLabel.setOnMouseClicked(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("normalgiris.fxml"));
                Stage stage = (Stage) normalGirisLabel.getScene().getWindow();
                stage.setScene(new Scene(loader.load()));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Öncelikli giriş label tıklanınca oncelikligiris.fxml açılacak
        oncelikliGirisLabel.setOnMouseClicked(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("oncelikligiris.fxml"));
                Stage stage = (Stage) oncelikliGirisLabel.getScene().getWindow();
                stage.setScene(new Scene(loader.load()));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
