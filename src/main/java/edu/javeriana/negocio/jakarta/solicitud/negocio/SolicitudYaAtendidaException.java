package edu.javeriana.negocio.jakarta.solicitud.negocio;

import jakarta.ejb.ApplicationException;

/**
 * Se intentó atender una solicitud que ya estaba en estado ATENDIDA.
 */
@ApplicationException
public class SolicitudYaAtendidaException extends RuntimeException {

    public SolicitudYaAtendidaException(long id) {
        super("La solicitud " + id + " ya fue atendida");
    }
}
