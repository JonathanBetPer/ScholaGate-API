package me.scholagate.api.services;

import me.scholagate.api.models.Curso;
import me.scholagate.api.models.Grupo;
import me.scholagate.api.models.Usuario;
import me.scholagate.api.repositories.GrupoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GrupoServiceImpl implements GrupoService{

    private final GrupoRepository grupoRepository;

    GrupoServiceImpl(GrupoRepository grupoRepository) {
        this.grupoRepository = grupoRepository;
    }

    @Override
    public List<Grupo> findAll() {
        return grupoRepository.findAll();
    }

    @Override
    public Grupo findById(Integer id) {
        return grupoRepository.findById(id).orElse(null);
    }

    @Override
    public Grupo findGrupoByIdTutor(Integer idTutor) {
        return grupoRepository.findGrupoByIdTutor(idTutor);
    }

    @Override
    public List<Grupo> findGruposByIdCurso(Integer idCurso) {
        return grupoRepository.findGruposByIdCurso(idCurso);
    }

    @Override
    public Grupo save(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    @Override
    public Grupo update(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    @Override
    public void deleteById(Integer id) {
        grupoRepository.deleteById(id);
    }
}
