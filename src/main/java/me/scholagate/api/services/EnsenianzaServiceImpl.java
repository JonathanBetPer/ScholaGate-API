package me.scholagate.api.services;

import me.scholagate.api.models.Ensenianza;
import me.scholagate.api.repositories.EnsenianzaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnsenianzaServiceImpl implements EnsenianzaService{

    private final EnsenianzaRepository ensenianzaRepository;

    EnsenianzaServiceImpl(EnsenianzaRepository ensenianzaRepository) {
        this.ensenianzaRepository = ensenianzaRepository;
    }

    @Override
    public List<Ensenianza> findAll() {
        return ensenianzaRepository.findAll();
    }

    @Override
    public Ensenianza findById(Integer id) {
        return ensenianzaRepository.findById(id).orElse(null);
    }

    @Override
    public Ensenianza findEnsenianzaByNombre(String nombre) {
        return ensenianzaRepository.findEnsenianzaByNombre(nombre);
    }

    @Override
    public Ensenianza save(Ensenianza ensenianza) {
        return ensenianzaRepository.save(ensenianza);
    }

    @Override
    public Ensenianza update(Ensenianza ensenianza) {
        return ensenianzaRepository.save(ensenianza);
    }

    @Override
    public void deleteById(Integer id) {
        ensenianzaRepository.deleteById(id);
    }
}
