package me.scholagate.api.service;


import me.scholagate.api.model.Usuario;

import java.util.List;

public interface UsuarioService {
    //Gets
    List<Usuario> findAll();
    Usuario findById(Integer id);
    Usuario findByNombre(String nombre);

    //Posts
    Usuario save(Usuario usuario);

    //Puts
    Usuario update(Usuario usuario);

    //Deletes
    void deleteById(Integer id);

}
