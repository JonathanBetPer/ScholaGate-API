package me.scholagate.api.services;

import jakarta.validation.constraints.NotNull;
import me.scholagate.api.models.Adjunto;
import me.scholagate.api.models.Reporte;

import java.util.List;
import java.util.Optional;

public interface AdjuntoService {
    List<Adjunto> findAllByIdReporte(Integer idReporte);
    List<Adjunto> findAll(String nombre);
    Adjunto findById(Integer id);

    Adjunto save(Adjunto adjunto);

    Adjunto update(Adjunto adjunto);

    void deleteById(Integer id);
}
