package com.stefane.article_manager.service;

import com.stefane.article_manager.dto.AlunoProgressoDTO;
import com.stefane.article_manager.dto.ComentarioAlunoDTO;
import com.stefane.article_manager.model.Aluno;
import com.stefane.article_manager.model.AlunoProgresso;
import com.stefane.article_manager.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AlunoProgressoService {

    private final AlunoRepository alunoRepository;
    private final AlunoProgressoRepository progressoRepository;

    private final ArtigoRepository artigoRepository;
    private final AutorRepository autorRepository;
    private final AreaRepository areaRepository;
    private final PeriodicoRepository periodicoRepository;

    public AlunoProgressoService(
            AlunoRepository alunoRepository,
            AlunoProgressoRepository progressoRepository,
            ArtigoRepository artigoRepository,
            AutorRepository autorRepository,
            AreaRepository areaRepository,
            PeriodicoRepository periodicoRepository
    ) {
        this.alunoRepository = alunoRepository;
        this.progressoRepository = progressoRepository;
        this.artigoRepository = artigoRepository;
        this.autorRepository = autorRepository;
        this.areaRepository = areaRepository;
        this.periodicoRepository = periodicoRepository;
    }

    public AlunoProgressoDTO visualizarProgresso(Long alunoId) {
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado"));

        AlunoProgresso progresso = progressoRepository.findByAlunoId(alunoId)
                .orElseGet(() -> progressoRepository.save(new AlunoProgresso(aluno)));

        Long usuarioId = aluno.getUsuario().getId();

        Long totalArtigos = artigoRepository.countByCriadoPorId(usuarioId);
        Long totalAutores = autorRepository.countByCriadoPorId(usuarioId);
        Long totalAreas = areaRepository.countByCriadoPorId(usuarioId);
        Long totalPeriodicos = periodicoRepository.countByCriadoPorId(usuarioId);

        return new AlunoProgressoDTO(
                aluno.getId(),
                aluno.getUsuario().getNome(),
                aluno.getStatus().name(),
                progresso.getComentarioProfessor(),
                totalArtigos,
                totalAutores,
                totalAreas,
                totalPeriodicos
        );
    }

    public AlunoProgressoDTO atualizarComentario(Long alunoId, ComentarioAlunoDTO dto) {
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado"));

        AlunoProgresso progresso = progressoRepository.findByAlunoId(alunoId)
                .orElseGet(() -> progressoRepository.save(new AlunoProgresso(aluno)));

        progresso.setComentarioProfessor(dto.getComentario());
        progresso.setDataAtualizacao(LocalDateTime.now());

        progressoRepository.save(progresso);

        return visualizarProgresso(alunoId);
    }
}
