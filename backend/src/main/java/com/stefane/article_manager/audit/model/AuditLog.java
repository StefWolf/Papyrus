package com.stefane.article_manager.audit.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String entidade;
    private Long recursoId;
    private String operacao;
    private LocalDateTime dataHora;

    public AuditLog() {}

    public AuditLog(String entidade, Long recursoId, String operacao) {
        this.entidade = entidade;
        this.recursoId = recursoId;
        this.operacao = operacao;
        this.dataHora = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getEntidade() {
        return entidade;
    }

    public Long getRecursoId() {
        return recursoId;
    }

    public String getOperacao() {
        return operacao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEntidade(String entidade) {
        this.entidade = entidade;
    }

    public void setRecursoId(Long recursoId) {
        this.recursoId = recursoId;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

}