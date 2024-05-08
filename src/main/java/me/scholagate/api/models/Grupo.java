package me.scholagate.api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Grupos")
@NoArgsConstructor
@AllArgsConstructor
public class Grupo {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @JoinColumn(name = "idTutor", nullable = false)
    private Integer idTutor;

    @NotNull
    @JoinColumn(name = "idCurso", nullable = false)
    private Integer idCurso;

    @NotNull
    @Lob
    @Column(name = "turno", nullable = false)
    private String turno;

    @NotNull
    @Column(name = "letra", nullable = false)
    private Character letra;

}