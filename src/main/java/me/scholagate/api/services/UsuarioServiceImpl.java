package me.scholagate.api.services;

import me.scholagate.api.models.Usuario;
import me.scholagate.api.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService{
    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> findAll() {return this.usuarioRepository.findAll();}

    @Override
    public Usuario findById(Integer id) {
        return this.usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public Usuario findByNombre(String nombre) {return this.usuarioRepository.findByNombre(nombre);}

    @Override
    public Usuario findByCorreo(String correo) {
        return this.usuarioRepository.findByNombre(correo);
    }

    @Override
    public Usuario save(Usuario usuario) {return this.usuarioRepository.save(usuario);}

    @Override
    public Usuario update(Usuario usuario) {return this.usuarioRepository.save(usuario);}

    @Override
    public void deleteById(Integer id) {
        this.usuarioRepository.deleteById(id);
    }
}
