package edu.javeriana.negocio.jakarta.solicitud.datos;

import edu.javeriana.negocio.jakarta.solicitud.modelo.SolicitudRecurso;
import edu.javeriana.negocio.jakarta.solicitud.negocio.SolicitudRepository;
import jakarta.ejb.Singleton;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Session Bean implementation class SolicitudRepositoryMemoria.
 * Tier de datos: guarda las solicitudes en una colección en memoria.
 * Es @Singleton para que exista una sola colección compartida por toda la aplicación.
 * Solo almacena y recupera; no valida ni cambia estados (eso es del negocio).
 */
@Singleton
public class SolicitudRepositoryMemoria implements SolicitudRepository {

    private final Map<Long, SolicitudRecurso> solicitudes = new ConcurrentHashMap<>();
    private final AtomicLong secuencia = new AtomicLong();

    /**
     * Default constructor.
     */
    public SolicitudRepositoryMemoria() {
    }

    @Override
    public SolicitudRecurso guardar(SolicitudRecurso solicitud) {
        // El identificador lo genera el almacén, igual que lo haría una base de datos.
        solicitud.setId(secuencia.incrementAndGet());
        solicitudes.put(solicitud.getId(), solicitud);
        return solicitud;
    }

    @Override
    public SolicitudRecurso actualizar(SolicitudRecurso solicitud) {
        solicitudes.put(solicitud.getId(), solicitud);
        return solicitud;
    }

    @Override
    public Optional<SolicitudRecurso> buscarPorId(long id) {
        return Optional.ofNullable(solicitudes.get(id));
    }

    @Override
    public List<SolicitudRecurso> listarTodas() {
        List<SolicitudRecurso> lista = new ArrayList<>(solicitudes.values());
        lista.sort(Comparator.comparing(SolicitudRecurso::getId));
        return lista;
    }
}
