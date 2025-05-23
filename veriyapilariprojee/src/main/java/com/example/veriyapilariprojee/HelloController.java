package com.example.veriyapilariprojee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private AnchorPane mainscreenanchorpane;
    @FXML
    private ImageView mainscreenimageview;
    @FXML
    private Button mainscreenbutton;

    @FXML
    public void getmusterikaydi(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("musterikaydı.fxml"));
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }


}