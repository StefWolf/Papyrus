package com.stefane.article_manager.model;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    private String lattes;
    private String email;
    private Integer qtdArtigosRegistrados = 0;

    @ManyToMany(mappedBy = "autores")
    @JsonIgnore
    private List<Artigo> artigos;

    public Autor() {}

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario criadoPor;

    public Long getId() {
        return id;
    }

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

    public List<Artigo> getArtigos() {
        return artigos;
    }

    public void setArtigos(List<Artigo> artigos) {
        this.artigos = artigos;
    }

        public Usuario getCriadoPor() {
            return criadoPor;
        }

        public void setCriadoPor(Usuario criadoPor) {
            this.criadoPor = criadoPor;
        }
}
