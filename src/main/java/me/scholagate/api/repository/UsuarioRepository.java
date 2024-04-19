package org.example.chatappapi.repository;

import org.example.chatappapi.model.Usuario;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface  UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByNombre(String nombre);
}

