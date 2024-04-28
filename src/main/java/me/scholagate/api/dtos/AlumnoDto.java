package me.scholagate.api.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link me.scholagate.api.models.Alumno}
 */
@Value
public class AlumnoDto implements Serializable {
    Integer id;
    @NotNull
    GrupoDto idGrupo;
    @NotNull
    @Size(max = 80)
    String nombre;
    @NotNull
    LocalDate fechaNac;
    byte[] foto;
}