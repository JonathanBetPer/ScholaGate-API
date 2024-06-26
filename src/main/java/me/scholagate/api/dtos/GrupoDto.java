package me.scholagate.api.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link me.scholagate.api.models.Grupo}
 */
public record GrupoDto(Integer id, @NotNull Integer idTutor, @NotNull Integer idCurso, @NotNull String turno,
                       @NotNull Character letra) implements Serializable {
}