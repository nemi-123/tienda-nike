package cl.nike.categoria.service;

import cl.nike.categoria.dto.CategoriaRequest;
import cl.nike.categoria.dto.CategoriaResponse;
import cl.nike.categoria.mapper.CategoriaMapper;
import cl.nike.categoria.model.Categoria;
import cl.nike.categoria.model.Tipo;
import cl.nike.categoria.repository.CategoriaRepository;
import cl.nike.categoria.repository.TipoRepository;

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

    // NUEVO
    private final TipoRepository tipoRepository;


    public List<CategoriaResponse> findAll() {
        return categoriaMapper.toResponseList(categoriaRepository.findAll());
    }


    public CategoriaResponse findById(BigDecimal id) {
        return categoriaMapper.toResponse(getCategoriaById(id));
    }


    @Transactional
    public CategoriaResponse create(CategoriaRequest request) {

        // convierte Request -> Categoria
        Categoria categoria = categoriaMapper.toEntity(request);


        // NUEVO: busca el tipo real en la BD
        Tipo tipo = tipoRepository.findById(request.getIdTipo())
                .orElseThrow(() ->
                    new RuntimeException(
                        "Tipo no encontrado con ID: " + request.getIdTipo()
                    )
                );


        // NUEVO: asigna el tipo encontrado
        categoria.setTipo(tipo);


        return categoriaMapper.toResponse(
                categoriaRepository.save(categoria)
        );
    }


@Transactional
    public CategoriaResponse update(BigDecimal id, CategoriaRequest request) {
        Categoria categoria = getCategoriaById(id);
        categoriaMapper.updateEntity(request, categoria);
        if (request.getIdTipo() != null) {
            Tipo tipo = tipoRepository.findById(request.getIdTipo())
                    .orElseThrow(() -> new RuntimeException("Tipo no encontrado con ID: " + request.getIdTipo()));
            
            categoria.setTipo(tipo);
        }

        return categoriaMapper.toResponse(
                categoriaRepository.save(categoria)
        );
    }


    @Transactional
    public void deleteById(BigDecimal id) {

        Categoria categoria = getCategoriaById(id);

        categoriaRepository.delete(categoria);
    }


    private Categoria getCategoriaById(BigDecimal id) {

        return categoriaRepository.findById(id)
            .orElseThrow(() ->
                new RuntimeException(
                    "Categoría no encontrada con ID: " + id
                )
            );
    }
}