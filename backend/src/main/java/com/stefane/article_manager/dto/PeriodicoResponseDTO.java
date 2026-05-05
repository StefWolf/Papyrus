package com.stefane.article_manager.dto;

public class PeriodicoResponseDTO {
    private String nome;
    private String link;
    private Integer qtdArtigosRegistrados;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getQtdArtigosRegistrados() {
        return qtdArtigosRegistrados;
    }

    public void setQtdArtigosRegistrados(Integer qtdArtigosRegistrados) {
        this.qtdArtigosRegistrados = qtdArtigosRegistrados;
    }

}
