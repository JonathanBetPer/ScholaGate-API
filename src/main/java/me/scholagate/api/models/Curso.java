package me.scholagate.api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Cursos")
public class Curso {
    @Id
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
    private Ensenianza idEnseníanza;

}