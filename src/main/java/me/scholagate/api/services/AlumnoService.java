package me.scholagate.api.services;

import me.scholagate.api.models.Alumno;
import me.scholagate.api.models.Usuario;

import java.util.List;

public interface AlumnoService {

    List<Alumno> findAll();
    Alumno findById(Integer id);
    Alumno findByNombre(String nombre);

    Alumno save(Usuario usuario);
    Alumno update(Usuario usuario);
    void deleteById(Integer id);
}
