module com.example.javafxapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.net.http;
    requires jdk.jsobject;
    requires org.json;
    requires javafx.swing;

    opens com.example.javafxapp.controller to javafx.fxml;
    opens com.example.javafxapp to javafx.fxml;
    opens com.example.javafxapp.domain to javafx.base;
    exports com.example.javafxapp;
}