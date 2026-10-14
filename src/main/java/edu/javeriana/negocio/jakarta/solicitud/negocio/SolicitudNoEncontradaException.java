package edu.javeriana.negocio.jakarta.solicitud.negocio;

import jakarta.ejb.ApplicationException;

/**
 * No existe una solicitud con el identificador indicado.
 */
@ApplicationException
public class SolicitudNoEncontradaException extends RuntimeException {

    public SolicitudNoEncontradaException(long id) {
        super("No existe una solicitud con id " + id);
    }
}
