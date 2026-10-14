package edu.javeriana.negocio.jakarta.solicitud.rest.dto;

import edu.javeriana.negocio.jakarta.solicitud.modelo.EstadoSolicitud;
import edu.javeriana.negocio.jakarta.solicitud.modelo.Prioridad;
import edu.javeriana.negocio.jakarta.solicitud.modelo.SolicitudRecurso;

/**
 * Respuesta JSON de todas las operaciones sobre solicitudes.
 */
public class SolicitudSalida {

    private long id;
    private String descripcionDesastre;
    private String tipoRecurso;
    private int cantidad;
    private Prioridad prioridad;
    private EstadoSolicitud estado;

    public SolicitudSalida() {
    }

    /** Convierte el objeto de dominio en el DTO que se devuelve al cliente. */
    public static SolicitudSalida desde(SolicitudRecurso solicitud) {
        SolicitudSalida salida = new SolicitudSalida();
        salida.id = solicitud.getId();
        salida.descripcionDesastre = solicitud.getDescripcionDesastre();
        salida.tipoRecurso = solicitud.getTipoRecurso();
        salida.cantidad = solicitud.getCantidad();
        salida.prioridad = solicitud.getPrioridad();
        salida.estado = solicitud.getEstado();
        return salida;
    }

    public long getId() {
        return id;
    }

    public String getDescripcionDesastre() {
        return descripcionDesastre;
    }

    public String getTipoRecurso() {
        return tipoRecurso;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }
}
