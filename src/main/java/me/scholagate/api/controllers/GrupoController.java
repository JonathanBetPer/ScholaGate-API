package me.scholagate.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.scholagate.api.dtos.GrupoDto;
import me.scholagate.api.models.Curso;
import me.scholagate.api.models.Grupo;
import me.scholagate.api.models.Usuario;
import me.scholagate.api.services.CursoService;
import me.scholagate.api.services.GrupoService;
import me.scholagate.api.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador de Grupos
 * Permite realizar operaciones CRUD sobre los grupos
 * Además, permite obtener los grupos de un curso o de un tutor
 * @version 1.0
 * @since 04/05/2024
 * @see Grupo
 * @see GrupoService
 * @see GrupoDto
 * @author JonathanBetPer
 */
@RestController
@RequestMapping("/api/v1")
@Tag(name = "Grupo Controller", description = "Controlador de Grupos")
public class GrupoController {

    @Autowired
    private GrupoService grupoService;
    @Autowired
    private CursoService cursoService;
    @Autowired
    private UsuarioService usuarioService;

    GrupoController(GrupoService grupoService, CursoService cursoService, UsuarioService usuarioService) {
        this.grupoService = grupoService;
        this.cursoService = cursoService;
        this.usuarioService = usuarioService;
    }

    /**
     * Obtiene los grupos
     */
    @Operation(summary = "Obtener los grupos", description = "Obtener todos los grupos")
    @GetMapping("/grupos")
    public ResponseEntity<List<Grupo>> getGrupos() {
        return ResponseEntity.ok(grupoService.findAll());
    }

    /**
     * Obtiene los grupos de un curso
     * @param idCurso ID del curso
     * @return Lista de grupos
     */
    @Operation(summary = "Obtener los grupos de un curso", description = "Obtener los grupos de un curso por su ID")
    @GetMapping("/grupos/{idCurso}")
    public ResponseEntity<List<Grupo>> getGruposByCurso(@PathVariable Integer idCurso) {

        Curso curso = cursoService.findById(idCurso);

        if (curso == null) {
            return ResponseEntity.notFound().build();
        }

        List<Grupo> grupos = grupoService.findGruposByIdCurso(curso.getId());



        if (grupos == null || grupos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(grupos);
    }

    /**
     * Obtiene un grupo por su ID
     * @param idGrupo ID del grupo
     * @return Grupo
     */
    @Operation(summary = "Obtener un grupo", description = "Obtener un grupo por su ID")
    @GetMapping("/grupo/{idGrupo}")
    public ResponseEntity<Grupo> getGrupoById(@PathVariable Integer idGrupo) {
        Grupo grupo = grupoService.findById(idGrupo);
        if (grupo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(grupo);
    }

    /**
     * Obtiene un grupo por el ID de un tutor
     * @param idTutor ID del tutor
     * @return Grupo
     */
    @Operation(summary = "Obtener un grupo por el ID de un tutor", description = "Obtener un grupo por el ID de un tutor")
    @GetMapping("/grupo/{idTutor}")
    public ResponseEntity<Grupo> getGrupoByTutor(@PathVariable Integer idTutor) {

        Usuario tutor = usuarioService.findById(idTutor);

        if (tutor == null) {
            return ResponseEntity.notFound().build();
        }

        Grupo grupo = grupoService.findGrupoByIdTutor(tutor.getId());

        if (grupo == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(grupo);
    }

    /**
     * Crea un nuevo grupo
     * @param grupoDto DTO del grupo
     * @return Grupo creado
     */
    @Operation(summary = "Crear un grupo", description = "Crear un nuevo grupo")
    @PostMapping("/grupo")
    public ResponseEntity<Grupo> postGrupo(@RequestBody GrupoDto grupoDto) {

        Curso curso = cursoService.findById(grupoDto.idCurso());

        if (curso == null) {
            return ResponseEntity.notFound().build();
        }

        Usuario tutor = usuarioService.findById(grupoDto.idTutor());

        if (tutor == null) {
            return ResponseEntity.notFound().build();
        }

        Grupo grupo = new Grupo(
                0,
                tutor.getId(),
                curso.getId(),
                grupoDto.turno(),
                grupoDto.letra()
        );

        return ResponseEntity.ok(grupoService.save(grupo));
    }

    /**
     * Actualiza los datos un grupo
     * No permite cambiar curso de un grupo
     * @param grupoDto DTO del grupo
     * @return Grupo actualizado
     */
    @Operation(summary = "Actualizar un grupo", description = "Actualizar un grupo")
    @PutMapping("/grupo")
    public ResponseEntity<Grupo> putGrupo(@RequestBody GrupoDto grupoDto) {

        Grupo grupo = grupoService.findById(grupoDto.id());

        if (grupo == null) {
            return ResponseEntity.notFound().build();
        }

        Usuario tutor = usuarioService.findById(grupoDto.idTutor());

        if (tutor == null) {
            return ResponseEntity.notFound().build();
        }

        grupo.setIdTutor(tutor.getId());
        grupo.setTurno(grupoDto.turno());
        grupo.setLetra(grupoDto.letra());

        return ResponseEntity.ok(grupoService.save(grupo));
    }

    /**
     * Elimina un grupo
     * @param idGrupo ID del grupo
     * @return  Estado de la eliminación
     */
    @Operation(summary = "Eliminar un grupo", description = "Eliminar un grupo por su ID")
    @DeleteMapping("/grupo/{idGrupo}")
    public ResponseEntity<String> deleteGrupo(@PathVariable Integer idGrupo) {

        Grupo grupo = grupoService.findById(idGrupo);

        if (grupo == null) {
            return ResponseEntity.notFound().build();
        }

        grupoService.deleteById(idGrupo);

        return ResponseEntity.ok("Grupo eliminado");
    }
}
