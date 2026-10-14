package edu.javeriana.negocio.jakarta.solicitud.negocio;

import edu.javeriana.negocio.jakarta.solicitud.modelo.EstadoSolicitud;
import edu.javeriana.negocio.jakarta.solicitud.modelo.Prioridad;
import edu.javeriana.negocio.jakarta.solicitud.modelo.SolicitudRecurso;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Pruebas de las reglas de negocio del EJB, sin desplegar en el servidor.
 */
class GestionSolicitudesBeanTest {

    private GestionSolicitudesBean gestion;

    @BeforeEach
    void prepararEjb() {
        gestion = new GestionSolicitudesBean(new SolicitudRepositoryFalso());
    }

    private SolicitudRecurso registrarValida() {
        return gestion.registrar("Inundación en el barrio La Esperanza", "Agua potable", 500, Prioridad.ALTA);
    }

    // ---------- Registrar ----------

    @Test
    @DisplayName("Una solicitud válida se registra con id y en estado PENDIENTE")
    void registrarSolicitudValida() {
        SolicitudRecurso s = registrarValida();

        assertNotNull(s.getId());
        assertEquals(EstadoSolicitud.PENDIENTE, s.getEstado());
        assertEquals("Agua potable", s.getTipoRecurso());
        assertEquals(500, s.getCantidad());
    }

    @Test
    @DisplayName("La cantidad debe ser mayor que cero")
    void rechazarCantidadCeroONegativa() {
        assertThrows(SolicitudInvalidaException.class,
                () -> gestion.registrar("Incendio", "Extintores", 0, Prioridad.ALTA));
        assertThrows(SolicitudInvalidaException.class,
                () -> gestion.registrar("Incendio", "Extintores", -5, Prioridad.ALTA));
    }

    @Test
    @DisplayName("La descripción del desastre es obligatoria")
    void rechazarSinDescripcion() {
        assertThrows(SolicitudInvalidaException.class,
                () -> gestion.registrar(null, "Carpas", 10, Prioridad.MEDIA));
        assertThrows(SolicitudInvalidaException.class,
                () -> gestion.registrar("   ", "Carpas", 10, Prioridad.MEDIA));
    }

    @Test
    @DisplayName("El tipo de recurso es obligatorio")
    void rechazarSinTipoRecurso() {
        assertThrows(SolicitudInvalidaException.class,
                () -> gestion.registrar("Deslizamiento", "", 10, Prioridad.BAJA));
    }

    @Test
    @DisplayName("La prioridad es obligatoria")
    void rechazarSinPrioridad() {
        assertThrows(SolicitudInvalidaException.class,
                () -> gestion.registrar("Deslizamiento", "Mantas", 10, null));
    }

    @Test
    @DisplayName("Una solicitud rechazada no queda guardada")
    void solicitudInvalidaNoSeGuarda() {
        assertThrows(SolicitudInvalidaException.class,
                () -> gestion.registrar("Incendio", "Extintores", 0, Prioridad.ALTA));

        assertTrue(gestion.consultarTodas().isEmpty());
    }

    // ---------- Consultar ----------

    @Test
    @DisplayName("Consultar todas devuelve todas las solicitudes registradas")
    void consultarTodas() {
        registrarValida();
        gestion.registrar("Incendio forestal", "Extintores", 20, Prioridad.MEDIA);

        assertEquals(2, gestion.consultarTodas().size());
    }

    @Test
    @DisplayName("Buscar por id devuelve la solicitud correcta")
    void buscarPorId() {
        SolicitudRecurso creada = registrarValida();

        SolicitudRecurso encontrada = gestion.buscarPorId(creada.getId());

        assertEquals(creada.getId(), encontrada.getId());
        assertEquals(creada.getDescripcionDesastre(), encontrada.getDescripcionDesastre());
    }

    @Test
    @DisplayName("Buscar un id que no existe lanza SolicitudNoEncontradaException")
    void buscarIdInexistente() {
        assertThrows(SolicitudNoEncontradaException.class, () -> gestion.buscarPorId(99));
    }

    @Test
    @DisplayName("Consultar pendientes no incluye las solicitudes atendidas")
    void consultarPendientes() {
        SolicitudRecurso primera = registrarValida();
        SolicitudRecurso segunda = gestion.registrar("Incendio forestal", "Extintores", 20, Prioridad.MEDIA);
        gestion.atender(primera.getId());

        List<SolicitudRecurso> pendientes = gestion.consultarPendientes();

        assertEquals(1, pendientes.size());
        assertEquals(segunda.getId(), pendientes.get(0).getId());
    }

    // ---------- Atender ----------

    @Test
    @DisplayName("Atender una solicitud PENDIENTE la pasa a ATENDIDA")
    void atenderPendiente() {
        SolicitudRecurso creada = registrarValida();

        SolicitudRecurso atendida = gestion.atender(creada.getId());

        assertEquals(EstadoSolicitud.ATENDIDA, atendida.getEstado());
        assertEquals(EstadoSolicitud.ATENDIDA, gestion.buscarPorId(creada.getId()).getEstado());
    }

    @Test
    @DisplayName("No se puede atender una solicitud inexistente")
    void atenderInexistente() {
        assertThrows(SolicitudNoEncontradaException.class, () -> gestion.atender(99));
    }

    @Test
    @DisplayName("No se puede atender dos veces la misma solicitud")
    void atenderDosVeces() {
        SolicitudRecurso creada = registrarValida();
        gestion.atender(creada.getId());

        assertThrows(SolicitudYaAtendidaException.class, () -> gestion.atender(creada.getId()));
    }
}
