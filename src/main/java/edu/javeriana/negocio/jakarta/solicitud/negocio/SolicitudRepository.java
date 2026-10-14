package edu.javeriana.negocio.jakarta.solicitud.negocio;

import edu.javeriana.negocio.jakarta.solicitud.modelo.SolicitudRecurso;

import java.util.List;
import java.util.Optional;

/**
 * Lo que el negocio necesita de la persistencia, sin decir cómo se hace.
 * La interfaz pertenece al tier de negocio y el tier de datos la implementa;
 * así el EJB de negocio nunca conoce la clase concreta que guarda los datos.
 */
public interface SolicitudRepository {

    /** Guarda una solicitud nueva y le asigna un identificador. */
    SolicitudRecurso guardar(SolicitudRecurso solicitud);

    /** Reemplaza una solicitud ya existente. */
    SolicitudRecurso actualizar(SolicitudRecurso solicitud);

    Optional<SolicitudRecurso> buscarPorId(long id);

    List<SolicitudRecurso> listarTodas();
}
