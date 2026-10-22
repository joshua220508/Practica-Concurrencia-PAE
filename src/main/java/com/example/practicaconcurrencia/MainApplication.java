
package com.example.practicaconcurrencia;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        URL rutaFXML = MainApplication.class.getResource(
                "/com/example/practicaconcurrencia/venta-view.fxml"
        );

        FXMLLoader fxmlLoader = new FXMLLoader(rutaFXML);
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Procesamiento Concurrente de Ventas");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
