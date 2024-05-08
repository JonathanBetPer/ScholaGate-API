package me.scholagate.api.repositories;

import me.scholagate.api.models.Alumno;
import me.scholagate.api.models.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {
    List<Alumno> findAllByNombreContainingIgnoreCase(String nombre);
    List<Alumno> findAllByIdGrupo(Integer idGrupo);
}