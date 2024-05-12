package me.scholagate.api.services;

import me.scholagate.api.models.Alumno;
import me.scholagate.api.models.Grupo;
import me.scholagate.api.models.Usuario;
import me.scholagate.api.repositories.AlumnoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoServiceImpl implements AlumnoService{

    private final AlumnoRepository alumnoRepository;

    public AlumnoServiceImpl(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    @Override
    public List<Alumno> findAll() {
        return alumnoRepository.findAll();
    }

    @Override
    public Alumno findById(Integer id) {
        return alumnoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Alumno> findAllByNombreContainingIgnoreCase(String nombre) {
        return alumnoRepository.findAllByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public List<Alumno> findAllByIdGrupo(Integer idGrupo) {
        return alumnoRepository.findAllByIdGrupo(idGrupo);
    }

    @Override
    public List<Alumno> save(List<Alumno> alumnos) {
        return alumnoRepository.saveAll(alumnos);
    }

    @Override
    public Alumno save(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    @Override
    public Alumno update(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    @Override
    public void deleteById(Integer id) {
        alumnoRepository.deleteById(id);
    }
}
