package cl.nike.categoria.service;

import cl.nike.categoria.dto.TipoRequest;
import cl.nike.categoria.dto.TipoResponse;
import cl.nike.categoria.mapper.TipoMapper;
import cl.nike.categoria.model.Tipo;
import cl.nike.categoria.repository.TipoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoService {

    private final TipoRepository tipoRepository;
    private final TipoMapper tipoMapper;
    

    public List<TipoResponse> findAll() {
        return tipoMapper.toResponseList(tipoRepository.findAll());
    }

    public TipoResponse findById(BigDecimal id) {
        return tipoMapper.toResponse(getTipoById(id));
    }

    @Transactional
    public TipoResponse create(TipoRequest request) {
        Tipo tipo = tipoMapper.toEntity(request);
        return tipoMapper.toResponse(tipoRepository.save(tipo));
    }

    @Transactional
    public TipoResponse update(BigDecimal id, TipoRequest request) {
        Tipo tipo = getTipoById(id);
        tipoMapper.updateEntity(request, tipo);
        return tipoMapper.toResponse(tipoRepository.save(tipo));
    }

    @Transactional
    public void deleteById(BigDecimal id) {
        Tipo tipo = getTipoById(id);
        // Regla de negocio: Impedir eliminación si tiene categorías asociadas
        if (!tipo.getCategorias().isEmpty()) {
            throw new RuntimeException("No se puede eliminar el Tipo porque tiene categorías asociadas");
        }
        tipoRepository.delete(tipo);
    }

    private Tipo getTipoById(BigDecimal id) {
        return tipoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Tipo no encontrado con ID: " + id));
    }
}