package com.stefane.article_manager.service;

import com.stefane.article_manager.dto.AlunoDTO;
import com.stefane.article_manager.model.*;
import com.stefane.article_manager.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioRoleRepository roleRepository;
    private final AlunoProgressoRepository progressoRepository;
    private final PasswordEncoder passwordEncoder;

    public AlunoService(
            AlunoRepository alunoRepository,
            UsuarioRepository usuarioRepository,
            UsuarioRoleRepository roleRepository,
            AlunoProgressoRepository progressoRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.alunoRepository = alunoRepository;
        this.usuarioRepository = usuarioRepository;
        this.roleRepository = roleRepository;
        this.progressoRepository = progressoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AlunoDTO cadastrar(AlunoDTO dto) {
        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        if (alunoRepository.existsByNumeroMatricula(dto.getNumeroMatricula())) {
            throw new IllegalArgumentException("Matrícula já cadastrada");
        }

        UsuarioRole roleAluno = roleRepository.findByName("ROLE_ALUNO")
                .orElseThrow(() -> new EntityNotFoundException("ROLE_ALUNO não encontrada"));

        Usuario usuario = new Usuario(
                dto.getNome(),
                dto.getEmail(),
                passwordEncoder.encode(dto.getSenha()),
                roleAluno
        );

        usuarioRepository.save(usuario);

        Aluno aluno = new Aluno(
                dto.getNumeroMatricula(),
                dto.getSemestre(),
                dto.getCurso(),
                StatusAluno.CURSANDO,
                usuario
        );

        Aluno alunoSalvo = alunoRepository.save(aluno);

        AlunoProgresso progresso = new AlunoProgresso(alunoSalvo);
        progressoRepository.save(progresso);

        return toDTO(alunoSalvo);
    }

    public List<AlunoDTO> listarTodos() {
        return alunoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public AlunoDTO buscarPorId(Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado"));

        return toDTO(aluno);
    }

    public AlunoDTO atualizar(Long id, AlunoDTO dto) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado"));

        Usuario usuario = aluno.getUsuario();

        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());

        if (dto.getSenha() != null && !dto.getSenha().isBlank()) {
            usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        }

        aluno.setNumeroMatricula(dto.getNumeroMatricula());
        aluno.setSemestre(dto.getSemestre());
        aluno.setCurso(dto.getCurso());

        usuarioRepository.save(usuario);
        Aluno alunoAtualizado = alunoRepository.save(aluno);

        return toDTO(alunoAtualizado);
    }

    public AlunoDTO finalizarAluno(Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado"));

        aluno.setStatus(StatusAluno.FINALIZADO);

        return toDTO(alunoRepository.save(aluno));
    }

    private AlunoDTO toDTO(Aluno aluno) {
        AlunoDTO dto = new AlunoDTO();

        dto.setId(aluno.getId());
        dto.setNome(aluno.getUsuario().getNome());
        dto.setEmail(aluno.getUsuario().getEmail());
        dto.setSenha(null);
        dto.setNumeroMatricula(aluno.getNumeroMatricula());
        dto.setSemestre(aluno.getSemestre());
        dto.setCurso(aluno.getCurso());
        dto.setStatus(aluno.getStatus().name());

        return dto;
    }
}
