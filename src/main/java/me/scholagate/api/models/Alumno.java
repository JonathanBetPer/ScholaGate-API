package me.scholagate.api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "Alumnos")
@NoArgsConstructor
@AllArgsConstructor
public class Alumno {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @JoinColumn(name = "idGrupo", nullable = false)
    private Integer idGrupo;

    @Size(max = 80)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 80)
    private String nombre;

    @NotNull
    @Column(name = "fechaNac", nullable = false)
    private LocalDate fechaNac;

    @Column(name = "foto")
    private byte[] foto;

}