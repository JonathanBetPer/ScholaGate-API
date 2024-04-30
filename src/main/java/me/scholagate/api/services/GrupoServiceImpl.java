package me.scholagate.api.services;

import me.scholagate.api.repositories.GrupoRepository;
import org.springframework.stereotype.Service;

@Service
public class GrupoServiceImpl implements GrupoService{

    private GrupoRepository grupoRepository;

    GrupoServiceImpl(GrupoRepository grupoRepository) {
        this.grupoRepository = grupoRepository;
    }
}
