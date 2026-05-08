package com.stefane.article_manager.controller;

import com.stefane.article_manager.dto.*;
import com.stefane.article_manager.model.Periodico;
import com.stefane.article_manager.repository.*;
import com.stefane.article_manager.exception.RegraNegocioException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/periodicos")
public class PeriodicoController {

    @Autowired
    private PeriodicoRepository periodicoRepository;

    @Autowired
    private ArtigoRepository artigoRepository;

    @PostMapping
    @PreAuthorize("hasRole('ALUNO')")
    public ResponseEntity<?> criar(@Valid @RequestBody PeriodicoRequestDTO dto) {

        if (periodicoRepository.existsByNomeIgnoreCase(dto.getNome())) {
            throw new RegraNegocioException("Já existe um periódico com esse nome");
        }

        Periodico p = new Periodico();
        p.setNome(dto.getNome());
        p.setLink(dto.getLink());

        periodicoRepository.save(p);

        return ResponseEntity.ok("Periódico criado com sucesso");
    }

    @GetMapping
    @PreAuthorize("hasRole('ALUNO')")
    public ResponseEntity<List<PeriodicoResponseDTO>> listar() {

        List<PeriodicoResponseDTO> lista = periodicoRepository.findAll()
                .stream()
                .map(p -> {
                    PeriodicoResponseDTO dto = new PeriodicoResponseDTO();
                    dto.setNome(p.getNome());
                    dto.setLink(p.getLink());
                    dto.setQtdArtigosRegistrados(p.getQtdArtigosRegistrados());
                    return dto;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ALUNO')")
    public ResponseEntity<?> atualizar(@PathVariable Long id,
                                       @Valid @RequestBody PeriodicoRequestDTO dto) {

        Periodico p = periodicoRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Periódico não encontrado"));

        p.setNome(dto.getNome());
        p.setLink(dto.getLink());

        periodicoRepository.save(p);

        return ResponseEntity.ok("Periódico atualizado");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ALUNO')")
    public ResponseEntity<?> deletar(@PathVariable Long id) {

        periodicoRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Periódico não encontrado"));

        if (artigoRepository.existsByPeriodico_Id(id)) {
            throw new RegraNegocioException("Periódico possui artigos vinculados");
        }

        periodicoRepository.deleteById(id);

        return ResponseEntity.ok("Periódico deletado");
    }
}