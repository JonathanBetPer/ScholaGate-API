package me.scholagate.api.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link me.scholagate.api.models.Curso}
 */
public record CursoDto(Integer id, @Size(max = 40) String nombre, @Size(max = 10) String abreviatura,
                       @NotNull EnsenianzaDto idEnsenianza) implements Serializable {
}