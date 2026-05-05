package com.stefane.article_manager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Periodico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String link;

    @OneToMany(mappedBy = "periodico")
    @JsonIgnore
    private List<Artigo> artigos;

    public Integer getQtdArtigosRegistrados() {
        return artigos != null ? artigos.size() : 0;
    }

    public Periodico() {}

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getLink() {
        return link;
    }

    public List<Artigo> getArtigos() {
        return artigos;
    }

    //public Integer getQtdArtigosRegistrados() {
      //  return artigos != null ? artigos.size() : 0;
  //  }
  

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setArtigos(List<Artigo> artigos) {
        this.artigos = artigos;
    }
}