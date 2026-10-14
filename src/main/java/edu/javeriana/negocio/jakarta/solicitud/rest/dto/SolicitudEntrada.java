package edu.javeriana.negocio.jakarta.solicitud.rest.dto;

import edu.javeriana.negocio.jakarta.solicitud.modelo.Prioridad;

/**
 * Cuerpo JSON de POST /solicitudes.
 * No tiene id ni estado: esos los asignan el repositorio y el EJB, no el cliente.
 */
public class SolicitudEntrada {

    private String descripcionDesastre;
    private String tipoRecurso;
    private int cantidad;
    private Prioridad prioridad;

    public SolicitudEntrada() {
    }

    public String getDescripcionDesastre() {
        return descripcionDesastre;
    }

    public void setDescripcionDesastre(String descripcionDesastre) {
        this.descripcionDesastre = descripcionDesastre;
    }

    public String getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(String tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }
}
