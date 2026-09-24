package com.superdev.helpdesk.repositories;

import com.superdev.helpdesk.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepositorio extends JpaRepository<Categoria, Integer> {
}
