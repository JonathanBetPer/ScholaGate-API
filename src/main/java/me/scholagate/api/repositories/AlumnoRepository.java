package me.scholagate.api.repositories;

import me.scholagate.api.models.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {
    Alumno findByNombre(String nombre);
    List<Alumno> findAllByNombre(String nombre);
}