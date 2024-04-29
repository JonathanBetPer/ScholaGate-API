package me.scholagate.api.services;

import me.scholagate.api.models.Alumno;
import me.scholagate.api.models.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoServiceImpl implements AlumnoService{

    @Override
    public List<Alumno> findAll() {
        return List.of();
    }

    @Override
    public Alumno findById(Integer id) {
        return null;
    }

    @Override
    public Alumno findByNombre(String nombre) {
        return null;
    }

    @Override
    public Alumno save(Usuario usuario) {
        return null;
    }

    @Override
    public Alumno update(Usuario usuario) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
