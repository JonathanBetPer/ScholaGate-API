package me.scholagate.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "alumnos")
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idCurso", nullable = false)
    private Curso idCurso;

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