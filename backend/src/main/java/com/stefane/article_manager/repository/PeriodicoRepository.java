package com.stefane.article_manager.repository;

import com.stefane.article_manager.model.Periodico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeriodicoRepository extends JpaRepository<Periodico, Long> {

    boolean existsByNomeIgnoreCase(String nome);

    List<Periodico> findByNomeContainingIgnoreCase(String nome);

    List<Periodico> findAllByOrderByNomeAsc();

    boolean existsByLink(String link);
}