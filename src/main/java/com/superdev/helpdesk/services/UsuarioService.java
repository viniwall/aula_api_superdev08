package com.superdev.helpdesk.services;

import com.superdev.helpdesk.dto.usuario.UsuarioAtualizarDto;
import com.superdev.helpdesk.dto.usuario.UsuarioCriarDto;
import com.superdev.helpdesk.exceptions.ConflitoException;
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
        String email = dado.email().trim().toLowerCase();
        // Validar que existe o usuário com o e-mail
        repository.findByEmail(email).ifPresent(usuario -> {
            // Caso existir não deve permitir cadastrar outro usuário com o mesmo erro
            // Vamos lançar uma exceção que status code 409
            throw new ConflitoException("E-mail já cadastrado");
        });

        var usuario = Usuario.builder()
                .nome(dado.nome())
                .email(dado.email())
                .papel(dado.papel())
                .ativa(true)
                .build();
        return this.repository.save(usuario);
    }

    public Usuario atualizar(int id, UsuarioAtualizarDto dado) {
        var usuario = this.repository.findById(id).orElseThrow();

        usuario.setNome(dado.nome());
        usuario.setEmail(dado.email());
        usuario.setPapel(dado.papel());

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
