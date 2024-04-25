package me.scholagate.api.controller;

import com.password4j.Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AutenticacionController {

    @Autowired
    private final UsuarioService usuarioService;
    @Autowired
    private final PasswordService passwordService;
    private static final Logger logger = LoggerFactory.getLogger(AutenticacionController.class);

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
    public ResponseEntity<Usuario> login(@RequestBody Credenciales credenciales ){

        Usuario usuario = this.usuarioService.findByNombre(credenciales.getNombreUsuario());

        if (usuario == null){
            logger.info("usuario "+credenciales.getNombreUsuario()+" no encontrado");
            return ResponseEntity.notFound().build();
        }

        Password password = this.passwordService.findById(usuario.getId());

        if (password == null){
            return ResponseEntity.notFound().build();
        }


        if (Encriptacion.comprobarPasswd(credenciales.getPassword(), password.getHashResult(), password.getSalt())) {


            usuario.setEstado("Conectado");
            usuarioService.update(usuario);

            String token = JWTAuthtenticationConfig.getJWTToken(usuario.getNombre());
            usuario.setToken(token);

            return ResponseEntity.ok(usuario);
        }else {
            logger.info("Contraseña incorrecta para usuario " + credenciales.getNombreUsuario());
            return ResponseEntity.badRequest().build();
        }

    }

    //Logout
    @PostMapping("/logout")
    public ResponseEntity<Usuario> logout(@RequestHeader("Authorization") String token){

        logger.info("Usuario: "+JWTAuthtenticationConfig.getUsernameFromToken(token) +"desconectado");
        Usuario usuario = this.usuarioService.findByNombre(JWTAuthtenticationConfig.getUsernameFromToken(token));

        if (usuario != null) {
            usuario.setEstado("Desconectado");
            usuario = usuarioService.update(usuario);

            return ResponseEntity.ok(usuario);
        }else {
            return ResponseEntity.badRequest().build();
        }

    }


    //Registro
    @PostMapping("/register")
    public ResponseEntity<Credenciales> register(@RequestBody Credenciales credenciales){

            Usuario usuario = this.usuarioService.findByNombre(credenciales.getNombreUsuario());

            if (usuario != null){
                logger.info("Usuario con nombre: "+credenciales.getNombreUsuario()+" ya existe");
                return ResponseEntity.badRequest().build();
            }

            usuario = new Usuario();
            usuario.setNombre(credenciales.getNombreUsuario());
            usuario.setEstado("Desconectado");

            usuario =   this.usuarioService.save(usuario);

            Password password = new Password();
            password.setUsuarios(usuario);

            Hash hash = Encriptacion.generarHash(credenciales.getPassword());
            password.setHashResult(hash.getResult());
            password.setSalt(hash.getSaltBytes());

            this.passwordService.save(password);

            logger.info("Usuario "+credenciales.getNombreUsuario()+" registrado correctamente");

            return ResponseEntity.ok(credenciales);
    }

}