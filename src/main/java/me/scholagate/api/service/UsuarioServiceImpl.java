package org.example.chatappapi.service;

import org.example.chatappapi.model.Usuario;
import org.example.chatappapi.repository.UsuarioRepository;
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
    public Usuario findByNombre(String uuid) {return this.usuarioRepository.findByNombre(uuid);}

    @Override
    public Usuario save(Usuario usuario) {return this.usuarioRepository.save(usuario);}

    @Override
    public Usuario update(Usuario usuario) {return this.usuarioRepository.save(usuario);}

    @Override
    public void deleteById(Integer id) {
        this.usuarioRepository.deleteById(id);
    }
}
