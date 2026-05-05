package com.stefane.article_manager.model;

import com.stefane.article_manager.enums.StatusArtigo;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Artigo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String doi;
    private Integer ano;
    private String observacoes;

    @Enumerated(EnumType.STRING)
    private StatusArtigo status;

    @ManyToOne
    private Area area;

    @ManyToOne
    private Periodico periodico;

    @ManyToMany
    @JoinTable(
        name = "artigo_autor",
        joinColumns = @JoinColumn(name = "artigo_id"),
        inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autores;

    public Artigo() {}

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getAno() {
        return ano;
    }

    public Periodico getPeriodico() {
        return periodico;
    }

    public Area getArea() {
        return area;
    }

    public String getDoi() {
        return doi;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public StatusArtigo getStatus() {
        return status;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public void setPeriodico(Periodico periodico) {
        this.periodico = periodico;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public void setStatus(StatusArtigo status) {
        this.status = status;
    }
}