package com.stefane.article_manager.controller;

import com.stefane.article_manager.dto.ArtigoRequestDTO;
import com.stefane.article_manager.dto.ArtigoResponseDTO;
import com.stefane.article_manager.exception.RegraNegocioException;
import com.stefane.article_manager.service.ArtigoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/artigos")
public class ArtigoController {

    @Autowired
    @Qualifier("avancado") 
    private ArtigoService service;


    @PostMapping
    @PreAuthorize("hasRole('ALUNO')")
    public ResponseEntity<?> criar(@Valid @RequestBody ArtigoRequestDTO dto) {
        try {
            ArtigoResponseDTO response = service.criar(dto);
            return ResponseEntity.ok(response);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('ALUNO')")
    public ResponseEntity<List<ArtigoResponseDTO>> listar(
            @RequestParam(required = false) Integer ano,
            @RequestParam(required = false) String area,
            @RequestParam(required = false) String autor,
            @RequestParam(required = false) String status
    ) {

        List<ArtigoResponseDTO> artigos = service.listar();

        if (ano != null) {
            artigos = artigos.stream()
                    .filter(a -> a.getAno().equals(ano))
                    .toList();
        }

        if (area != null) {
            artigos = artigos.stream()
                    .filter(a -> a.getArea().equalsIgnoreCase(area))
                    .toList();
        }

        if (autor != null) {
            artigos = artigos.stream()
                    .filter(a -> a.getAutores().stream()
                            .anyMatch(nome -> nome.equalsIgnoreCase(autor)))
                    .toList();
        }

        if (status != null) {
            artigos = artigos.stream()
                    .filter(a -> a.getStatus().name().equalsIgnoreCase(status))
                    .toList();
        }

        return ResponseEntity.ok(artigos);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ALUNO')")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.buscarPorId(id));
        } catch (RegraNegocioException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ALUNO')")
    public ResponseEntity<?> atualizar(@PathVariable Long id,
                                       @Valid @RequestBody ArtigoRequestDTO dto) {
        try {
            return ResponseEntity.ok(service.atualizar(id, dto));
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ALUNO')")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            service.deletar(id);
            return ResponseEntity.ok("Artigo removido com sucesso");
        } catch (RegraNegocioException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}