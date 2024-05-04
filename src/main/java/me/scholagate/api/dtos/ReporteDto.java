package me.scholagate.api.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;
import me.scholagate.api.models.Reporte;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link me.scholagate.api.models.Reporte}
 */
public record ReporteDto(Long id, @NotNull Integer idAlumno, @NotNull Integer idUsuario, @NotNull String tipo, @NotNull
        @Size(max = 80) String motivo, @NotNull Instant fecha) implements Serializable {
}