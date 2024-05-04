package me.scholagate.api.repositories;

import me.scholagate.api.models.Curso;
import me.scholagate.api.models.Grupo;
import me.scholagate.api.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Integer> {
    Grupo findGrupoByIdTutor (Usuario idTutor);
    List<Grupo> findGruposByIdCurso (Curso idCurso);

}