package com.stefane.article_manager.controller;

import com.stefane.article_manager.dto.*;
import com.stefane.article_manager.model.Area;
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
@RequestMapping("/areas")
public class AreaController {

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private ArtigoRepository artigoRepository;

    @PostMapping
    @PreAuthorize("hasRole('ALUNO')")
    public ResponseEntity<?> criar(@Valid @RequestBody AreaRequestDTO dto) {

        if (areaRepository.existsByNomeIgnoreCase(dto.getNome())) {
            throw new RegraNegocioException("Já existe uma área com esse nome");
        }

        Area area = new Area();
        area.setNome(dto.getNome());

        areaRepository.save(area);

        return ResponseEntity.ok("Área criada com sucesso");
    }

    @GetMapping
    @PreAuthorize("hasRole('ALUNO')")
    public ResponseEntity<List<AreaResponseDTO>> listar() {

        List<AreaResponseDTO> lista = areaRepository.findAll()
                .stream()
                .map(a -> {
                    AreaResponseDTO dto = new AreaResponseDTO();
                    dto.setNome(a.getNome());
                    dto.setQtdArtigosRegistrados(a.getQtdArtigosRegistrados());
                    return dto;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ALUNO')")
    public ResponseEntity<?> atualizar(@PathVariable Long id,
                                       @Valid @RequestBody AreaRequestDTO dto) {

        Area area = areaRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Área não encontrada"));

        area.setNome(dto.getNome());

        areaRepository.save(area);

        return ResponseEntity.ok("Área atualizada");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ALUNO')")
    public ResponseEntity<?> deletar(@PathVariable Long id) {

        areaRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Área não encontrada"));

        if (artigoRepository.existsByArea_Id(id)) {
            throw new RegraNegocioException("Área possui artigos vinculados");
        }

        areaRepository.deleteById(id);

        return ResponseEntity.ok("Área deletada");
    }
}