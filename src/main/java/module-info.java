module com.example.practicaconcurrencia {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.practicaconcurrencia.controller to javafx.fxml;
    opens com.example.practicaconcurrencia to javafx.fxml;
    exports com.example.practicaconcurrencia;
}