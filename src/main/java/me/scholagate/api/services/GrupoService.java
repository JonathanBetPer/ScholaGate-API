package me.scholagate.api.services;

import me.scholagate.api.models.*;

import java.util.List;

public interface GrupoService {
    List<Grupo> findAll();
    Grupo findById(Integer id);
    Grupo findGrupoByIdTutor (Usuario idTutor);
    List<Grupo> findGruposByIdCurso (Curso idCurso);
    Grupo save(Grupo grupo);
    Grupo update(Grupo grupo);
    void deleteById(Integer id);
}
