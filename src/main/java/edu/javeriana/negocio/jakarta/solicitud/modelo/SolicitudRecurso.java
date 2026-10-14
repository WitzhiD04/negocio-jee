package edu.javeriana.negocio.jakarta.solicitud.modelo;

/**
 * Entidad de dominio: una solicitud de recursos registrada por un centro de atención.
 * No contiene reglas de negocio; esas viven en el EJB GestionSolicitudesBean.
 */
public class SolicitudRecurso {

    private Long id;
    private String descripcionDesastre;
    private String tipoRecurso;
    private int cantidad;
    private Prioridad prioridad;
    private EstadoSolicitud estado;

    /**
     * Default constructor.
     */
    public SolicitudRecurso() {
    }

    public SolicitudRecurso(String descripcionDesastre, String tipoRecurso, int cantidad,
                            Prioridad prioridad, EstadoSolicitud estado) {
        this.descripcionDesastre = descripcionDesastre;
        this.tipoRecurso = tipoRecurso;
        this.cantidad = cantidad;
        this.prioridad = prioridad;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public void setEstado(EstadoSolicitud estado) {
        this.estado = estado;
    }
}
