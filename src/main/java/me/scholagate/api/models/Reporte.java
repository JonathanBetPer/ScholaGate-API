package me.scholagate.api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.scholagate.api.dtos.ReporteDto;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "Reportes")
@NoArgsConstructor
@AllArgsConstructor
public class Reporte implements Comparable<Reporte> {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idAlumno", nullable = false)
    private Alumno idAlumno;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario idUsuario;

    @NotNull
    @Lob
    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Size(max = 80)
    @NotNull
    @Column(name = "motivo", nullable = false, length = 80)
    private String motivo;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha", nullable = false)
    private Instant fecha;

    @Override
    public int compareTo(Reporte o) {
        return this.fecha.compareTo(o.fecha);
    }
}