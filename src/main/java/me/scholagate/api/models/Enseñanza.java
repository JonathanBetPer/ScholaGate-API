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
@Table(name = "`enseñanzas`")
public class Enseñanza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 40)
    @NotNull
    @Column(name = "Nombre", nullable = false, length = 40)
    private String nombre;

    @Size(max = 10)
    @NotNull
    @Column(name = "Abreviatura", nullable = false, length = 10)
    private String abreviatura;

    @OneToMany(mappedBy = "idEnseñanza")
    private Set<Curso> cursos = new LinkedHashSet<>();

}