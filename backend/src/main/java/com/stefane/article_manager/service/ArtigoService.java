package com.stefane.article_manager.service;

import java.util.List;

import com.stefane.article_manager.dto.ArtigoRequestDTO;
import com.stefane.article_manager.dto.ArtigoResponseDTO;


public interface ArtigoService {

    ArtigoResponseDTO criar(ArtigoRequestDTO dto);

    List<ArtigoResponseDTO> listar();

    ArtigoResponseDTO buscarPorId(Long id);

    ArtigoResponseDTO atualizar(Long id, ArtigoRequestDTO dto);

    void deletar(Long id);
}
