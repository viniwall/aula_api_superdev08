package com.superdev.helpdesk.repositories;

import com.superdev.helpdesk.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {
    //SELECT * FROM usuarios WHERE email = ?
    Optional<Usuario> findByEmail(String email);
}
