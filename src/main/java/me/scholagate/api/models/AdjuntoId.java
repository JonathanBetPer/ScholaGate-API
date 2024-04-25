package me.scholagate.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class AdjuntoId implements Serializable {
    private static final long serialVersionUID = -8848746239341135456L;
    @NotNull
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "idReporte", nullable = false)
    private Long idReporte;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AdjuntoId entity = (AdjuntoId) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.idReporte, entity.idReporte);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idReporte);
    }

}