package me.scholagate.api.services;

import me.scholagate.api.models.Alumno;
import me.scholagate.api.models.Reporte;
import me.scholagate.api.models.Usuario;

import java.util.List;
import java.util.Optional;

public interface ReporteService {
    List<Reporte> findAll();
    Reporte findById(Long id);
    List<Reporte> findReportesByIdUsuario(Usuario alumnoId);
    List<Reporte> findReportesByIdAlumno(Alumno usuarioId);
    Reporte save(Reporte reporte);
    Reporte update(Reporte reporte);
    void deleteById(Long id);

}
