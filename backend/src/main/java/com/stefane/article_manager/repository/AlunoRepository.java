package com.stefane.article_manager.repository;

import com.stefane.article_manager.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Optional<Aluno> findByNumeroMatricula(String numeroMatricula);

    boolean existsByNumeroMatricula(String numeroMatricula);

    Optional<Aluno> findByUsuarioEmail(String email);
}
