package me.scholagate.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.scholagate.api.dtos.UsuarioDto;
import me.scholagate.api.models.Usuario;
import me.scholagate.api.securities.JWTAuthtenticationConfig;
import me.scholagate.api.services.UsuarioService;
import me.scholagate.api.utils.Constans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static me.scholagate.api.controllers.AutenticacionController.enviarCorreoPassword;

/**
 * Controlador de Usuario
 * Permite realizar operaciones CRUD sobre los usuarios
 * Además, permite obtener información de un usuario en específico
 * @version 1.0
 * @since 04/05/2024
 * @see Usuario
 * @see UsuarioService
 * @see UsuarioDto
 * @author JonathanBetPer
 */
@RestController
@RequestMapping("/api/v1")
@Tag(name = "Usuario Controller", description = "Controlador de Usuario")
public class UsuarioController {

    @Autowired
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Método para obtener la información de un usuario
     * @param token Token de autenticación
     * @return ResponseEntity<Usuario> con la información del usuario
     */
    @Operation(summary = "Obtener la información de un usuario", description = "Obtener la información de un usuario")
    @GetMapping("/usuario")
    public ResponseEntity<Usuario> getUsuario(@RequestHeader("Authorization") String token){

        Usuario usuario = this.usuarioService.findByCorreo(JWTAuthtenticationConfig.getUsernameFromToken(token));

        if (usuario == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuario);
    }

    /**
     * Método para obtener la información de todos los usuarios
     * @param token Token de autenticación
     * @return ResponseEntity<List<Usuario>> con la información de todos los usuarios
     */
    @Operation(summary = "Obtener los usuarios", description = "Obtener todos los usuarios si es ADMIN")
    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> getAllUsuarios(@RequestHeader("Authorization") String token){

        if (JWTAuthtenticationConfig.isTokenRoleValid(token, Constans.ENUM_ROLES.ADMIN))
        {
            return ResponseEntity.ok(this.usuarioService.findAll());
        }
        return ResponseEntity.status(403).build();
    }

    /**
     * Método para obtener la información de un usuario en específico
     * @param id ID del usuario
     * @return ResponseEntity<Usuario> con la información del usuario
     */
    @Operation(summary = "Obtener un usuario", description = "Obtener la información de un usuario por su ID")
    @GetMapping("/usuario/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable("id") int id){
        return ResponseEntity.ok(this.usuarioService.findById(id));
    }

    /**
     * Método para crear un nuevo usuario
     * Se comprueba si el usuario ya existe, si no existe, se crea
     * Además, se envía un correo al usuario con la contraseña para completar el registro
     * @param usuarioDto DTO con la información del usuario
     * @return ResponseEntity<Usuario> con la información del usuario creado
     */
    @Operation(summary = "Crear un usuario", description = "Crear un nuevo usuario")
    @PostMapping("/usuario")
    public ResponseEntity<Usuario> postUsuario(@RequestBody UsuarioDto usuarioDto){

        Usuario usuario = this.usuarioService.findById(usuarioDto.id());

        if (usuario != null){
            return ResponseEntity.badRequest().build();
        }
        usuario = new Usuario(
                0,
                usuarioDto.nombre(),
                usuarioDto.correo(),
                usuarioDto.rol() == null || usuarioDto.rol().isEmpty() ? Constans.ENUM_ROLES.USER : usuarioDto.rol()
        );
        usuario = this.usuarioService.save(usuario);

        enviarCorreoPassword(usuario);

        return ResponseEntity.ok(usuario);
    }

    /**
     * Método para actualizar la información de un usuario
     * Se comprueba si el usuario existe, si existe, se actualiza
     * @param usuarioDto DTO con la información del usuario
     * @return ResponseEntity<Usuario> con la información del usuario actualizado
     */
    @Operation(summary = "Actualizar un usuario", description = "Actualizar la información de un usuario")
    @PutMapping("/usuario")
    public ResponseEntity<Usuario> putUsuario(@RequestBody UsuarioDto usuarioDto){

        Usuario usuario = this.usuarioService.findById(usuarioDto.id());

        if (usuario == null){
            return ResponseEntity.badRequest().build();
        }

        usuario.setNombre(usuarioDto.nombre());
        usuario.setCorreo(usuarioDto.correo());
        usuario.setRol(usuarioDto.rol());

        usuario = this.usuarioService.update(usuario);

        return ResponseEntity.ok(usuario);
    }

    /**
     * Método para eliminar un usuario
     * Se comprueba si el usuario existe, si existe, se elimina
     * @param id ID del usuario
     * @return ResponseEntity<String> con el estado de la eliminación
     */
    @Operation(summary = "Eliminar un usuario", description = "Eliminar un usuario por su Id")
    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable("id") int id){

        Usuario usuario = this.usuarioService.findById(id);

        if (usuario != null){
            return ResponseEntity.badRequest().build();
        }

        this.usuarioService.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
