package com.stefane.article_manager.dto;

import jakarta.validation.constraints.*;

public class AutorRequestDTO {

    @NotBlank(message = "O nome do autor é obrigatório")
    @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres")
    private String nome;

    @Size(max = 200, message = "O link do Lattes deve ter no máximo 200 caracteres")
    private String lattes;

    @Email(message = "Email inválido")
    @NotBlank(message = "O email é obrigatório")
    private String email;

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


}