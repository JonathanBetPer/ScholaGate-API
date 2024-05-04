package me.scholagate.api.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import me.scholagate.api.dtos.CursoDto;
import me.scholagate.api.models.Curso;
import me.scholagate.api.models.Ensenianza;
import me.scholagate.api.services.CursoService;
import me.scholagate.api.services.EnsenianzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador de Cursos
 * Permite realizar operaciones CRUD sobre los cursos
 * Además, permite obtener los cursos de una enseñanza
 * @version 1.0
 * @since 04/05/2024
 * @see Curso
 * @see CursoService
 * @see CursoDto
 * @author JonathanBetPer
 */
@RestController
@RequestMapping("/api/v1")
@Tag(name = "Cursos Controller", description = "Controlador de Cursos")
public class CursoController {
    @Autowired
    private CursoService cursoService;
    @Autowired
    private EnsenianzaService ensenianzaService;

    CursoController(CursoService cursoService, EnsenianzaService ensenianzaService) {
        this.cursoService = cursoService;
        this.ensenianzaService = ensenianzaService;
    }

    /**
     * Método para obtener la información de todos los cursos
     * @return ResponseEntity<List<Curso>> con la información de todos los cursos
     */
    @GetMapping("/cursos")
    public ResponseEntity<List<Curso>> getCursos(){

        List<Curso>  cursos = this.cursoService.findAll();

        if (cursos == null || cursos.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cursos);
    }

    /**
     * Método para obtener la información de un curso
     * @param idCurso ID del curso
     * @return ResponseEntity<Curso> con la información del curso
     */
    @GetMapping("/curso/{idCurso}")
    public ResponseEntity<Curso> getCurso(@PathVariable Integer idCurso){

        Curso curso = this.cursoService.findById(idCurso);

        if (curso == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(curso);
    }

    /**
     * Método para obtener los cursos de una enseñanza
     * @param idEnsenianza ID de la enseñanza
     * @return ResponseEntity<List<Curso>> con la información de los cursos
     */
    @GetMapping("/cursos/{idEnsenianza}")
    public ResponseEntity<List<Curso>> getCursosByEnsenianza(@PathVariable Integer idEnsenianza){

        Ensenianza ensenianza = this.ensenianzaService.findById(idEnsenianza);

        if (ensenianza == null){
            return ResponseEntity.notFound().build();
        }

        List<Curso> cursos = this.cursoService.findCursosByIdEnsenianza(ensenianza);

        if (cursos == null || cursos.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cursos);
    }

    /**
     * Método para crear un curso
     * @param cursoDto DTO con la información del curso
     * @return ResponseEntity<Curso> con la información del curso creado
     */
    @PostMapping("/curso")
    public ResponseEntity<Curso> postCurso(@RequestBody CursoDto cursoDto) {

        Ensenianza ensenianza = ensenianzaService.findById(cursoDto.idEnsenianza());

        if (ensenianza == null){
            return ResponseEntity.notFound().build();
        }

        Curso curso = new Curso(
                0,
                ensenianza,
                cursoDto.nombre(),
                cursoDto.abreviatura()
        );

        return ResponseEntity.ok(cursoService.save(curso));
    }

    /**
     * Método para actualizar un curso
     * @param cursoDto DTO con la información del curso
     * @return ResponseEntity<Curso> con la información del curso actualizado
     */
    @PutMapping("/curso")
    public ResponseEntity<Curso> putCurso(@RequestBody CursoDto cursoDto) {

        Curso curso = cursoService.findById(cursoDto.id());

        if (curso == null){
            return ResponseEntity.notFound().build();
        }

        Ensenianza ensenianza = ensenianzaService.findById(cursoDto.idEnsenianza());

        if (ensenianza == null){
            return ResponseEntity.notFound().build();
        }

        curso = new Curso(
                cursoDto.id(),
                ensenianza,
                cursoDto.nombre(),
                cursoDto.abreviatura()
        );

        return ResponseEntity.ok(cursoService.save(curso));
    }

    /**
     * Método para eliminar un curso
     * @param idCurso ID del curso
     * @return ResponseEntity<String> con el estado de la eliminación
     */
    @DeleteMapping("/curso/{idCurso}")
    public ResponseEntity<String> deleteCurso(@PathVariable Integer idCurso) {

            Curso curso = cursoService.findById(idCurso);

            if (curso == null){
                return ResponseEntity.notFound().build();
            }

            cursoService.deleteById(idCurso);

            return ResponseEntity.ok("Curso eliminado");
    }
}