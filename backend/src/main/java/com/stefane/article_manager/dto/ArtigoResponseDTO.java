package com.stefane.article_manager.dto;

import com.stefane.article_manager.enums.StatusArtigo;
import java.util.List;

public class ArtigoResponseDTO {

    private String nome;
    private Integer ano;
    private List<String> autores;
    private String periodico;
    private String area;
    private String doi;
    private String observacoes;
    private StatusArtigo status;

    // GETTERS E SETTERS

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

    public List<String> getAutores() {
        return autores;
    }

    public void setAutores(List<String> autores) {
        this.autores = autores;
    }

    public String getPeriodico() {
        return periodico;
    }

    public void setPeriodico(String periodico) {
        this.periodico = periodico;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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