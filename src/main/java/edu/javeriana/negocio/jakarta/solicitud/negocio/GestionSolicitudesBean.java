package edu.javeriana.negocio.jakarta.solicitud.negocio;

import edu.javeriana.negocio.jakarta.solicitud.modelo.EstadoSolicitud;
import edu.javeriana.negocio.jakarta.solicitud.modelo.Prioridad;
import edu.javeriana.negocio.jakarta.solicitud.modelo.SolicitudRecurso;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;

/**
 * Session Bean implementation class GestionSolicitudesBean.
 * Aquí viven TODAS las reglas de negocio de las solicitudes de recursos.
 * Solo conoce la interfaz SolicitudRepository, nunca la clase que guarda los datos.
 */
@Stateless
public class GestionSolicitudesBean implements GestionSolicitudes {

    // El contenedor inyecta la implementación disponible de la interfaz.
    @Inject
    private SolicitudRepository repositorio;

    /**
     * Default constructor. Lo usa el contenedor EJB.
     */
    public GestionSolicitudesBean() {
    }

    /**
     * Constructor para las pruebas unitarias: recibe el repositorio sin necesidad del servidor.
     */
    public GestionSolicitudesBean(SolicitudRepository repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public SolicitudRecurso registrar(String descripcionDesastre, String tipoRecurso,
                                      int cantidad, Prioridad prioridad) {
        if (cantidad <= 0) {
            throw new SolicitudInvalidaException("La cantidad solicitada debe ser mayor que cero");
        }
        if (estaVacio(descripcionDesastre)) {
            throw new SolicitudInvalidaException("La descripción del desastre es obligatoria");
        }
        if (estaVacio(tipoRecurso)) {
            throw new SolicitudInvalidaException("El tipo de recurso es obligatorio");
        }
        if (prioridad == null) {
            throw new SolicitudInvalidaException("La prioridad es obligatoria");
        }

        // Toda solicitud nueva inicia en PENDIENTE: lo decide el negocio, no el cliente.
        SolicitudRecurso solicitud = new SolicitudRecurso(descripcionDesastre, tipoRecurso,
                cantidad, prioridad, EstadoSolicitud.PENDIENTE);
        return repositorio.guardar(solicitud);
    }

    @Override
    public List<SolicitudRecurso> consultarTodas() {
        return repositorio.listarTodas();
    }

    @Override
    public SolicitudRecurso buscarPorId(long id) {
        return repositorio.buscarPorId(id)
                .orElseThrow(() -> new SolicitudNoEncontradaException(id));
    }

    @Override
    public List<SolicitudRecurso> consultarPendientes() {
        // Decidir qué es "pendiente" es una regla de negocio, por eso el filtro está aquí.
        return repositorio.listarTodas().stream()
                .filter(s -> s.getEstado() == EstadoSolicitud.PENDIENTE)
                .toList();
    }

    @Override
    public SolicitudRecurso atender(long id) {
        SolicitudRecurso solicitud = buscarPorId(id);   // si no existe lanza SolicitudNoEncontradaException

        if (solicitud.getEstado() == EstadoSolicitud.ATENDIDA) {
            throw new SolicitudYaAtendidaException(id);
        }

        solicitud.setEstado(EstadoSolicitud.ATENDIDA);
        return repositorio.actualizar(solicitud);
    }

    private boolean estaVacio(String texto) {
        return texto == null || texto.isBlank();
    }
}
