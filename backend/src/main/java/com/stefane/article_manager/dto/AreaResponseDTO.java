package com.stefane.article_manager.dto;

public class AreaResponseDTO {
    private String nome;
    private Integer qtdArtigosRegistrados;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQtdArtigosRegistrados() {
        return qtdArtigosRegistrados;
    }

    public void setQtdArtigosRegistrados(Integer qtdArtigosRegistrados) {
        this.qtdArtigosRegistrados = qtdArtigosRegistrados;
    }
}
