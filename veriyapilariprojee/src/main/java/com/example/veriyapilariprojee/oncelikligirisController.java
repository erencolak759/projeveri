package com.example.veriyapilariprojee;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class oncelikligirisController {

    @FXML
    private TextField oncelikligiristextfieldadsoyad;

    @FXML
    private TextField oncelikligiristc;

    @FXML
    private Button oncelikligirisgirisyap;

    @FXML
    private Button öncelikligirisgeridön;

    @FXML
    private ChoiceBox<String> chocicebox;


    private final String JSON_DOSYA_YOLU = "C:\\veri_yapilari\\veriyapilariprojee\\src\\kuyruk.json";

    @FXML
    public void initialize() {
        chocicebox.getItems().addAll("Yaş > 65", "Engelli", "Hamile");
    }

    @FXML
    public void backtomusterikaydi(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("musterikaydı.fxml"));
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    @FXML
    public void gotogirissekmesi(ActionEvent event) throws IOException {
        String adSoyad = oncelikligiristextfieldadsoyad.getText().trim();
        String tc = oncelikligiristc.getText().trim();
        String neden = chocicebox.getValue();

        if (adSoyad.isEmpty() || tc.isEmpty() || neden == null) {
            showAlert("Hata", "Lütfen tüm alanları doldur.");
            return;
        }

        Musteri yeniMusteri = new Musteri(adSoyad, tc, true, neden);

        List<Musteri> kuyruk = okuJSON();

        boolean zatenVar = false;
        for (Musteri m : kuyruk) {
            if (Objects.equals(m.getTc(), tc) &&
                    Objects.equals(m.getAdSoyad(), adSoyad) &&
                    Objects.equals(m.getOncelikNedeni(), neden)) {
                zatenVar = true;
                break;
            }
        }

        if (!zatenVar) {
            kuyruk.add(0, yeniMusteri); // Öncelikli olduğu için başa ekle
            yazJSON(kuyruk);
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("girissekmesi.fxml"));
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(loader.load());

        girissekmesiController controller = loader.getController();
        controller.setAdSoyad(adSoyad);

        stage.setScene(scene);
        stage.show();
    }

    private List<Musteri> okuJSON() {
        Gson gson = new Gson();
        List<Musteri> liste = new ArrayList<>();
        try {
            if (Files.exists(Paths.get(JSON_DOSYA_YOLU))) {
                Reader reader = Files.newBufferedReader(Paths.get(JSON_DOSYA_YOLU));
                Type listType = new TypeToken<List<Musteri>>() {}.getType();
                liste = gson.fromJson(reader, listType);
                reader.close();
                if (liste == null) liste = new ArrayList<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return liste;
    }

    private void yazJSON(List<Musteri> liste) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (Writer writer = Files.newBufferedWriter(Paths.get(JSON_DOSYA_YOLU))) {
            gson.toJson(liste, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String baslik, String mesaj) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(baslik);
        alert.setHeaderText(null);
        alert.setContentText(mesaj);
        alert.showAndWait();
    }
}
