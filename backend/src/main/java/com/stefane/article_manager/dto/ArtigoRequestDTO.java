package com.stefane.article_manager.dto;

import com.stefane.article_manager.enums.StatusArtigo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class ArtigoRequestDTO {

    @NotBlank(message = "O nome do artigo é obrigatório")
    @Size(min = 3, max = 200, message = "O nome deve ter entre 3 e 200 caracteres")
    private String nome;

    @NotNull(message = "O ano é obrigatório")
    @Min(value = 1900, message = "O ano deve ser maior que 1900")
    @Max(value = 2100, message = "O ano não pode ser maior que 2100")
    private Integer ano;

    @NotEmpty(message = "O artigo deve ter pelo menos um autor")
    private List<Long> autoresIds;

    @NotNull(message = "O periódico é obrigatório")
    private Long periodicoId;

    @NotNull(message = "A área é obrigatória")
    private Long areaId;

    @Size(max = 100, message = "O DOI deve ter no máximo 100 caracteres")
    private String doi;

    @Size(max = 500, message = "Observações devem ter no máximo 500 caracteres")
    private String observacoes;

    @NotNull(message = "O status é obrigatório")
    private StatusArtigo status;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public List<Long> getAutoresIds() {
        return autoresIds;
    }

    public void setAutoresIds(List<Long> autoresIds) {
        this.autoresIds = autoresIds;
    }

    public Long getPeriodicoId() {
        return periodicoId;
    }

    public void setPeriodicoId(Long periodicoId) {
        this.periodicoId = periodicoId;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public StatusArtigo getStatus() {
        return status;
    }

    public void setStatus(StatusArtigo status) {
        this.status = status;
    }
}