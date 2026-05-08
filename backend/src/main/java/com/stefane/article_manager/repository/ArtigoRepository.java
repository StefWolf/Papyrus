package com.stefane.article_manager.repository;

import com.stefane.article_manager.model.Artigo;
import com.stefane.article_manager.enums.StatusArtigo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArtigoRepository extends JpaRepository<Artigo, Long> {

    List<Artigo> findByAno(Integer ano);

    List<Artigo> findByStatus(StatusArtigo status);

    List<Artigo> findByArea_NomeIgnoreCase(String nomeArea);

    List<Artigo> findByAutores_NomeIgnoreCase(String nomeAutor);

    boolean existsByAutores_Id(Long autorId);

    boolean existsByDoi(String doi);

    Long countByCriadoPorId(Long usuarioId);

    boolean existsByPeriodico_Id(Long periodicoId);

    @Query("SELECT a FROM Artigo a WHERE LOWER(a.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Artigo> findByNomeContaining(@Param("nome") String nome);

    @Query("SELECT a FROM Artigo a WHERE a.ano = :ano AND a.status = :status")
    List<Artigo> findByAnoAndStatus(@Param("ano") Integer ano,
                                   @Param("status") StatusArtigo status);
    boolean existsByArea_Id(Long areaId);

    @Query("SELECT a FROM Artigo a JOIN a.autores aut WHERE LOWER(aut.nome) LIKE LOWER(CONCAT('%', :nomeAutor, '%'))")
    List<Artigo> findByAutorNomeContaining(@Param("nomeAutor") String nomeAutor);
}