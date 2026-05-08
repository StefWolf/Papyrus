package com.stefane.article_manager.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "aluno_progresso")
public class AlunoProgresso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comentarioProfessor;

    private LocalDateTime dataAtualizacao;

    @OneToOne
    @JoinColumn(name = "aluno_id", nullable = false, unique = true)
    private Aluno aluno;

    public AlunoProgresso() {
    }

    public AlunoProgresso(Aluno aluno) {
        this.aluno = aluno;
        this.dataAtualizacao = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getComentarioProfessor() {
        return comentarioProfessor;
    }

    public void setComentarioProfessor(String comentarioProfessor) {
        this.comentarioProfessor = comentarioProfessor;
        this.dataAtualizacao = LocalDateTime.now();
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

}
