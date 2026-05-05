package com.stefane.article_manager.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

@Entity
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "area")
    @JsonIgnore
    private List<Artigo> artigos;

    public Area() {}

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<Artigo> getArtigos() {
        return artigos;
    }

    public Integer getQtdArtigosRegistrados() {
        return artigos != null ? artigos.size() : 0;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setArtigos(List<Artigo> artigos) {
        this.artigos = artigos;
    }
}