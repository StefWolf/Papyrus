package com.stefane.article_manager.controller;

import com.stefane.article_manager.dto.AlunoProgressoDTO;
import com.stefane.article_manager.dto.ComentarioAlunoDTO;
import com.stefane.article_manager.service.AlunoProgressoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alunos/progresso")
public class AlunoProgressoController {

    private final AlunoProgressoService progressoService;

    public AlunoProgressoController(
            AlunoProgressoService progressoService
    ) {
        this.progressoService = progressoService;
    }

    @GetMapping("/{alunoId}")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'ADM')")
    public ResponseEntity<AlunoProgressoDTO> visualizar(
            @PathVariable Long alunoId
    ) {

        return ResponseEntity.ok(
                progressoService.visualizarProgresso(alunoId)
        );
    }

    @PutMapping("/{alunoId}/comentario")
    @PreAuthorize("hasRole('PROFESSOR')")
    public ResponseEntity<AlunoProgressoDTO> atualizarComentario(
            @PathVariable Long alunoId,
            @RequestBody @Valid ComentarioAlunoDTO dto
    ) {

        return ResponseEntity.ok(
                progressoService.atualizarComentario(alunoId, dto)
        );
    }
}
