package com.superdev.helpdesk.controller;

import com.superdev.helpdesk.dto.categoria.CategoriaAtualizarDto;
import com.superdev.helpdesk.dto.categoria.CategoriaCriarDto;
import com.superdev.helpdesk.models.Categoria;
import com.superdev.helpdesk.services.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Categoria> listar(){
        return this.service.listar();
    }

    @PostMapping
    public Categoria criar(
            @RequestBody @Valid CategoriaCriarDto dto){
        return this.service.criar(dto);
    }

    @PutMapping("/{id}")
    public Categoria atuzalizar(@PathVariable int id, @RequestBody @Valid CategoriaAtualizarDto dto) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public Categoria apagar(@PathVariable int id){
        return service.apagar(id);
    }

    @GetMapping("/{id}")
    public Categoria obterPorId(@PathVariable int id) {
        return service.obterPorId(id);
    }
}