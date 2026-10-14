package edu.javeriana.negocio.jakarta.solicitud.modelo;

/**
 * Estados por los que pasa una solicitud de recursos.
 * Toda solicitud nace PENDIENTE y solo puede pasar a ATENDIDA.
 */
public enum EstadoSolicitud {
    PENDIENTE,
    ATENDIDA
}
