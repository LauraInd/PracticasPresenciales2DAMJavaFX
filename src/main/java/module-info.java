module com.example.javafxapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.net.http;
    requires jdk.jsobject;
    requires org.json;


    opens com.example.javafxapp to javafx.fxml;
    exports com.example.javafxapp;
}