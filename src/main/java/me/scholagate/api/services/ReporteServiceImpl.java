package me.scholagate.api.services;

import me.scholagate.api.repositories.ReporteRepository;
import org.springframework.stereotype.Service;

@Service
public class ReporteServiceImpl implements ReporteService {

    private ReporteRepository reporteRepository;
    ReporteServiceImpl(ReporteRepository reporteRepository) {
        this.reporteRepository = reporteRepository;
    }
}
