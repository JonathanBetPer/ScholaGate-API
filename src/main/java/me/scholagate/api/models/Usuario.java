package me.scholagate.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 80)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 80)
    private String nombre;

    @Size(max = 75)
    @NotNull
    @Column(name = "correo", nullable = false, length = 75)
    private String correo;

    @ColumnDefault("User")
    @Lob
    @Column(name = "rol")
    private String rol;

    @OneToMany(mappedBy = "idTutor")
    private Set<Grupo> grupos = new LinkedHashSet<>();

    @OneToOne(mappedBy = "idUsuario")
    private Password password;

}