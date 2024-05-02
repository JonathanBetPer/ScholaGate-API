package me.scholagate.api.services;

import me.scholagate.api.models.Alumno;
import me.scholagate.api.models.Reporte;
import me.scholagate.api.models.Usuario;
import me.scholagate.api.repositories.ReporteRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ReporteServiceImpl implements ReporteService {

    private final ReporteRepository reporteRepository;
    ReporteServiceImpl(ReporteRepository reporteRepository) {
        this.reporteRepository = reporteRepository;
    }

    @Override
    public List<Reporte> findAll() {
        return reporteRepository.findAll();
    }

    @Override
    public Reporte findById(Long id) {
        return reporteRepository.findById(id).orElse(null);
    }

    @Override
    public List<Reporte> findReportesByIdUsuario(Usuario usuarioId) {
        return reporteRepository.findReportesByIdUsuario(usuarioId);
    }

    @Override
    public List<Reporte> findReportesByIdAlumno(Alumno alumnoId) {
        return reporteRepository.findReportesByIdAlumno(alumnoId);
    }

    @Override
    public Reporte save(Reporte reporte) {
        return reporteRepository.save(reporte);
    }

    @Override
    public Reporte update(Reporte reporte) {
        return reporteRepository.save(reporte);
    }

    @Override
    public void deleteById(Long id) {
        reporteRepository.deleteById(id);
    }
}
