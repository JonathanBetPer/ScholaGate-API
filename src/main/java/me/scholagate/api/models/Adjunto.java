package me.scholagate.api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Adjuntos")
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