package me.scholagate.api.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import me.scholagate.api.dtos.EnsenianzaDto;
import me.scholagate.api.models.Ensenianza;
import me.scholagate.api.services.EnsenianzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador de Enseñanzas
 * Permite realizar operaciones CRUD sobre las enseñanzas
 * @version 1.0
 * @since 04/05/2024
 * @see Ensenianza
 * @see EnsenianzaService
 * @see EnsenianzaDto
 * @author JonathanBetPer
 */
@RestController
@RequestMapping("/api/v1")
@Tag(name = "Enseñanzas Controller", description = "Controlador de Enseñanzas")
public class EnsenianzaController {
    @Autowired
    private EnsenianzaService ensenianzaService;

    EnsenianzaController(EnsenianzaService ensenianzaService) {
        this.ensenianzaService = ensenianzaService;
    }

    /**
     * Método para obtener la información de todas las enseñanzas
     * @return ResponseEntity<List<Ensenianza>> con la información de todas las enseñanzas
     */
    @GetMapping("/enseñanzas")
    public ResponseEntity<List<Ensenianza>> getEnsenianzas() {

        return ResponseEntity.ok(ensenianzaService.findAll());
    }

    /**
     * Método para obtener la información de una enseñanza
     * @param idEnsenianza ID de la enseñanza
     * @return ResponseEntity<Ensenianza> con la información de la enseñanza
     */
    @GetMapping("/enseñanza/{idEnsenianza}")
    public ResponseEntity<Ensenianza> getEnsenianzasById(@PathVariable Integer idEnsenianza) {

        Ensenianza ensenianzas = ensenianzaService.findById(idEnsenianza);

        if (ensenianzas == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ensenianzas);
    }

    /**
     * Método para crear una nueva enseñanza
     * @param ensenianzaDto DTO de la enseñanza
     * @return Ensenianza creada
     */
    @PostMapping("/enseñanza")
    public ResponseEntity<Ensenianza> createEnsenianza(@RequestBody EnsenianzaDto ensenianzaDto) {

        Ensenianza ensenianza = new Ensenianza(
                0,
                ensenianzaDto.nombre(),
                ensenianzaDto.abreviatura()
        );

        return ResponseEntity.ok(ensenianzaService.save(ensenianza));
    }

    /**
     * Método para actualizar una enseñanza
     * @param ensenianzaDto DTO de la enseñanza
     * @return Ensenianza actualizada
     */
    @PutMapping("/enseñanza")
    public ResponseEntity<Ensenianza> updateEnsenianza(@RequestBody EnsenianzaDto ensenianzaDto) {

        Ensenianza ensenianzaActual = ensenianzaService.findById(ensenianzaDto.id());

        if (ensenianzaActual == null) {
            return ResponseEntity.notFound().build();
        }

        ensenianzaActual.setNombre(ensenianzaDto.nombre());
        ensenianzaActual.setAbreviatura(ensenianzaDto.abreviatura());

        return ResponseEntity.ok(ensenianzaService.save(ensenianzaActual));
    }

    /**
     * Método para eliminar una enseñanza
     * @param idEnsenianza ID de la enseñanza
     * @return ResponseEntity<Void> con el estado de la eliminación
     */
    @DeleteMapping("/enseñanza/{idEnsenianza}")
    public ResponseEntity<String> deleteEnsenianza(@PathVariable Integer idEnsenianza) {

        Ensenianza ensenianza = ensenianzaService.findById(idEnsenianza);

        if (ensenianza == null) {
            return ResponseEntity.notFound().build();
        }

        ensenianzaService.deleteById(idEnsenianza);

        return ResponseEntity.ok("Enseñanza eliminada");
    }
}
