package me.scholagate.api.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link me.scholagate.api.models.Adjunto}
 */
@Value
public class AdjuntoDto implements Serializable {
    Integer id;
    @NotNull
    ReporteDto idReporte;
    @Size(max = 50)
    String nombre;
    byte[] foto;
}