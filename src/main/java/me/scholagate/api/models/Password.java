package me.scholagate.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "passwords")
public class Password {
    @Id
    @Column(name = "idUsuario", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuarios;

    @Size(max = 255)
    @NotNull
    @Column(name = "hashResult", nullable = false)
    private String hashResult;

    @NotNull
    @Column(name = "salt", nullable = false)
    private byte[] salt;
}