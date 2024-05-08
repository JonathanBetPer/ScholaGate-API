package me.scholagate.api.repositories;

import me.scholagate.api.models.Alumno;
import me.scholagate.api.models.Reporte;
import me.scholagate.api.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Long> {

    List<Reporte> findReportesByIdUsuario(Integer id);
    List<Reporte> findReportesByIdAlumno(Integer id);

}