package me.scholagate.api.services;


import me.scholagate.api.models.Usuario;

import java.util.List;

public interface UsuarioService {
    //Gets
    List<Usuario> findAll();
    Usuario findById(Integer id);
    Usuario findByCorreo(String correo);
    //Posts
    Usuario save(Usuario usuario);

    //Puts
    Usuario update(Usuario usuario);

    //Deletes
    void deleteById(Integer id);

}
