package com.stefane.article_manager.repository;

import com.stefane.article_manager.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    Optional<Professor> findByUsuarioEmail(String email);
}
