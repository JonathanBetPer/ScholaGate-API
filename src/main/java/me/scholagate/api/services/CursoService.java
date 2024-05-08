package me.scholagate.api.services;

import me.scholagate.api.models.Curso;
import me.scholagate.api.models.Ensenianza;

import java.util.List;

public interface CursoService {

    List<Curso> findAll();
    Curso findById(Integer id);
    Curso findCursoByAbreviatura (String abreviatura);
    Curso findCursoByNombre (String nombre);
    List<Curso> findCursosByIdEnsenianza (Integer idEnsenianza);
    Curso save(Curso curso);
    Curso update(Curso curso);
    void deleteById(Integer id);
}
