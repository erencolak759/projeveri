package com.example.veriyapilariprojee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class paraYatirmaOnay {

    @FXML
    private Label txtbakiye;

    private double miktar;

    public void setMiktar(double miktar) {
        this.miktar = miktar;
        BakiyeManager.paraEkle(miktar);
        txtbakiye.setText( BakiyeManager.getBakiye() + " TL");
    }

    public void backtogirissekmesi2(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("girissekmesi.fxml"));
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }
}
