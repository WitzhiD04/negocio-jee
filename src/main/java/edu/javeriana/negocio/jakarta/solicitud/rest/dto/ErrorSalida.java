package edu.javeriana.negocio.jakarta.solicitud.rest.dto;

/**
 * Respuesta JSON cuando el negocio rechaza una operación.
 */
public class ErrorSalida {

    private String error;

    public ErrorSalida() {
    }

    public ErrorSalida(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
