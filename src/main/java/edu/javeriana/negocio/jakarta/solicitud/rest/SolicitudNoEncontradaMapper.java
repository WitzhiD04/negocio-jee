package edu.javeriana.negocio.jakarta.solicitud.rest;

import edu.javeriana.negocio.jakarta.solicitud.negocio.SolicitudNoEncontradaException;
import edu.javeriana.negocio.jakarta.solicitud.rest.dto.ErrorSalida;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 * Convierte SolicitudNoEncontradaException en 404 Not Found.
 */
@Provider
public class SolicitudNoEncontradaMapper implements ExceptionMapper<SolicitudNoEncontradaException> {

    @Override
    public Response toResponse(SolicitudNoEncontradaException e) {
        return Response.status(Response.Status.NOT_FOUND)
                .type(MediaType.APPLICATION_JSON)
                .entity(new ErrorSalida(e.getMessage()))
                .build();
    }
}
