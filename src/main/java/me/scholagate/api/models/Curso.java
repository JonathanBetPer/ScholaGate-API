package me.scholagate.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "cursos")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 40)
    @Column(name = "Nombre", length = 40)
    private String nombre;

    @Size(max = 10)
    @Column(name = "Abreviatura", length = 10)
    private String abreviatura;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "`idEnseñanza`", nullable = false)
    private Enseñanza idEnseñanza;

    @OneToMany(mappedBy = "idCurso")
    private Set<Alumno> alumnos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idCurso")
    private Set<Grupo> grupos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idCurso")
    private Set<Reporte> reportes = new LinkedHashSet<>();

}