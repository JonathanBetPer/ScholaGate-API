package me.scholagate.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.scholagate.api.dtos.AdjuntoDto;
import me.scholagate.api.models.Adjunto;
import me.scholagate.api.models.Reporte;
import me.scholagate.api.services.AdjuntoService;
import me.scholagate.api.services.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador de Adjuntos
 * Permite realizar operaciones CRUD sobre los adjuntos
 * Además, permite obtener los adjuntos de un reporte
 * @version 1.0
 * @since 04/05/2024
 * @see Adjunto
 * @see AdjuntoService
 * @see AdjuntoDto
 * @author JonathanBetPer
 */
@RestController
@RequestMapping("/api/v1")
@Tag(name = "Adjunto Controller", description = "Controlador de Adjuntos")
public class AdjuntoController {

    @Autowired
    private AdjuntoService adjuntoService;
    @Autowired
    private ReporteService reporteService;

    AdjuntoController(AdjuntoService adjuntoService, ReporteService reporteService) {
        this.adjuntoService = adjuntoService;
        this.reporteService = reporteService;
    }

    /**
     * Método para obtener la información de un adjunto
     * Se comprueba si el adjunto existe, si existe, se devuelve
     * @param idAdjunto Id del adjunto
     * @return ResponseEntity<Adjunto> con la información del adjunto
     */
    @Operation(summary = "Obtener un adjunto", description = "Obtener la información de un adjunto por su Id")
    @GetMapping("/adjunto/{idAdjunto}")
    public ResponseEntity<Adjunto> getAdjunto(@PathVariable("idAdjunto") Integer idAdjunto){

        Adjunto adjunto = this.adjuntoService.findById(idAdjunto);

        if (adjunto == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(adjunto);
    }

    /**
     * Método para obtener los adjuntos de un reporte
     * Se comprueba si el reporte existe, si existe, se devuelven los adjuntos
     * @param idReporte Id del reporte
     * @return ResponseEntity<List<Adjunto>> con la lista de adjuntos
     */
    @Operation(summary = "Obtener los adjuntos de un reporte", description = "Obtener la lista de adjuntos por el Id de su reporte")
    @GetMapping("/adjuntos/{idReporte}")
    public ResponseEntity<List<Adjunto>> getAllAdjuntosByIdReporte(@PathVariable("idReporte") Long idReporte){
        Reporte reporte = this.reporteService.findById(idReporte);

        if (reporte == null){
            return ResponseEntity.notFound().build();
        }

        List<Adjunto> adjuntos = this.adjuntoService.findAllByIdReporte(reporte.getId());

        if (adjuntos == null || adjuntos.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(adjuntos);
    }

    /**
     * Método para crear un nuevo adjunto
     * Se comprueba si el reporte existe, si existe, se crea el adjunto
     * @param adjuntoDto DTO del adjunto
     * @return ResponseEntity<Adjunto> con el adjunto creado
     */
    @Operation(summary = "Crear un adjunto", description = "Crear un nuevo adjunto")
    @PostMapping("/adjunto")
    public ResponseEntity<Adjunto> postAdjunto(@RequestBody AdjuntoDto adjuntoDto){

        Reporte reporte = this.reporteService.findById(adjuntoDto.idReporte());

        if (reporte == null){
            return ResponseEntity.notFound().build();
        }

        Adjunto adjunto = new Adjunto(
                0,
                reporte.getId(),
                adjuntoDto.nombre(),
                adjuntoDto.foto()
        );

        return ResponseEntity.ok(this.adjuntoService.save(adjunto));
    }

    /**
     * Método para actualizar la información de un adjunto
     * Se comprueba si el adjunto existe, si existe, se actualiza
     * @param adjuntoDto DTO del adjunto
     * @return ResponseEntity<Adjunto> con el adjunto actualizado
     */
    @Operation(summary = "Actualizar un adjunto", description = "Actualizar la información de un adjunto")
    @PutMapping("/adjunto")
    public ResponseEntity<Adjunto> putAdjunto(@RequestBody AdjuntoDto adjuntoDto){

        Adjunto adjunto = this.adjuntoService.findById(adjuntoDto.id());

        if (adjunto == null){
            return ResponseEntity.notFound().build();
        }

        adjunto.setNombre(adjuntoDto.nombre());
        adjunto.setFoto(adjuntoDto.foto());

        return ResponseEntity.ok(this.adjuntoService.save(adjunto));
    }

    /**
     * Método para eliminar un adjunto
     * Se comprueba si el adjunto existe, si existe, se elimina
     * @param idAdjunto ID del adjunto
     * @return ResponseEntity<String> con el estado de la eliminación
     */
    @Operation(summary = "Eliminar un adjunto", description = "Eliminar un adjunto por su Id")
    @DeleteMapping("/adjunto/{idAdjunto}")
    public ResponseEntity<String> deleteAdjunto(@PathVariable("idAdjunto") Integer idAdjunto){

        Adjunto adjunto = this.adjuntoService.findById(idAdjunto);

        if (adjunto == null){
            return ResponseEntity.notFound().build();
        }

        this.adjuntoService.deleteById(idAdjunto);

        return ResponseEntity.ok("Adjunto eliminado");
    }
}