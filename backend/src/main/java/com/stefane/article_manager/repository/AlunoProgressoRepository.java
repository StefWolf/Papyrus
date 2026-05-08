package com.stefane.article_manager.repository;

import com.stefane.article_manager.model.AlunoProgresso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlunoProgressoRepository extends JpaRepository<AlunoProgresso, Long> {

    Optional<AlunoProgresso> findByAlunoId(Long alunoId);

    boolean existsByAlunoId(Long alunoId);
}
