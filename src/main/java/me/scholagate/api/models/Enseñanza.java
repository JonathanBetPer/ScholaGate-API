package me.scholagate.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "`Enseñanzas`")
public class Enseñanza {
    @Id
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

}