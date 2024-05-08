package me.scholagate.api.repositories;

import me.scholagate.api.models.Adjunto;
import me.scholagate.api.models.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface AdjuntoRepository extends JpaRepository<Adjunto, Integer> {
    List<Adjunto> findAdjuntosByIdReporte (Long idReporte);
}