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
@Table(name = "Adjuntos")
@NoArgsConstructor
@AllArgsConstructor
public class Adjunto {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idReporte", nullable = false)
    private Reporte idReporte;

    @Size(max = 50)
    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "foto")
    private byte[] foto;

}