package me.scholagate.api.services;

import me.scholagate.api.models.Curso;
import me.scholagate.api.models.Ensenianza;
import me.scholagate.api.repositories.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;

    CursoServiceImpl(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Override
    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }

    @Override
    public Curso findById(Integer id) {
        return cursoRepository.findById(id).orElse(null);
    }

    @Override
    public Curso findCursoByAbreviatura(String abreviatura) {
        return cursoRepository.findCursoByAbreviatura(abreviatura);
    }

    @Override
    public Curso findCursoByNombre(String nombre) {
        return cursoRepository.findCursoByNombre(nombre);
    }

    @Override
    public List<Curso> findCursosByIdEnsenianza(Integer idEnsenianza) {
        return cursoRepository.findCursosByIdEnsenianza(idEnsenianza);
    }

    @Override
    public Curso save(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Override
    public Curso update(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Override
    public void deleteById(Integer id) {
        cursoRepository.deleteById(id);
    }
}
