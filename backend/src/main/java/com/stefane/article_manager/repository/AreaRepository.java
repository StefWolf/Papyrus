package com.stefane.article_manager.repository;

import com.stefane.article_manager.model.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaRepository extends JpaRepository<Area, Long> {


    List<Area> findByNomeContainingIgnoreCase(String nome);
    boolean existsByNomeIgnoreCase(String nome);
    Long countByCriadoPorId(Long usuarioId);

}