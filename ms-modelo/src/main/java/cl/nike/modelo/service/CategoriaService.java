package cl.nike.modelo.service;

import cl.nike.modelo.dto.CategoriaRequest;
import cl.nike.modelo.dto.CategoriaResponse;
import cl.nike.modelo.mapper.CategoriaMapper;
import cl.nike.modelo.model.Categoria;
import cl.nike.modelo.repository.CategoriaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    public List<CategoriaResponse> findAll() {
        return categoriaMapper.toResponseList(categoriaRepository.findAll());
    }

    public CategoriaResponse findById(BigDecimal id) {
        return categoriaMapper.toResponse(getCategoriaById(id));
    }

    @Transactional
    public CategoriaResponse create(CategoriaRequest request) {
        Categoria categoria = categoriaMapper.toEntity(request);
        return categoriaMapper.toResponse(categoriaRepository.save(categoria));
    }

    @Transactional
    public CategoriaResponse update(BigDecimal id, CategoriaRequest request) {
        Categoria categoria = getCategoriaById(id);
        categoriaMapper.updateEntity(request, categoria);
        return categoriaMapper.toResponse(categoriaRepository.save(categoria));
    }

    @Transactional
    public void deleteById(BigDecimal id) {
        Categoria categoria = getCategoriaById(id);
        if (categoria.getModelos() != null && !categoria.getModelos().isEmpty()) {
            throw new RuntimeException("No se puede eliminar la categoría porque tiene modelos asociados");
        }
        categoriaRepository.delete(categoria);
    }

    private Categoria getCategoriaById(BigDecimal id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + id));
    }
}