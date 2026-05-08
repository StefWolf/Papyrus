package com.stefane.article_manager.dto;

import jakarta.validation.constraints.NotBlank;

public class ComentarioAlunoDTO {

    @NotBlank(message = "Comentário é obrigatório")
    private String comentario;

    public ComentarioAlunoDTO() {
    }

    public ComentarioAlunoDTO(String comentario) {
        this.comentario = comentario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
