package com.stefane.article_manager.dto;

import jakarta.validation.constraints.*;

public class AlunoDTO {

    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @Email(message = "Email inválido")
    @NotBlank(message = "Email é obrigatório")
    private String email;

    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
    private String senha;

    @NotBlank(message = "Matrícula é obrigatória")
    private String numeroMatricula;

    @NotNull(message = "Semestre é obrigatório")
    private Integer semestre;

    @NotBlank(message = "Curso é obrigatório")
    private String curso;

    private String status;

    public AlunoDTO() {
    }

    public AlunoDTO(Long id, String nome, String email, String senha,
                     String numeroMatricula, Integer semestre,
                     String curso, String status) {

        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.numeroMatricula = numeroMatricula;
        this.semestre = semestre;
        this.curso = curso;
        this.status = status;
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

    public String getNumeroMatricula() {
        return numeroMatricula;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public String getCurso() {
        return curso;
    }

    public String getStatus() {
        return status;
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

    public void setNumeroMatricula(String numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
