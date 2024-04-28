package me.scholagate.api.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link me.scholagate.api.models.Reporte}
 */
@Value
public class ReporteDto implements Serializable {
    Long id;
    @NotNull
    AlumnoDto idAlumno;
    @NotNull
    UsuarioDto idUsuario;
    @NotNull
    @Size(max = 80)
    String nombre;
    @NotNull
    Instant fecha;
    byte[] foto;
}