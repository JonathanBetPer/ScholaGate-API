package me.scholagate.api.services;

import me.scholagate.api.models.Alumno;
import me.scholagate.api.models.Grupo;
import me.scholagate.api.models.Usuario;

import java.util.List;

public interface AlumnoService {

    List<Alumno> findAll();
    Alumno findById(Integer id);
    List<Alumno> findAllByNombreContainingIgnoreCase(String nombre);
    List<Alumno> findAllByIdGrupo(Grupo idGrupo);
    List<Alumno> save(List<Alumno> alumnos);
    Alumno save(Alumno alumno);
    Alumno update(Alumno alumno);
    void deleteById(Integer id);
}
