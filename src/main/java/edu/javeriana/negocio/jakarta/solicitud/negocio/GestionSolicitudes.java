package edu.javeriana.negocio.jakarta.solicitud.negocio;

import edu.javeriana.negocio.jakarta.solicitud.modelo.Prioridad;
import edu.javeriana.negocio.jakarta.solicitud.modelo.SolicitudRecurso;
import jakarta.ejb.Local;

import java.util.List;

/**
 * Interfaz de negocio (local) del EJB GestionSolicitudesBean.
 * Define los servicios que se ofrecen a los clientes que están en la misma JVM.
 */
@Local
public interface GestionSolicitudes {

    SolicitudRecurso registrar(String descripcionDesastre, String tipoRecurso,
                               int cantidad, Prioridad prioridad);

    List<SolicitudRecurso> consultarTodas();

    SolicitudRecurso buscarPorId(long id);

    List<SolicitudRecurso> consultarPendientes();

    SolicitudRecurso atender(long id);
}
