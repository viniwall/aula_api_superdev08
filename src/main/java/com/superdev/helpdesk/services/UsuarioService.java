package com.superdev.helpdesk.services;

import com.superdev.helpdesk.dto.usuario.UsuarioAtualizarDto;
import com.superdev.helpdesk.dto.usuario.UsuarioCriarDto;
import com.superdev.helpdesk.models.Usuario;
import com.superdev.helpdesk.repositories.UsuarioRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepositorio repository;

    public UsuarioService(UsuarioRepositorio repository) {
        this.repository = repository;
    }

    public List<Usuario> Listar() {
        return this.repository.findAll();
    }

    public List<Usuario> listar() {
        return this.repository.findAll();
    }

    public Usuario criar(UsuarioCriarDto dado) {
        var usuario = Usuario.builder()
                .nome(dado.nome())
                .email(dado.email())
                .ativa(true)
                .build();
        return this.repository.save(usuario);
    }

    public Usuario atualizar(int id, UsuarioAtualizarDto dado) {
        var usuario = this.repository.findById(id).orElseThrow();

        usuario.setNome(dado.nome());
        usuario.setEmail(dado.email());

        return repository.save(usuario);
    }

    public Usuario apagar(int id) {
        var usuario = repository.findById(id).orElseThrow();

        usuario.setAtiva(false);
        return repository.save(usuario);
    }

    public Usuario obterPorId(int id) {
        var usuario = repository.findById(id).orElseThrow();
        return usuario;
    }
}
