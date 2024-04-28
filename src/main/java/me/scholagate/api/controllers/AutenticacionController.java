package me.scholagate.api.controllers;

import com.password4j.Hash;
import io.swagger.annotations.Authorization;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.scholagate.api.dtos.CredencialesDto;
import me.scholagate.api.dtos.UsuarioDto;
import me.scholagate.api.models.Password;
import me.scholagate.api.models.Usuario;
import me.scholagate.api.securities.JWTAuthtenticationConfig;
import me.scholagate.api.services.EmailService;
import me.scholagate.api.services.PasswordService;
import me.scholagate.api.services.UsuarioService;
import me.scholagate.api.utils.Encriptacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
@Tag(name = "Autenticación Controller", description = "Controlador")
public class AutenticacionController {

    @Autowired
    private final UsuarioService usuarioService;
    @Autowired
    private final PasswordService passwordService;
    @Autowired
    private EmailService emailService;


    public AutenticacionController(UsuarioService usuarioService, PasswordService passwordService) {
        this.usuarioService = usuarioService;
        this.passwordService = passwordService;
    }

    @GetMapping("/up")
    public ResponseEntity<String> up(){
        return ResponseEntity.ok("up");
    }

    //Login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody CredencialesDto credenciales){

        Usuario usuario = this.usuarioService.findByNombre(credenciales.getNombreUsuario());

        if (usuario == null){
            return ResponseEntity.notFound().build();
        }

        Password password = this.passwordService.findById(usuario.getId());

        if (password == null){
            return ResponseEntity.notFound().build();
        }

        if (Encriptacion.comprobarPasswd(credenciales.getPassword(), password.getHashResult(), password.getSalt())) {

            String token = JWTAuthtenticationConfig.getJWTToken(usuario.getCorreo(), usuario.getRol());

            return ResponseEntity.ok(token);

        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    //Registro
    @PostMapping("/registerAdmin")
    public ResponseEntity<String> register(@RequestBody UsuarioDto usuarioDto){

            List<Usuario> usuarios = this.usuarioService.findAll();
/*
            if (usuarios != null && usuarios.isEmpty()){
                return ResponseEntity.ok().build();
            }
*/
            Usuario usuario = new Usuario();
            usuario.setId(usuarioDto.getId());
            usuario.setNombre(usuarioDto.getNombre());
            usuario.setCorreo(usuarioDto.getCorreo());
            usuario.setRol(Usuario.ENUM_ROLES.ADMIN);
            this.usuarioService.save(usuario);

            //Enviar correo de confirmación de registro
            String token = JWTAuthtenticationConfig.getJWTToken(usuario.getCorreo(), usuario.getRol());
            emailService.sendPasswordSetupEmail(usuario.getCorreo(), token);

            return ResponseEntity.ok("http://localhost:33133/passwd/"+token);
    }

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

        return ResponseEntity.ok("ok");
    }

    @Authorization(value = "Bearer")
    @GetMapping("/auth")
    public ResponseEntity<String> auth(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(JWTAuthtenticationConfig.getUsernameFromToken(token));
    }
}