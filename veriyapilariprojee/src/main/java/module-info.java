module com.example.veriyapilariprojee {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;
    requires com.google.gson;

    opens com.example.veriyapilariprojee to javafx.fxml, com.google.gson;
    exports com.example.veriyapilariprojee;
}
