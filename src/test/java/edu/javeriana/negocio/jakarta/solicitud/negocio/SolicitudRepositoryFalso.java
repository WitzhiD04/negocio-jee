package edu.javeriana.negocio.jakarta.solicitud.negocio;

import edu.javeriana.negocio.jakarta.solicitud.modelo.SolicitudRecurso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

/**
 * Repositorio de mentira para las pruebas: implementa la misma interfaz que el
 * repositorio real. Que el EJB funcione con él demuestra que el negocio no está
 * atado a ninguna implementación concreta de persistencia.
 */
class SolicitudRepositoryFalso implements SolicitudRepository {

    private final Map<Long, SolicitudRecurso> datos = new TreeMap<>();
    private long siguienteId = 1;

    @Override
    public SolicitudRecurso guardar(SolicitudRecurso solicitud) {
        solicitud.setId(siguienteId++);
        datos.put(solicitud.getId(), solicitud);
        return solicitud;
    }

    @Override
    public SolicitudRecurso actualizar(SolicitudRecurso solicitud) {
        datos.put(solicitud.getId(), solicitud);
        return solicitud;
    }

    @Override
    public Optional<SolicitudRecurso> buscarPorId(long id) {
        return Optional.ofNullable(datos.get(id));
    }

    @Override
    public List<SolicitudRecurso> listarTodas() {
        return new ArrayList<>(datos.values());
    }
}
