package me.scholagate.api.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import me.scholagate.api.dtos.ReporteDto;
import me.scholagate.api.models.Alumno;
import me.scholagate.api.models.Grupo;
import me.scholagate.api.models.Reporte;
import me.scholagate.api.models.Usuario;
import me.scholagate.api.securities.JWTAuthtenticationConfig;
import me.scholagate.api.services.AlumnoService;
import me.scholagate.api.services.GrupoService;
import me.scholagate.api.services.ReporteService;
import me.scholagate.api.services.UsuarioService;
import me.scholagate.api.utils.Constans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Controlador de Reportes
 * Permite realizar operaciones CRUD sobre los reportes
 * Además, permite obtener los reportes de un usuario o de un alumno
 * Los reportes se ordenan por fecha descendente
 *
 * @version 1.0
 * @since 03/05/2024
 * @see Reporte
 * @see ReporteService
 * @see ReporteDto

 * @author JonathanBetPer
 */
@RestController
@RequestMapping("/api/v1")
@Tag(name = "Reportes Controller", description = "Controlador de Reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;
    @Autowired
    private GrupoService grupoService;
    @Autowired
    private AlumnoService alumnoService;
    @Autowired
    private UsuarioService usuarioService;

    ReporteController(ReporteService reporteService, GrupoService grupoService,
                      AlumnoService alumnoService, UsuarioService usuarioService){
        this.reporteService = reporteService;
        this.grupoService = grupoService;
        this.alumnoService = alumnoService;
        this.usuarioService = usuarioService;
    }

    /**
     * Crea un nuevo reporte
     * @param reporteDto DTO del reporte
     * @return Reporte creado
     */
    @PostMapping("/reporte")
    public ResponseEntity<Reporte> postReporte(@RequestBody ReporteDto reporteDto) {

        Reporte reporte = new Reporte(
                0L,
                alumnoService.findById(reporteDto.idAlumno().id()),
                usuarioService.findById(reporteDto.idUsuario()),
                reporteDto.nombre(),
                reporteDto.fecha()
        );

        return ResponseEntity.ok(reporteService.save(reporte));
    }

    /**
     * Actualiza un reporte
     * @param reporteDto DTO del reporte
     * @return Reporte actualizado
     */
    @PutMapping("/reporte")
    public ResponseEntity<Reporte> putReporte(@RequestBody ReporteDto reporteDto) {

        Reporte reporte = reporteService.findById(reporteDto.id());

        if (reporte == null){
            return ResponseEntity.notFound().build();
        }

        reporte = new Reporte(
                reporteDto.id(),
                alumnoService.findById(reporteDto.idAlumno().id()),
                usuarioService.findById(reporteDto.idUsuario()),
                reporteDto.nombre(),
                reporteDto.fecha()
        );

        return ResponseEntity.ok(reporteService.save(reporte));
    }

    /**
     * Elimina un reporte
     * @param id Id del reporte
     * @return Mensaje de confirmación
     */
    @DeleteMapping("/reporte/{id}")
    public ResponseEntity<String> deleteReporte(@PathVariable Long id) {

        Reporte reporte = reporteService.findById(id);

        if (reporte == null){
            return ResponseEntity.notFound().build();
        }

        reporteService.deleteById(id);
        return ResponseEntity.ok("Reporte eliminado");
    }

    /**
     * Obtiene todos los reportes de un usuario
     * Si el usuario es ADMIN, obtiene todos los reportes
     * Si el usuario es USER, obtiene los reportes del usuario y los reportes de los alumnos de los grupos donde es tutor
     * Además, los reportes se ordenan por fecha descendente
     * @param token Token de autenticación en la cabecera de la petición
     * @return Lista de reportes
     */
    @GetMapping("/reportes")
    public ResponseEntity<List<Reporte>> getReportes(@RequestHeader("Authorization") String token) {

        List<Reporte> reportes = new LinkedList<>();

        // Usuario ADMIN = Todos los reportes
        if (JWTAuthtenticationConfig.getRolesFromToken(token).contains(Constans.ENUM_ROLES.ADMIN)){

            reportes.addAll(reporteService.findAll());

            //Usuario USER = Sus reportes + Reportes donde es tutor
        } else {
            Usuario usuario = usuarioService.findByCorreo(JWTAuthtenticationConfig.getUsernameFromToken(token));

            reportes.addAll(reporteService.findReportesByIdUsuario(usuario));

            Grupo grupo = grupoService.findGrupoByIdTutor(usuario);
            if (grupo != null){

                List<Alumno> alumnos = alumnoService.findAllByIdGrupo(grupo);

                for (Alumno alumno : alumnos) {
                    reportes.addAll(reporteService.findReportesByIdAlumno(alumno));
                }
            }
        }

        if ( reportes.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(orderReportes(reportes));
    }


    /**
     * Obtiene todos los reportes de un alumno
     * Si el usuario es ADMIN, obtiene todos los reportes del alumno
     * Si el usuario es USER, obtiene los reportes del alumno si es tutor del alumno
     * @param token Token de autenticación en la cabecera de la petición
     * @param idAlumno Id del alumno
     * @return Lista de reportes
     */
    @GetMapping("/reportesAlumno/{idAlumno}")
    public ResponseEntity<List<Reporte>> getReportesByAlumno(@RequestHeader("Authorization") String token, @PathVariable Integer idAlumno) {

        List<Reporte> reportes = new LinkedList<>();

        // Usuario ADMIN = Todos los reportes del Alumno
        if (JWTAuthtenticationConfig.getRolesFromToken(token).contains(Constans.ENUM_ROLES.ADMIN)){

            reportes.addAll(reporteService.findReportesByIdAlumno(alumnoService.findById(idAlumno)));

        //Usuario USER = Si es Tutor del Alumno
        } else {
            Usuario usuario = usuarioService.findByCorreo(JWTAuthtenticationConfig.getUsernameFromToken(token));

            Grupo grupo = grupoService.findGrupoByIdTutor(usuario);

            if (grupo != null){
                Alumno alumno = alumnoService.findById(idAlumno);
                if (alumno != null && alumno.getIdGrupo().getId().equals(grupo.getId())){
                    reportes.addAll(reporteService.findReportesByIdAlumno(alumno));
                }
            }
        }

        if ( reportes.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(orderReportes(reportes));
    }

    /**
     * Obtiene todos los reportes de un usuario determinado. Solo para Usuarios con rol ADMIN
     * @param idUsuario Id del usuario
     * @return Lista de reportes
     */
    //TODO: Capar Solicitud Solo para ADMIN
    @GetMapping("/reportesUsuario/{idUsuario}")
    public ResponseEntity<List<Reporte>> getReportesByUsuario(@PathVariable Integer idUsuario) {

        List<Reporte> reportes = reporteService.findReportesByIdUsuario(usuarioService.findById(idUsuario));

        if ( reportes.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(orderReportes(reportes));
    }

    /**
     * Método para ordenar los reportes por fecha descendente
     * @param reportes Lista de reportes
     * @return Lista de reportes ordenada por fecha descendente
     */
    private LinkedList<Reporte> orderReportes(List<Reporte> reportes){

        reportes.sort(Comparator.comparing(Reporte::getFecha).reversed());
        return (LinkedList<Reporte>) reportes;
    }
}
