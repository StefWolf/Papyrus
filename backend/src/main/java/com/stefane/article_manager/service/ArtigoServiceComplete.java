package com.stefane.article_manager.service;

import com.stefane.article_manager.audit.model.AuditLog;
import com.stefane.article_manager.audit.repository.AuditLogRepository;
import com.stefane.article_manager.dto.*;
import com.stefane.article_manager.model.*;
import com.stefane.article_manager.repository.*;
import com.stefane.article_manager.exception.RegraNegocioException;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Qualifier("complete")
public class ArtigoServiceComplete implements ArtigoService {


    @Autowired
    private ArtigoRepository artigoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private PeriodicoRepository periodicoRepository;

    @Autowired
    private AuditLogRepository auditLogRepository;
    

    @Override
    public ArtigoResponseDTO criar(ArtigoRequestDTO dto) {
        Artigo artigo = montarArtigo(dto);

        Artigo artigoSalvo = artigoRepository.save(artigo);

        AuditLog log = new AuditLog(
            "Artigo",
            artigoSalvo.getId(),
            "CREATE"
         );


         auditLogRepository.save(log);

        return converterParaDTO(artigoSalvo);
    }

    @Override
    public List<ArtigoResponseDTO> listar() {
        return artigoRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    

    @Override
    public ArtigoResponseDTO buscarPorId(Long id) {
        Artigo artigo = artigoRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Artigo não encontrado"));

        return converterParaDTO(artigo);
    }

     @Override
    public ArtigoResponseDTO atualizar(Long id, ArtigoRequestDTO dto) {
        Artigo artigoExistente = artigoRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Artigo não encontrado"));

        Artigo atualizado = montarArtigo(dto);
        atualizado.setId(id);
        atualizado.setCriadoPor(artigoExistente.getCriadoPor());

        Artigo artigoSalvo = artigoRepository.save(atualizado);

        return converterParaDTO(artigoSalvo);
    }

    @Override
    public void deletar(Long id) {
        if (!artigoRepository.existsById(id)) {
            throw new RegraNegocioException("Artigo não encontrado");
        }

        artigoRepository.deleteById(id);
    }

    private Artigo montarArtigo(ArtigoRequestDTO dto) {

        List<Long> autoresIds = dto.getAutoresIds();

        if (autoresIds == null || autoresIds.isEmpty()) {
          throw new RegraNegocioException("O artigo deve ter pelo menos um autor");
        }

        List<Autor> autores = autorRepository.findAllById(autoresIds);

        if (autores.size() != autoresIds.size()) {
        throw new RegraNegocioException("Um ou mais autores não foram encontrados");
        }

        Area area = areaRepository.findById(dto.getAreaId())
                .orElseThrow(() -> new RegraNegocioException("Área não encontrada"));

        Periodico periodico = periodicoRepository.findById(dto.getPeriodicoId())
                .orElseThrow(() -> new RegraNegocioException("Periódico não encontrado"));

        Artigo artigo = new Artigo();
        artigo.setNome(dto.getNome());
        artigo.setAno(dto.getAno());
        artigo.setAutores(autores);
        artigo.setArea(area);
        artigo.setPeriodico(periodico);
        artigo.setDoi(dto.getDoi());
        artigo.setObservacoes(dto.getObservacoes());
        artigo.setStatus(dto.getStatus());

        Usuario usuarioLogado = usuarioService.getUsuarioLogado();
        artigo.setCriadoPor(usuarioLogado);

        return artigo;
    }

    private ArtigoResponseDTO converterParaDTO(Artigo artigo) {

        ArtigoResponseDTO dto = new ArtigoResponseDTO();

       // dto.setId(artigo.getId());
        dto.setNome(artigo.getNome());
        dto.setAno(artigo.getAno());

        dto.setAutores(
                artigo.getAutores().stream()
                        .map(Autor::getNome)
                        .toList()
        );

        dto.setArea(artigo.getArea().getNome());
        dto.setPeriodico(artigo.getPeriodico().getNome());
        dto.setDoi(artigo.getDoi());
        dto.setObservacoes(artigo.getObservacoes());
        dto.setStatus(artigo.getStatus());

        return dto;
    }
}