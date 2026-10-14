package edu.javeriana.negocio.jakarta.solicitud.rest;

import edu.javeriana.negocio.jakarta.solicitud.negocio.SolicitudYaAtendidaException;
import edu.javeriana.negocio.jakarta.solicitud.rest.dto.ErrorSalida;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 * Convierte SolicitudYaAtendidaException en 409 Conflict.
 */
@Provider
public class SolicitudYaAtendidaMapper implements ExceptionMapper<SolicitudYaAtendidaException> {

    @Override
    public Response toResponse(SolicitudYaAtendidaException e) {
        return Response.status(Response.Status.CONFLICT)
                .type(MediaType.APPLICATION_JSON)
                .entity(new ErrorSalida(e.getMessage()))
                .build();
    }
}
