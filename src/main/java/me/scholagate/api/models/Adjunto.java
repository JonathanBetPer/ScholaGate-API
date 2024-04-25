package me.scholagate.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "adjuntos")
public class Adjunto {
    @EmbeddedId
    private AdjuntoId id;

    @MapsId("idReporte")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idReporte", nullable = false)
    private Reporte idReporte;

    @Size(max = 50)
    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "foto")
    private byte[] foto;

}