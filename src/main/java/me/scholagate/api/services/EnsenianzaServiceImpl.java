package me.scholagate.api.services;

import me.scholagate.api.repositories.EnsenianzaRepository;
import org.springframework.stereotype.Service;

@Service
public class EnsenianzaServiceImpl implements EnsenianzaService{

    private EnsenianzaRepository ensenianzaRepository;

    EnsenianzaServiceImpl(EnsenianzaRepository ensenianzaRepository) {
        this.ensenianzaRepository = ensenianzaRepository;
    }
}
