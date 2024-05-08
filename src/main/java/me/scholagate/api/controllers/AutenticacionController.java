package me.scholagate.api.controllers;

import com.password4j.Hash;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.scholagate.api.dtos.CredencialesDto;
import me.scholagate.api.dtos.UsuarioDto;
import me.scholagate.api.models.Password;
import me.scholagate.api.models.Usuario;
import me.scholagate.api.utils.Constans;
import me.scholagate.api.securities.JWTAuthtenticationConfig;
import me.scholagate.api.services.EmailService;
import me.scholagate.api.services.PasswordService;
import me.scholagate.api.services.UsuarioService;
import me.scholagate.api.utils.Encriptacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;


/**
 * Controlador de Autenticación
 * Permite realizar operaciones de autenticación de usuarios
 * Además, permite realizar operaciones de registro y cambio de contraseña
 *
 * @version 1.0
 * @since 03/05/2024
 * @see Usuario
 * @see UsuarioService
 * @see Password
 * @see PasswordService
 * @see EmailService
 * @see Encriptacion
 * @see JWTAuthtenticationConfig
 * @see Constans
 * @see CredencialesDto
 * @see UsuarioDto
 * @author JonathanBetPer
 */
@RestController
@RequestMapping("/api/v1")
@Tag(name = "Autenticación Controller", description = "Expone los métodos para la autenticación de usuarios, registro, " +
        "cambio de contraseña y un método para comprobar si un token es válido ")
public class AutenticacionController {

    @Autowired
    private final UsuarioService usuarioService;
    @Autowired
    private final PasswordService passwordService;
    @Autowired
    private static EmailService emailService;

    public AutenticacionController(UsuarioService usuarioService, PasswordService passwordService, EmailService emailService) {
        this.usuarioService = usuarioService;
        this.passwordService = passwordService;
        this.emailService = emailService;
    }

    /**
     * Método para comprobar si la aplicación está encendida
     * @return ResponseEntity<String> con el estado de la aplicación
     */
    @Operation(summary = "Comprobar si la aplicación está encendida", description = "Comprueba si la aplicación " +
            "está encendida")
    @GetMapping("/up")
    public ResponseEntity<String> up(){
        return ResponseEntity.ok("All Correct");
    }

    /**
     * Método para realizar el login de un usuario
     * Se comprueba si el usuario existe y si la contraseña es correcta
     * Si es correcto, se genera un token JWT
     *
     * @param credencialesDto DTO con el nombre de usuario y la contraseña
     * @return ResponseEntity<String> con el token JWT
     */
    @Operation(summary = "Login de un usuario", description = "Realiza el login de un usuario, devolviendo un token JWT " +
            "si es correcto")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody CredencialesDto credencialesDto){

        Usuario usuario = this.usuarioService.findByCorreo(credencialesDto.nombreUsuario());

        if (usuario == null){
            return ResponseEntity.notFound().build();
        }

        Password password = this.passwordService.findById(usuario.getId());

        if (password == null){
            return ResponseEntity.notFound().build();
        }

        if (Encriptacion.comprobarPasswd(credencialesDto.password(), password.getHashResult(), password.getSalt())) {

            String token = JWTAuthtenticationConfig.getJWTToken(Constans.TOKEN_EXPIRATION_TIME, usuario.getCorreo(), usuario.getRol());

            return ResponseEntity.ok(token);

        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Método para realizar el registro del primer usuario ADMIN
     * Si no existe, se registra y se envía un correo de confirmación
     * Se genera un token JWT para confirmar el registro
     *
     * @param usuarioDto DTO con los datos del usuario
     * @return ResponseEntity<String> con el estado del envío del correo
     */
    @Operation(summary = "Registro de un usuario ADMIN", description = "Realiza el registro de un usuario ADMIN si no " +
            "existe ningún usuario en la base de datos")
    @PostMapping("/registerAdmin")
    public ResponseEntity<String> registerAdmin(@RequestBody UsuarioDto usuarioDto){

        if (!this.usuarioService.findAll().isEmpty()){
            return ResponseEntity.ok().build();
        }

        Usuario usuario = new Usuario();
        usuario.setId(usuarioDto.id());
        usuario.setNombre(usuarioDto.nombre());
        usuario.setCorreo(usuarioDto.correo());
        usuario.setRol(Constans.ENUM_ROLES.ADMIN);
        usuario = this.usuarioService.save(usuario);

        //Enviar correo de confirmación de registro
        String token = JWTAuthtenticationConfig.getJWTToken(Constans.TOKEN_EXPIRATION_TIME, usuario.getCorreo(), usuario.getRol());

        emailService.sendAdminEmail(usuario, token);

        return ResponseEntity.ok("Mail send");
    }

    /**
     * Método para registrar la contraseña de un usuario
     * Se comprueba si el usuario existe y si la contraseña no existe
     * Si es correcto, se registra la contraseña
     * @param token Token de autenticación
     * @param newPasswd Contraseña nueva
     * @return ResponseEntity<String> con el estado del registro de la contraseña
     */
    @Operation(summary = "Registrar la contraseña de un usuario", description = "Registra o cambia la contraseña de un usuario por su Token" +
            "si no existe en la base de datos")
    @PostMapping("/passwd/{tokenAuth}")
    public ResponseEntity<String> passwd(@PathVariable("tokenAuth") String token, @RequestBody String newPasswd){

        Usuario usuario = this.usuarioService.findByCorreo(JWTAuthtenticationConfig.getUsernameFromToken(token));

        if (usuario == null){
            return ResponseEntity.notFound().build();
        }

        Password password = this.passwordService.findById(usuario.getId());

        if (password == null){
            password = new Password();
            password.setUsuarios(usuario);
        }

        Hash hash = Encriptacion.generarHash(newPasswd);
        password.setHashResult(hash.getResult());
        password.setSalt(hash.getSaltBytes());

        return ResponseEntity.ok("Password changed");
    }

    /**
     * Método para comprobar la autenticación de un usuario
     * @param token Token de autenticación
     * @return ResponseEntity<String> con el correo del usuario
     */
    @Deprecated
    @Operation(summary = "Comprobar la autenticación de un usuario", description = "Comprueba si el token de autenticación " +
            "es correcto")
    @GetMapping("/auth")
    public ResponseEntity<String> auth(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(JWTAuthtenticationConfig.getUsernameFromToken(token));
    }

    /**
     * Método para enviar un correo de cambio de contraseña
     * Se comprueba si el usuario existe y se envía un correo de cambio de contraseña
     * @param idUsuario Id del usuario
     * @return ResponseEntity<String> con el estado del envío del correo
     */
    @Operation(summary = "Enviar un correo de cambio de contraseña", description = "Envía un correo de cambio de contraseña " +
            "a un usuario por su Id")
    @PostMapping("pedirPaswd/{idUsuario}")
    public ResponseEntity<String> generarPasswd(@PathVariable("idUsuario") int idUsuario){

        Usuario usuario = this.usuarioService.findById(idUsuario);

        if (usuario == null){
            return ResponseEntity.notFound().build();
        }

        enviarCorreoPassword(usuario);

        return ResponseEntity.ok("Mail send");
    }

    /**
     * Método para enviar un correo de confirmación de cambio de contraseña
     * Se genera un token JWT para confirmar el cambio de contraseña con una duración de 2 horas
     * @param usuario Usuario al que se le enviará el correo
     */
    protected static void enviarCorreoPassword(Usuario usuario){

        String token = JWTAuthtenticationConfig.getJWTToken(Constans.TOKEN_EXPIRATION_TIME_PASSWORD, usuario.getCorreo(), Constans.ENUM_ROLES.Passwd);

        emailService.sendPasswordSetupEmail(usuario, token);
    }
}