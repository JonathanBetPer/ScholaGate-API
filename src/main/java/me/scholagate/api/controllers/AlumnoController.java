package me.scholagate.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.scholagate.api.dtos.AlumnoDto;
import me.scholagate.api.models.Alumno;
import me.scholagate.api.models.Grupo;
import me.scholagate.api.models.Usuario;
import me.scholagate.api.securities.JWTAuthtenticationConfig;
import me.scholagate.api.services.AlumnoService;
import me.scholagate.api.services.GrupoService;
import me.scholagate.api.services.UsuarioService;
import me.scholagate.api.utils.Constans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Controlador de Alumnos
 * Permite realizar operaciones CRUD sobre los alumnos
 * Además, permite obtener los alumnos de un grupo
 *
 * @version 1.0
 * @since 03/05/2024
 * @see AlumnoService
 * @see Alumno
 * @see AlumnoDto
 * @author JonathanBetPer
 */
@RestController
@RequestMapping("/api/v1")
@Tag(name = "Alumno Controller", description = "Controlador de Alumnos")
public class AlumnoController {
    @Autowired
    private AlumnoService alumnoService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private GrupoService grupoService;

    AlumnoController(AlumnoService alumnoService, UsuarioService usuarioService, GrupoService grupoService) {
        this.alumnoService = alumnoService;
        this.usuarioService = usuarioService;
        this.grupoService = grupoService;
    }

    /**
     * Obtiene los alumnos de un grupo
     * Si el usuario es un ADMIN, se le permite ver TODOS los alumnos
     * Si el usuario es un TUTOR, se le permite ver los alumnos de su grupo
     * @param token Token de autenticación del usuario
     * @return Lista de alumnos
     */
    @Operation(summary = "Obtener los alumnos", description = "Obtener todos los alumnos que puede ver el usuario")
    @GetMapping("/alumnos")
    public ResponseEntity<List<Alumno>> getAlumnos(@RequestHeader("Authorization") String token){

        //Si el usuario es un usuario ADMIN, se le permite ver TODOS los alumnos
        if (JWTAuthtenticationConfig.getRolesFromToken(token).contains(Constans.ENUM_ROLES.ADMIN)){
            return ResponseEntity.ok(this.alumnoService.findAll());
        }

        Usuario usuario = this.usuarioService.findByCorreo(JWTAuthtenticationConfig.getUsernameFromToken(token));

        Grupo grupo = this.grupoService.findById(usuario.getId());

        return ResponseEntity.ok(this.alumnoService.findAllByIdGrupo(grupo));
    }

    /**
     * Obtiene una lista de alumnos por el id de un grupo
     * Si el grupo no existe, se devuelve un NOT_FOUND
     * Solo se permite ver los alumnos de un grupo si el usuario es un ADMIN
     * @param idGrupo Id del grupo
     * @return Lista de alumnos
     */
    @Operation(summary = "Obtener los alumnos de un grupo", description = "Obtener los alumnos por el Id de su grupo")
    @GetMapping("/alumnosGrupo/{idGrupo}")
    public ResponseEntity<List<Alumno>> getAlumnos(@PathVariable("idGrupo") int idGrupo){

        Grupo grupo = this.grupoService.findById(idGrupo);

        if (grupo == null){
            return ResponseEntity.notFound().build();
        }

        List<Alumno> alumnos = this.alumnoService.findAllByIdGrupo(grupo);

        if (alumnos.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(alumnos);
    }

    /**
     * Obtiene un alumno por su id
     * Si el alumno no existe, se devuelve un NOT_FOUND
     * @param id Id del alumno
     * @return Alumno
     */
    @Operation(summary = "Obtener un alumno", description = "Obtener la información de un alumno por su Id")
    @GetMapping("/alumno/{id}")
    public ResponseEntity<Alumno> getAlumno(@PathVariable("id") int id){

        Alumno alumno = this.alumnoService.findById(id);

        if (alumno == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(this.alumnoService.findById(id));
    }

    /**
     * Guarda un nuevo alumno
     * Si el grupo no existe, se devuelve un NOT_FOUND
     * Solo el usuario con rol ADMIn puede crear un alumno nuevo
     * @param alumnoDto Datos del alumno
     * @return Alumno guardado
     */
    @Operation(summary = "Crear un alumno", description = "Crear un nuevo alumno")
    @PostMapping("/alumno")
    public ResponseEntity<Alumno> postAlumno(@RequestBody AlumnoDto alumnoDto){

        Grupo grupo = this.grupoService.findById(alumnoDto.idGrupo());

        if (grupo == null){
            return ResponseEntity.notFound().build();
        }

        Alumno alumno = new Alumno(
                0,
                grupo,
                alumnoDto.nombre(),
                alumnoDto.fechaNac(),
                alumnoDto.foto()
        );

        alumno = this.alumnoService.save(alumno);

        return ResponseEntity.ok(alumno);
    }


    /**
     * Guarda una lista de alumnos
     * Si el grupo no existe, no se añade
     * Solo el usuario con rol ADMIN puede crear alumnos nuevos
     * @param alumnosDto Lista de alumnos
     * @return Lista de alumnos guardados
     */
    @Operation(summary = "Crear varios alumnos", description = "Crear varios alumnos")
    @PostMapping("/alumnos")
    public ResponseEntity<List<Alumno>> postAlumnos(@RequestBody List<AlumnoDto> alumnosDto){
        List<Alumno> alumnos = new LinkedList<>();

        for (AlumnoDto alumnoDto : alumnosDto){
            Grupo grupo = this.grupoService.findById(alumnoDto.idGrupo());

            if (grupo == null){
                return ResponseEntity.notFound().build();
            }else{
                alumnos.add(
                        new Alumno(
                                0,
                                grupo,
                                alumnoDto.nombre(),
                                alumnoDto.fechaNac(),
                                alumnoDto.foto()
                        )
                );
            }
        }

        alumnos = this.alumnoService.save(alumnos);

        return ResponseEntity.ok(alumnos);
    }

    /**
     * Actualiza un alumno
     * Si el alumno no existe, se devuelve un NOT_FOUND
     * @param id Id del alumno
     * @param alumnoDto Datos del alumno
     * @return Alumno actualizado
     */
    @Operation(summary = "Actualizar un alumno", description = "Actualizar la información de un alumno")
    @PutMapping("/alumno/{id}")
    public ResponseEntity<Alumno> putAlumno(@PathVariable("id") int id, @RequestBody AlumnoDto alumnoDto){
        Alumno alumno = this.alumnoService.findById(id);

        if (alumno == null){
            return ResponseEntity.notFound().build();
        }

        alumno.setIdGrupo(this.grupoService.findById(alumnoDto.idGrupo()));
        alumno.setNombre(alumnoDto.nombre());
        alumno.setFechaNac(alumnoDto.fechaNac());
        alumno.setFoto(alumnoDto.foto());

        alumno = this.alumnoService.save(alumno);

        return ResponseEntity.ok(alumno);
    }

    /**
     * Elimina un alumno
     * Solo el usuario con rol ADMIN puede eliminar un alumno
     * @param id Id del alumno
     * @return Mensaje de confirmación
     */
    @Operation(summary = "Eliminar un alumno", description = "Eliminar un alumno por su Id")
    @DeleteMapping("/alumno/{id}")
    public ResponseEntity<String> deleteAlumno(@PathVariable("id") int id){

        this.alumnoService.deleteById(id);

        return ResponseEntity.ok().build();
    }
}