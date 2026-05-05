package com.stefane.article_manager.controller;

import com.stefane.article_manager.dto.*;
import com.stefane.article_manager.model.Autor;
import com.stefane.article_manager.repository.*;
import com.stefane.article_manager.exception.RegraNegocioException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private ArtigoRepository artigoRepository;

    @PostMapping
    public ResponseEntity<?> criar(@Valid @RequestBody AutorRequestDTO dto) {

        if (autorRepository.existsByEmail(dto.getEmail())) {
            throw new RegraNegocioException("Já existe um autor com esse email");
        }

        Autor autor = new Autor();
        autor.setNome(dto.getNome());
        autor.setLattes(dto.getLattes());
        autor.setEmail(dto.getEmail());

        autorRepository.save(autor);

        return ResponseEntity.ok("Autor criado com sucesso");
    }

    @GetMapping
    public ResponseEntity<List<AutorResponseDTO>> listar() {

        List<AutorResponseDTO> lista = autorRepository.findAll()
                .stream()
                .map(a -> {
                    AutorResponseDTO dto = new AutorResponseDTO();
                    dto.setNome(a.getNome());
                    dto.setEmail(a.getEmail());
                    dto.setLattes(a.getLattes());
                    dto.setQtdArtigosRegistrados(a.getQtdArtigosRegistrados());
                    return dto;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id,
                                       @Valid @RequestBody AutorRequestDTO dto) {

        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Autor não encontrado"));

        autor.setNome(dto.getNome());
        autor.setEmail(dto.getEmail());
        autor.setLattes(dto.getLattes());

        autorRepository.save(autor);

        return ResponseEntity.ok("Autor atualizado");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {

        autorRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Autor não encontrado"));

        if (artigoRepository.existsByAutores_Id(id)) {
            throw new RegraNegocioException("Autor possui artigos vinculados");
        }

        autorRepository.deleteById(id);

        return ResponseEntity.ok("Autor deletado");
    }
}