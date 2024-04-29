package me.scholagate.api.repositories;

import me.scholagate.api.models.Ensenianza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnsenianzaRepository extends JpaRepository<Ensenianza, Integer> {
}