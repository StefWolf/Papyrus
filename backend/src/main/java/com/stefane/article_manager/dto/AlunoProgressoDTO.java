package com.stefane.article_manager.dto;

public class AlunoProgressoDTO {

    private Long alunoId;

    private String nomeAluno;

    private String status;

    private String comentarioProfessor;

    private Long totalArtigos;

    private Long totalAutores;

    private Long totalAreas;

    private Long totalPeriodicos;

    public AlunoProgressoDTO() {
    }

    public AlunoProgressoDTO(Long alunoId, String nomeAluno,
                              String status, String comentarioProfessor,
                              Long totalArtigos, Long totalAutores,
                              Long totalAreas, Long totalPeriodicos) {

        this.alunoId = alunoId;
        this.nomeAluno = nomeAluno;
        this.status = status;
        this.comentarioProfessor = comentarioProfessor;
        this.totalArtigos = totalArtigos;
        this.totalAutores = totalAutores;
        this.totalAreas = totalAreas;
        this.totalPeriodicos = totalPeriodicos;
    }

    public Long getAlunoId() {
        return alunoId;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public String getStatus() {
        return status;
    }

    public String getComentarioProfessor() {
        return comentarioProfessor;
    }

    public Long getTotalArtigos() {
        return totalArtigos;
    }

    public Long getTotalAutores() {
        return totalAutores;
    }

    public Long getTotalAreas() {
        return totalAreas;
    }

    public Long getTotalPeriodicos() {
        return totalPeriodicos;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setComentarioProfessor(String comentarioProfessor) {
        this.comentarioProfessor = comentarioProfessor;
    }

    public void setTotalArtigos(Long totalArtigos) {
        this.totalArtigos = totalArtigos;
    }

    public void setTotalAutores(Long totalAutores) {
        this.totalAutores = totalAutores;
    }

    public void setTotalAreas(Long totalAreas) {
        this.totalAreas = totalAreas;
    }

    public void setTotalPeriodicos(Long totalPeriodicos) {
        this.totalPeriodicos = totalPeriodicos;
    }
}
