package me.scholagate.api.repositories;

import me.scholagate.api.models.Adjunto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdjuntoRepository extends JpaRepository<Adjunto, Integer> {
}