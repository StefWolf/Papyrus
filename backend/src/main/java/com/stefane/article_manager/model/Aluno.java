package com.stefane.article_manager.model;

import jakarta.persistence.*;

@Entity
@Table(name = "alunos")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroMatricula;
    private Integer semestre;
    private String curso;

    @Enumerated(EnumType.STRING)
    private StatusAluno status;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private Usuario usuario;

    public Aluno() {
    }

    public Aluno(String numeroMatricula, Integer semestre, String curso, StatusAluno status, Usuario usuario) {
        this.numeroMatricula = numeroMatricula;
        this.semestre = semestre;
        this.curso = curso;
        this.status = status;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public String getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(String numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public StatusAluno getStatus() {
        return status;
    }

    public void setStatus(StatusAluno status) {
        this.status = status;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}