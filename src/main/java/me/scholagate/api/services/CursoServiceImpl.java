package me.scholagate.api.services;

import me.scholagate.api.repositories.CursoRepository;
import org.springframework.stereotype.Service;

@Service
public class CursoServiceImpl implements CursoService {

    private CursoRepository cursoRepository;

    CursoServiceImpl(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

}
