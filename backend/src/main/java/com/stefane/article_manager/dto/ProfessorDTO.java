package com.stefane.article_manager.dto;

import jakarta.validation.constraints.*;

public class ProfessorDTO {

    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @Email(message = "Email inválido")
    @NotBlank(message = "Email é obrigatório")
    private String email;

    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
    private String senha;

    @NotBlank(message = "Formação é obrigatória")
    private String formacao;

    public ProfessorDTO() {
    }

    public ProfessorDTO(Long id, String nome, String email,
                         String senha, String formacao) {

        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.formacao = formacao;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getFormacao() {
        return formacao;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }
}
