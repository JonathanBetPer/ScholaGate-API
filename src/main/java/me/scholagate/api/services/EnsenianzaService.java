package me.scholagate.api.services;

import me.scholagate.api.models.Alumno;
import me.scholagate.api.models.Ensenianza;

import java.util.List;

public interface EnsenianzaService {
    List<Ensenianza> findAll();
    Ensenianza findById(Integer id);
    Ensenianza findEnsenianzaByNombre (String nombre);
    Ensenianza save(Ensenianza ensenianza);
    Ensenianza update(Ensenianza ensenianza);
    void deleteById(Integer id);
}
