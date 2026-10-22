
package com.example.practicaconcurrencia.controller;

import com.example.practicaconcurrencia.model.CajaCompartida;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;

public class VentasController {

    @FXML
    private ProgressBar progresoVendedor1;

    @FXML
    private ProgressBar progresoVendedor2;

    @FXML
    private ProgressBar progresoVendedor3;

    @FXML
    private Label estadoVendedor1;

    @FXML
    private Label estadoVendedor2;

    @FXML
    private Label estadoVendedor3;

    @FXML
    private Label lblVentasRealizadas;

    @FXML
    private Label lblTotalRecaudado;

    @FXML
    private TextArea txtResultados;

    @FXML
    private Button btnIniciar;

    @FXML
    private Button btnLimpiar;

    private CajaCompartida cajaCompartida;

    @FXML
    private void initialize() {
        cajaCompartida = new CajaCompartida();
    }

    @FXML
    private void iniciarVentas() {

        cajaCompartida = new CajaCompartida();

        reiniciarInterfaz();

        btnIniciar.setDisable(true);
        btnLimpiar.setDisable(true);

        Thread vendedor1 = new Thread(() ->
                procesarVentas(
                        "Vendedor 1",
                        progresoVendedor1,
                        estadoVendedor1
                )
        );

        Thread vendedor2 = new Thread(() ->
                procesarVentas(
                        "Vendedor 2",
                        progresoVendedor2,
                        estadoVendedor2
                )
        );

        Thread vendedor3 = new Thread(() ->
                procesarVentas(
                        "Vendedor 3",
                        progresoVendedor3,
                        estadoVendedor3
                )
        );

        vendedor1.start();
        vendedor2.start();
        vendedor3.start();

        Thread supervisor = new Thread(() -> {
            try {
                vendedor1.join();
                vendedor2.join();
                vendedor3.join();

                Platform.runLater(() -> {
                    txtResultados.appendText(
                            "\n----- Procesamiento finalizado -----\n"
                    );

                    lblVentasRealizadas.setText(
                            String.valueOf(cajaCompartida.getCantidadVentas())
                    );

                    lblTotalRecaudado.setText(
                            "C$" + cajaCompartida.getTotalRecaudado()
                    );

                    btnIniciar.setDisable(false);
                    btnLimpiar.setDisable(false);
                });

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        supervisor.start();
    }

    private void procesarVentas(
            String vendedor,
            ProgressBar pbVendedor,
            Label lblVendedor) {

        for (int i = 1; i <= 5; i++) {
            try {
                Thread.sleep(1000);

                cajaCompartida.registrarVenta(100);

                int numeroVenta = i;
                double progreso = (double) i / 5;

                Platform.runLater(() -> {
                    pbVendedor.setProgress(progreso);

                    lblVendedor.setText(vendedor + " - Venta " + numeroVenta + " de 5");
                    txtResultados.appendText(vendedor + " registró una venta de C$100\n");
                    lblVentasRealizadas.setText(String.valueOf(cajaCompartida.getCantidadVentas()));
                    lblTotalRecaudado.setText("C$" + cajaCompartida.getTotalRecaudado());
                });

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();

                Platform.runLater(() ->
                        lblVendedor.setText(vendedor + " fue interrumpido")
                );

                return;
            }
        }

        Platform.runLater(() ->
                lblVendedor.setText(vendedor + " finalizó sus ventas")
        );
    }

    private void reiniciarInterfaz() {

        progresoVendedor1.setProgress(0);
        progresoVendedor2.setProgress(0);
        progresoVendedor3.setProgress(0);

        estadoVendedor1.setText("Esperando...");
        estadoVendedor2.setText("Esperando...");
        estadoVendedor3.setText("Esperando...");

        lblVentasRealizadas.setText("0");
        lblTotalRecaudado.setText("C$0");

        txtResultados.clear();
    }

    @FXML
    private void limpiar() {
        progresoVendedor1.setProgress(0);
        progresoVendedor2.setProgress(0);
        progresoVendedor3.setProgress(0);

        estadoVendedor1.setText("Esperando...");
        estadoVendedor2.setText("Esperando...");
        estadoVendedor3.setText("Esperando...");

        lblVentasRealizadas.setText("0");
        lblTotalRecaudado.setText("C$0");

        txtResultados.setText("");
    }
}
