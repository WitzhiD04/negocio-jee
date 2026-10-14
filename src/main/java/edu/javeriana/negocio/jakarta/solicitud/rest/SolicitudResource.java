package edu.javeriana.negocio.jakarta.solicitud.rest;

import edu.javeriana.negocio.jakarta.solicitud.negocio.GestionSolicitudes;
import edu.javeriana.negocio.jakarta.solicitud.modelo.SolicitudRecurso;
import edu.javeriana.negocio.jakarta.solicitud.rest.dto.SolicitudEntrada;
import edu.javeriana.negocio.jakarta.solicitud.rest.dto.SolicitudSalida;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

/**
 * Endpoints REST para evidenciar el funcionamiento del tier de negocio.
 * No contiene reglas: traduce JSON a llamadas al EJB y la respuesta de vuelta a JSON.
 */
@Path("solicitudes")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SolicitudResource {

    // Depende de la interfaz local del EJB, no de la clase GestionSolicitudesBean.
    @Inject
    private GestionSolicitudes gestion;

    @POST
    public Response registrar(SolicitudEntrada entrada) {
        if (entrada == null) {
            entrada = new SolicitudEntrada();   // cuerpo vacío: el negocio lo rechazará
        }
        SolicitudRecurso creada = gestion.registrar(entrada.getDescripcionDesastre(),
                entrada.getTipoRecurso(), entrada.getCantidad(), entrada.getPrioridad());
        return Response.status(Response.Status.CREATED)
                .entity(SolicitudSalida.desde(creada))
                .build();
    }

    @GET
    public List<SolicitudSalida> consultarTodas() {
        return convertir(gestion.consultarTodas());
    }

    @GET
    @Path("pendientes")
    public List<SolicitudSalida> consultarPendientes() {
        return convertir(gestion.consultarPendientes());
    }

    @GET
    @Path("{id}")
    public SolicitudSalida buscarPorId(@PathParam("id") long id) {
        return SolicitudSalida.desde(gestion.buscarPorId(id));
    }

    @PUT
    @Path("{id}/atender")
    public SolicitudSalida atender(@PathParam("id") long id) {
        return SolicitudSalida.desde(gestion.atender(id));
    }

    private List<SolicitudSalida> convertir(List<SolicitudRecurso> solicitudes) {
        return solicitudes.stream().map(SolicitudSalida::desde).toList();
    }
}
