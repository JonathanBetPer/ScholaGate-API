package me.scholagate.api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Passwords")
@NoArgsConstructor
@AllArgsConstructor
public class Password {
    @Id
    @Column(name = "idUsuario", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "hashResult", nullable = false)
    private String hashResult;

    @NotNull
    @Column(name = "salt", nullable = false)
    private byte[] salt;

}