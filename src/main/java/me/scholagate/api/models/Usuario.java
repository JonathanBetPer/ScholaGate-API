package me.scholagate.api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "Usuarios")
public class Usuario {
    @Id
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

    public static final class ENUM_ROLES {
        public static final String ADMIN = "Admin";
        public static final String  USER = "User";
    }

}