package me.scholagate.api.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link me.scholagate.api.models.Grupo}
 */
@Value
public class GrupoDto implements Serializable {
    Integer id;
    @NotNull
    UsuarioDto idTutor;
    @NotNull
    CursoDto idCurso;
    @NotNull
    String turno;
    @NotNull
    Character letra;
}