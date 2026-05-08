package com.stefane.article_manager.controller;

import com.stefane.article_manager.dto.ProfessorDTO;
import com.stefane.article_manager.service.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADM')")
    public ResponseEntity<ProfessorDTO> cadastrar(
            @RequestBody @Valid ProfessorDTO dto
    ) {

        ProfessorDTO professor = professorService.cadastrar(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(professor);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADM')")
    public ResponseEntity<List<ProfessorDTO>> listarTodos() {

        return ResponseEntity.ok(professorService.listarTodos());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADM')")
    public ResponseEntity<ProfessorDTO> buscarPorId(@PathVariable Long id) {

        return ResponseEntity.ok(professorService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADM')")
    public ResponseEntity<ProfessorDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid ProfessorDTO dto
    ) {

        return ResponseEntity.ok(professorService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADM')")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        professorService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}