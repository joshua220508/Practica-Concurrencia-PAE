package com.example.practicaconcurrencia.model;

public class CajaCompartida {
    private int cantidadVentas = 0;
    private int totalRecaudado = 0;

    public synchronized void registrarVenta(int monto) {
        cantidadVentas++;
        totalRecaudado += monto;
    }

    public synchronized int getCantidadVentas() {
        return cantidadVentas;
    }

    public synchronized int getTotalRecaudado() {
        return totalRecaudado;
    }

    /** Usamos synchronized porque los tres vendedores comparten la misma caja y así evitamos que se mezclen
     * las ventas o que alguna no se cuente bien cuando trabajan al mismo tiempo.
      */

}
