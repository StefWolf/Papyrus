package com.stefane.article_manager.dto;

public class AutorResponseDTO {

    private String nome;
    private String lattes;
    private String email;
    private Integer qtdArtigosRegistrados;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLattes() {
        return lattes;
    }

    public void setLattes(String lattes) {
        this.lattes = lattes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getQtdArtigosRegistrados() {
        return qtdArtigosRegistrados;
    }

    public void setQtdArtigosRegistrados(Integer qtdArtigosRegistrados) {
        this.qtdArtigosRegistrados = qtdArtigosRegistrados;
    }

    

}