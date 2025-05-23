package com.example.veriyapilariprojee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class girissekmesiController {

    @FXML
    private Label girissekmelabelhosgeldin;

    @FXML
    private Button girissekmebutonparayatır;

    @FXML
    private Button girissekmebutonparacekme;

    @FXML
    private Button girissekmebutongeridon;

    @FXML
    private Label girissekmelabeluyari;

    @FXML
    private Button ödemeislemleributton;

    @FXML
    private Button buttonbasvuruislemleri;

    public void setAdSoyad(String adSoyad) {
        girissekmelabelhosgeldin.setText("Hoşgeldiniz: " + adSoyad);
    }

    public void backtonormalgiris(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("musterikaydı.fxml"));
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    public void parayatir(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("parayatir.fxml"));
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    public void paracek(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("paracek.fxml"));
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }
    @FXML
    public void butonPressed() {
        girissekmebutonparayatır.setScaleX(0.97);
        girissekmebutonparayatır.setScaleY(0.97);
        girissekmebutonparayatır.setTranslateY(2);
    }

    @FXML
    public void butonReleased() {
        girissekmebutonparayatır.setScaleX(1.0);
        girissekmebutonparayatır.setScaleY(1.0);
        girissekmebutonparayatır.setTranslateY(0);
    }
}
