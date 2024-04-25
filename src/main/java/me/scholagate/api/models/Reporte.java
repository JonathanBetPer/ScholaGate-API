package me.scholagate.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "reportes")
public class Reporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idCurso", nullable = false)
    private Curso idCurso;

    @Size(max = 80)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 80)
    private String nombre;

    @NotNull
    @ColumnDefault("current_timestamp()")
    @Column(name = "fechaNac", nullable = false)
    private LocalDate fechaNac;

    @Column(name = "foto")
    private byte[] foto;

    @OneToMany(mappedBy = "idReporte")
    private Set<Adjunto> adjuntos = new LinkedHashSet<>();

}