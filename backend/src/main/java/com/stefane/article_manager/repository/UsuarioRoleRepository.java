package com.stefane.article_manager.repository;

import com.stefane.article_manager.model.UsuarioRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRoleRepository extends JpaRepository<UsuarioRole, Long> {

    Optional<UsuarioRole> findByName(String name);
}