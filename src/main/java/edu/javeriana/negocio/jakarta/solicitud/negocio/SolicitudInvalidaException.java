package edu.javeriana.negocio.jakarta.solicitud.negocio;

import jakarta.ejb.ApplicationException;

/**
 * Los datos de una solicitud no cumplen las reglas de negocio.
 * Es una excepción de aplicación: el contenedor la entrega tal cual al cliente
 * en lugar de envolverla en una EJBException.
 */
@ApplicationException
public class SolicitudInvalidaException extends RuntimeException {

    public SolicitudInvalidaException(String mensaje) {
        super(mensaje);
    }
}
