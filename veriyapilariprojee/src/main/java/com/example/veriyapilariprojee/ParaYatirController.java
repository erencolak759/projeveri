package com.example.veriyapilariprojee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ParaYatirController {

    @FXML
    private Button button1, button2, button3, button4, button5;

    @FXML
    private TextField txtDiger;

    public void backtogirissekmesi(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("girissekmesi.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    public void butonlaYatir(ActionEvent event) throws IOException {
        Button tiklananButon = (Button) event.getSource();
        String yazi = tiklananButon.getText(); // "50 TL"
        double miktar = Double.parseDouble(yazi.replace("TL", "").trim());

        // Bilgilendirme alert'i
        bilgiAlert("İşlem Başarılı", miktar + " TL hesabınıza başarıyla yatırıldı!");

        // Sahne geçişi
        FXMLLoader loader = new FXMLLoader(getClass().getResource("yatirmabasarili.fxml"));
        Parent root = loader.load();

        paraYatirmaOnay controller = loader.getController();
        controller.setMiktar(miktar);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void digerYatirTiklandi(ActionEvent event) throws IOException {
        try {
            double miktar = Double.parseDouble(txtDiger.getText().trim());

            if (miktar <= 0) {
                alertGoster("Geçersiz Tutar", "Negatif ya da sıfır para yatıramazsın dostum!");
                return;
            }

            bilgiAlert("İşlem Başarılı", miktar + " TL hesabınıza başarıyla yatırıldı!");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("yatirmabasarili.fxml"));
            Parent root = loader.load();

            paraYatirmaOnay controller = loader.getController();
            controller.setMiktar(miktar);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (NumberFormatException e) {
            alertGoster("Hatalı Giriş", "Lütfen geçerli bir sayı gir reis 🙄");
        }
    }

    private void alertGoster(String baslik, String mesaj) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Uyarı");
        alert.setHeaderText(baslik);
        alert.setContentText(mesaj);
        alert.showAndWait();
    }

    private void bilgiAlert(String baslik, String mesaj) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Bilgi");
        alert.setHeaderText(baslik);
        alert.setContentText(mesaj);
        alert.showAndWait();
    }
}
