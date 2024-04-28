package me.scholagate.api.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link me.scholagate.api.models.Usuario}
 */
@Value
public class UsuarioDto implements Serializable {
    Integer id;
    @NotNull
    @Size(max = 80)
    String nombre;
    @NotNull
    @Size(max = 75)
    String correo;
    String rol;
}