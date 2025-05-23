package com.example.veriyapilariprojee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ParaCekmeController {

    @FXML
    private Button button1, button2, button3, button4, button5, digerbutton, geridon;

    @FXML
    private TextField txtDiger;

    @FXML
    private Label txtbakiye;

    private double bakiye = 5000; // örnek bakiye

    public void initialize() {
        guncelleBakiye();
    }

    private void guncelleBakiye() {
        txtbakiye.setText("Bakiyeniz: " + bakiye + " TL");
    }

    @FXML
    public void backtogirissekmesi(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("girissekmesi.fxml"));
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    @FXML
    private void butonlaCek(ActionEvent event) {
        Button tiklanan = (Button) event.getSource();
        String yazi = tiklanan.getText(); // örnek "100 TL"
        double miktar = Double.parseDouble(yazi.replace("TL", "").trim());
        cek(miktar);
    }

    @FXML
    private void digerCekTiklandi(ActionEvent event) {
        try {
            double miktar = Double.parseDouble(txtDiger.getText().trim());
            cek(miktar);
        } catch (NumberFormatException e) {
            alertGoster("Hatalı Giriş", "Lütfen geçerli bir sayı girin.");
        }
    }

    private void cek(double miktar) {
        if (miktar <= 0) {
            alertGoster("Geçersiz Tutar", "Negatif veya sıfır para çekemezsin kanka.");
            return;
        }

        if (miktar > bakiye) {
            alertGoster("Yetersiz Bakiye", "Hesabında o kadar para yok kardeşim, biraz sakin ol. 😅");
        } else {
            bakiye -= miktar;
            guncelleBakiye();
            bilgiAlert("İşlem Başarılı", miktar + " TL başarıyla çekildi. Yeni bakiye: " + bakiye + " TL");
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
