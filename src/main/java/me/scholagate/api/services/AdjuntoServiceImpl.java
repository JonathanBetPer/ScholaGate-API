package me.scholagate.api.services;

import me.scholagate.api.models.Adjunto;
import me.scholagate.api.models.Reporte;
import me.scholagate.api.repositories.AdjuntoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdjuntoServiceImpl implements AdjuntoService{

    private final AdjuntoRepository adjuntoRepository;

    AdjuntoServiceImpl(AdjuntoRepository adjuntoRepository){
        this.adjuntoRepository = adjuntoRepository;
    }

    @Override
    public List<Adjunto> findAllByIdReporte(Long idReporte) {
        return adjuntoRepository.findAdjuntosByIdReporte(idReporte);
    }

    @Override
    public List<Adjunto> findAll(String nombre) {
        return adjuntoRepository.findAll();
    }

    @Override
    public Adjunto findById(Integer id) {
        return adjuntoRepository.findById(id).orElse(null);
    }

    @Override
    public Adjunto save(Adjunto adjunto) {
        return adjuntoRepository.save(adjunto);
    }

    @Override
    public Adjunto update(Adjunto adjunto) {
        return adjuntoRepository.save(adjunto);
    }

    @Override
    public void deleteById(Integer id) {
        adjuntoRepository.deleteById(id);
    }
}
