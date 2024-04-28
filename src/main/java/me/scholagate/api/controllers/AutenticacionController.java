package me.scholagate.api.controllers;

import com.password4j.Hash;
import io.swagger.annotations.Authorization;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.scholagate.api.dtos.CredencialesDto;
import me.scholagate.api.models.Password;
import me.scholagate.api.models.Usuario;
import me.scholagate.api.securities.JWTAuthtenticationConfig;
import me.scholagate.api.services.PasswordService;
import me.scholagate.api.services.UsuarioService;
import me.scholagate.api.utils.Encriptacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
@Tag(name = "Autenticación Controller", description = "Controlador")
public class AutenticacionController {

    @Autowired
    private final UsuarioService usuarioService;
    @Autowired
    private final PasswordService passwordService;


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
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody CredencialesDto credenciales){

            Usuario usuario = this.usuarioService.findByNombre(credenciales.getNombreUsuario());

            if (usuario != null){
                return ResponseEntity.badRequest().build();
            }

            usuario = new Usuario();
            usuario.setNombre(credenciales.getNombreUsuario());
            usuario.setCorreo("jonathanbetancorperdomo@gmail.com");
            usuario.setRol(Usuario.ENUM_ROLES.ADMIN);

            usuario = this.usuarioService.save(usuario);

            Password password = new Password();
            password.setUsuarios(usuario);

            Hash hash = Encriptacion.generarHash(credenciales.getPassword());
            password.setHashResult(hash.getResult());
            password.setSalt(hash.getSaltBytes());

            this.passwordService.save(password);

            return ResponseEntity.ok("ok");
    }

    @Authorization(value = "Bearer")
    @GetMapping("/auth")
    public ResponseEntity<String> auth(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(JWTAuthtenticationConfig.getUsernameFromToken(token));
    }
}