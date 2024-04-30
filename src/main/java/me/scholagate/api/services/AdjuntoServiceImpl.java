package me.scholagate.api.services;

import me.scholagate.api.models.Adjunto;
import me.scholagate.api.repositories.AdjuntoRepository;
import me.scholagate.api.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdjuntoServiceImpl implements AdjuntoService{

    private AdjuntoRepository adjuntoRepository;

    AdjuntoServiceImpl(AdjuntoRepository adjuntoRepository){
        this.adjuntoRepository = adjuntoRepository;
    }

    @Override
    public List<Adjunto> findAllByIdReporte(Integer idReporte) {
        return List.of();
    }

    @Override
    public List<Adjunto> findAll(String nombre) {
        return List.of();
    }

    @Override
    public Adjunto findById(Integer id) {
        return null;
    }

    @Override
    public Adjunto save(Adjunto adjunto) {
        return null;
    }

    @Override
    public Adjunto update(Adjunto adjunto) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
