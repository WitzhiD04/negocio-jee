package edu.javeriana.negocio.jakarta.solicitud.rest;

import edu.javeriana.negocio.jakarta.solicitud.negocio.SolicitudInvalidaException;
import edu.javeriana.negocio.jakarta.solicitud.rest.dto.ErrorSalida;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 * Convierte SolicitudInvalidaException en 400 Bad Request.
 */
@Provider
public class SolicitudInvalidaMapper implements ExceptionMapper<SolicitudInvalidaException> {

    @Override
    public Response toResponse(SolicitudInvalidaException e) {
        return Response.status(Response.Status.BAD_REQUEST)
                .type(MediaType.APPLICATION_JSON)
                .entity(new ErrorSalida(e.getMessage()))
                .build();
    }
}
