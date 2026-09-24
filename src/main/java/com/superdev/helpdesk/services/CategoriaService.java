package com.superdev.helpdesk.services;

import com.superdev.helpdesk.dto.categoria.CategoriaAtualizarDto;
import com.superdev.helpdesk.dto.categoria.CategoriaCriarDto;
import com.superdev.helpdesk.models.Categoria;
import com.superdev.helpdesk.repositories.CategoriaRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    private final CategoriaRepositorio repository;

    public CategoriaService(CategoriaRepositorio repository) {
        this.repository = repository;
    }

    public List<Categoria> Listar() {
        return this.repository.findAll();
    }

    public List<Categoria> listar() {
        return this.repository.findAll();
    }

    public Categoria criar(CategoriaCriarDto dado){
        var categoria = Categoria.builder()
                .nome(dado.nome())
                .descricao(dado.descricao())
                .ativa(true)
                .build();
        return this.repository.save(categoria);
    }

    public Categoria atualizar(int id, CategoriaAtualizarDto dado) {
        var categoria = this.repository.findById(id)
                .orElseThrow();

        categoria.setNome(dado.nome());
        categoria.setDescricao(dado.descricao());

        return repository.save(categoria);
    }

    public Categoria apagar(int id) {
        var categoria = repository.findById(id).orElseThrow();

        categoria.setAtiva(false);
        return repository.save(categoria);
    }

    public Categoria obterPorId(int id) {
        var categoria = repository.findById(id).orElseThrow();
        return categoria;
    }
}
