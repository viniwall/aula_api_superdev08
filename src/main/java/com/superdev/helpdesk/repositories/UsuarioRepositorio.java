package com.superdev.helpdesk.repositories;

import com.superdev.helpdesk.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {
}
