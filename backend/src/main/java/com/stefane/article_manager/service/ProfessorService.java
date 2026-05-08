package com.stefane.article_manager.service;

import com.stefane.article_manager.dto.ProfessorDTO;
import com.stefane.article_manager.model.*;
import com.stefane.article_manager.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public ProfessorService(
            ProfessorRepository professorRepository,
            UsuarioRepository usuarioRepository,
            UsuarioRoleRepository roleRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.professorRepository = professorRepository;
        this.usuarioRepository = usuarioRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ProfessorDTO cadastrar(ProfessorDTO dto) {
        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        UsuarioRole roleProfessor = roleRepository.findByName("ROLE_PROFESSOR")
                .orElseThrow(() -> new EntityNotFoundException("ROLE_PROFESSOR não encontrada"));

        Usuario usuario = new Usuario(
                dto.getNome(),
                dto.getEmail(),
                passwordEncoder.encode(dto.getSenha()),
                roleProfessor
        );

        usuarioRepository.save(usuario);

        Professor professor = new Professor(
                dto.getFormacao(),
                usuario
        );

        return toDTO(professorRepository.save(professor));
    }

    public List<ProfessorDTO> listarTodos() {
        return professorRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public ProfessorDTO buscarPorId(Long id) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Professor não encontrado"));

        return toDTO(professor);
    }

    public ProfessorDTO atualizar(Long id, ProfessorDTO dto) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Professor não encontrado"));

        Usuario usuario = professor.getUsuario();

        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());

        if (dto.getSenha() != null && !dto.getSenha().isBlank()) {
            usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        }

        professor.setFormacao(dto.getFormacao());

        usuarioRepository.save(usuario);
        Professor professorAtualizado = professorRepository.save(professor);

        return toDTO(professorAtualizado);
    }

    public void deletar(Long id) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Professor não encontrado"));

        professorRepository.delete(professor);
        usuarioRepository.delete(professor.getUsuario());
    }

    private ProfessorDTO toDTO(Professor professor) {
        ProfessorDTO dto = new ProfessorDTO();

        dto.setId(professor.getId());
        dto.setNome(professor.getUsuario().getNome());
        dto.setEmail(professor.getUsuario().getEmail());
        dto.setSenha(null);
        dto.setFormacao(professor.getFormacao());

        return dto;
    }
}
