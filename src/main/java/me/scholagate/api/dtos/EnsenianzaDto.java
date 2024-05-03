package me.scholagate.api.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link me.scholagate.api.models.Ensenianza}
 */

public record EnsenianzaDto(Integer id, @NotNull @Size(max = 40) String nombre, @NotNull @Size(max = 10)
    String abreviatura) implements Serializable {
}