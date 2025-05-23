package com.example.veriyapilariprojee;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class normalgirisController {

    @FXML
    private AnchorPane Normalgirişanchorpane;

    @FXML
    private Button normalgirisgeridon;

    @FXML
    private Button normalgirisgirisyap;

    @FXML
    private TextField normalgiristc;

    @FXML
    private TextField normalgiristextfieldadsoyad;

    @FXML
    private Label txt1;

    private final String JSON_DOSYA_YOLU = "C:\\veri_yapilari\\veriyapilariprojee\\src\\kuyruk.json";

    private static class Node {
        Musteri data;
        Node next;

        public Node(Musteri data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head = null;
    private Node tail = null;

    private void enqueue(Musteri m) {
        Node newNode = new Node(m);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    private void kuyruguJsondanYukle() {
        Gson gson = new Gson();
        head = null;
        tail = null;

        try {
            if (Files.exists(Paths.get(JSON_DOSYA_YOLU))) {
                Reader reader = Files.newBufferedReader(Paths.get(JSON_DOSYA_YOLU));
                Type listeTipi = new TypeToken<List<Musteri>>() {}.getType();
                List<Musteri> musteriler = gson.fromJson(reader, listeTipi);
                reader.close();

                if (musteriler != null) {
                    for (Musteri m : musteriler) {
                        enqueue(m);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            txt1.setText("Dosya okunurken hata oluştu!");
        }
    }

    private void kuyruguJsoneKaydet() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (Writer writer = Files.newBufferedWriter(Paths.get(JSON_DOSYA_YOLU))) {
            List<Musteri> liste = new java.util.ArrayList<>();
            Node temp = head;
            while (temp != null) {
                liste.add(temp.data);
                temp = temp.next;
            }
            gson.toJson(liste, writer);
        } catch (IOException e) {
            e.printStackTrace();
            txt1.setText("Dosya yazılırken hata oluştu!");
        }
    }

    private int onundeKacKisiVar(String adSoyad, String tc) {
        int count = 0;
        Node temp = head;

        while (temp != null && !(temp.data.getTc().equals(tc) && temp.data.getAdSoyad().equalsIgnoreCase(adSoyad))) {
            count++;
            temp = temp.next;
        }

        if (temp == null) return -1;

        return count;
    }

    private void alertGoster(String baslik, String mesaj) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(baslik);
        alert.setHeaderText(null);
        alert.setContentText(mesaj);
        alert.showAndWait();
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
        String adSoyad = normalgiristextfieldadsoyad.getText().trim();
        String tc = normalgiristc.getText().trim();

        if (adSoyad.isEmpty() || tc.isEmpty()) {
            txt1.setText("Ad Soyad ve TC boş olamaz!");
            return;
        }

        kuyruguJsondanYukle();

        Node temp = head;
        boolean bulundu = false;

        while (temp != null) {
            if (temp.data.getTc().equals(tc) && temp.data.getAdSoyad().equalsIgnoreCase(adSoyad)) {
                if (temp.data.isOncelikli()) {
                    alertGoster("Hatalı Giriş", "Zaten öncelikli kaydınız var.\nLütfen öncelikli giriş sekmesinden giriş yapınız.");
                    return;
                } else {
                    bulundu = true;
                    break;
                }
            }
            temp = temp.next;
        }

        if (!bulundu) {
            enqueue(new Musteri(adSoyad, tc, false));
            kuyruguJsoneKaydet();
        }

        int onunde = onundeKacKisiVar(adSoyad, tc);
        if (onunde > 0) {
            alertGoster("Sıra Var!", "Sıraya alındınız.\nÖnünüzde " + onunde + " kişi var, sıranın size gelmesini bekleyiniz.");
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("girissekmesi.fxml"));
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(loader.load());

        girissekmesiController controller = loader.getController();
        controller.setAdSoyad(adSoyad);

        stage.setScene(scene);
        stage.show();
    }
}
