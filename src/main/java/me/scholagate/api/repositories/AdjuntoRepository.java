package me.scholagate.api.repositories;

import jakarta.validation.constraints.NotNull;
import me.scholagate.api.models.Adjunto;
import me.scholagate.api.models.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdjuntoRepository extends JpaRepository<Adjunto, Integer> {
    List<Adjunto> findAllByIdReporte(@NotNull Reporte idReporte);
    List<Adjunto> findAll(String nombre);
}