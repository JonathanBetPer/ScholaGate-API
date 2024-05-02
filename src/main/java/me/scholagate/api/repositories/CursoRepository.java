package me.scholagate.api.repositories;

import me.scholagate.api.models.Curso;
import me.scholagate.api.models.Ensenianza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {
    Curso findCursoByAbreviatura (String abreviatura);
    Curso findCursoByNombre (String nombre);
    List<Curso> findCursosByIdEnsenianza(Ensenianza idEnsenianza);
}