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
@Table(name = "Cursos")
@NoArgsConstructor
@AllArgsConstructor
public class Curso {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idEnsenianza", nullable = false)
    private Ensenianza idEnsenianza;

    @Size(max = 40)
    @Column(name = "Nombre", length = 40)
    private String nombre;

    @Size(max = 10)
    @Column(name = "Abreviatura", length = 10)
    private String abreviatura;
}