package me.scholagate.api.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import me.scholagate.api.dtos.UsuarioDto;
import me.scholagate.api.models.Usuario;
import me.scholagate.api.securities.JWTAuthtenticationConfig;
import me.scholagate.api.services.EmailService;
import me.scholagate.api.services.UsuarioService;
import me.scholagate.api.utils.Constans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static me.scholagate.api.controllers.AutenticacionController.enviarCorreoPassword;

@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuario Controller", description = "Controlador de Usuario")
public class UsuarioController {

    @Autowired
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @GetMapping()
    public ResponseEntity<Usuario> getUsuario(@RequestHeader("Authorization") String token){

        Usuario usuario = this.usuarioService.findByNombre(JWTAuthtenticationConfig.getUsernameFromToken(token));

        if (usuario == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuario);
    }

    @GetMapping("s")
    public ResponseEntity<List<Usuario>> findAllUsuarios(@RequestHeader("Authorization") String token){

        if (JWTAuthtenticationConfig.getRolesFromToken(token).contains(Constans.ENUM_ROLES.USER)){
            return ResponseEntity.ok(this.usuarioService.findAll());
        }
        return ResponseEntity.status(403).build();
    }


    //TODO: PROBAR SI DEVUELVE ERROR SI ID INCORRECTA
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findUsuario(@PathVariable("id") int id){
        return ResponseEntity.ok(this.usuarioService.findById(id));
    }

    @PostMapping("")
    public ResponseEntity<Usuario> postUsuario(@RequestBody UsuarioDto usuarioDto){

        Usuario usuario = this.usuarioService.findById(usuarioDto.getId());

        if (usuario != null){
            return ResponseEntity.badRequest().build();
        }
        usuario = new Usuario(
                0,
                usuarioDto.getNombre(),
                usuarioDto.getCorreo(),
                usuarioDto.getRol() == null || usuarioDto.getRol().isEmpty() ? Constans.ENUM_ROLES.USER : usuarioDto.getRol()
        );
        usuario = this.usuarioService.save(usuario);

        enviarCorreoPassword(usuario);

        return ResponseEntity.ok(usuario);
    }

    @PutMapping("")
    public ResponseEntity<Usuario> putUsuario(@RequestBody UsuarioDto usuarioDto){

        Usuario usuario = this.usuarioService.findById(usuarioDto.getId());

        if (usuario != null){
            return ResponseEntity.badRequest().build();
        }

        usuario.setNombre(usuarioDto.getNombre());
        usuario.setCorreo(usuarioDto.getCorreo());
        usuario.setRol(usuarioDto.getRol());

        usuario = this.usuarioService.update(usuario);

        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUsuario(@PathVariable("id") int id){

        Usuario usuario = this.usuarioService.findById(id);

        if (usuario != null){
            return ResponseEntity.badRequest().build();
        }

        this.usuarioService.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
