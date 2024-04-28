package me.scholagate.api.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import java.io.Serializable;

@Getter
@Setter
@Value
public class CredencialesDto implements Serializable {
    @NotNull
    @Size(max = 75)
    @Email
    String nombreUsuario;
    @NotNull
    @Size(max = 75)
    String password;
}

