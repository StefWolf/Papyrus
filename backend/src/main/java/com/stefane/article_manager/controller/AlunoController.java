package com.stefane.article_manager.controller;

import com.stefane.article_manager.dto.AlunoDTO;
import com.stefane.article_manager.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('PROFESSOR', 'ADM')")
    public ResponseEntity<AlunoDTO> cadastrar(@RequestBody @Valid AlunoDTO dto) {

        AlunoDTO aluno = alunoService.cadastrar(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(aluno);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('PROFESSOR', 'ADM')")
    public ResponseEntity<List<AlunoDTO>> listarTodos() {

        return ResponseEntity.ok(alunoService.listarTodos());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'ADM')")
    public ResponseEntity<AlunoDTO> buscarPorId(@PathVariable Long id) {

        return ResponseEntity.ok(alunoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'ADM')")
    public ResponseEntity<AlunoDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid AlunoDTO dto
    ) {

        return ResponseEntity.ok(alunoService.atualizar(id, dto));
    }

    @PatchMapping("/{id}/finalizar")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'ADM')")
    public ResponseEntity<AlunoDTO> finalizar(@PathVariable Long id) {

        return ResponseEntity.ok(alunoService.finalizarAluno(id));
    }
}
