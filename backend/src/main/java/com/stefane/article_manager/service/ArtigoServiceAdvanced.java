package com.stefane.article_manager.service;

import com.stefane.article_manager.dto.ArtigoRequestDTO;
import com.stefane.article_manager.dto.ArtigoResponseDTO;
import com.stefane.article_manager.enums.StatusArtigo;
import com.stefane.article_manager.exception.RegraNegocioException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Qualifier;

@Service
@Qualifier("avancado")
public class ArtigoServiceAdvanced extends ArtigoServiceComplete {

    @Override
    public ArtigoResponseDTO criar(ArtigoRequestDTO dto) {

        validarRegras(dto);

        ArtigoResponseDTO response = super.criar(dto);

        return response;
    }

    @Override
    public ArtigoResponseDTO atualizar(Long id, ArtigoRequestDTO dto) {

        validarRegras(dto);

        return super.atualizar(id, dto);
    }

    private void validarRegras(ArtigoRequestDTO dto) {

        if (dto.getAno() > java.time.Year.now().getValue()) {
            throw new RegraNegocioException("Ano não pode ser no futuro");
        }

        if (dto.getStatus() == StatusArtigo.FINALIZADO &&
                (dto.getObservacoes() == null || dto.getObservacoes().isBlank())) {
            throw new RegraNegocioException("Artigos finalizados precisam de observação");
        }

        if (dto.getStatus() == StatusArtigo.FINALIZADO &&
                (dto.getDoi() == null || dto.getDoi().isBlank())) {
            throw new RegraNegocioException("Artigos finalizados devem possuir DOI");
        }
    }
}