package cl.nike.modelo.service;

import cl.nike.modelo.dto.ModeloRequest;
import cl.nike.modelo.dto.ModeloResponse;
import cl.nike.modelo.mapper.ModeloMapper;
import cl.nike.modelo.model.Modelo;
import cl.nike.modelo.repository.CategoriaRepository;
import cl.nike.modelo.repository.MarcaRepository;
import cl.nike.modelo.repository.ModeloRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ModeloService {

    private final ModeloRepository modeloRepository;
    private final MarcaRepository marcaRepository;
    private final CategoriaRepository categoriaRepository;
    private final ModeloMapper modeloMapper;

    public List<ModeloResponse> findAll() {
        return modeloMapper.toResponseList(modeloRepository.findAll());
    }

    public ModeloResponse findById(BigDecimal id) {
        return modeloMapper.toResponse(getModeloById(id));
    }

    @Transactional
    public ModeloResponse create(ModeloRequest request) {
        Modelo modelo = modeloMapper.toEntity(request);
        modelo.setMarca(marcaRepository.findById(request.getIdMarca())
            .orElseThrow(() -> new RuntimeException("Marca no encontrada")));
        modelo.setCategoria(categoriaRepository.findById(request.getIdCategoria())
            .orElseThrow(() -> new RuntimeException("Categoría no encontrada")));
        return modeloMapper.toResponse(modeloRepository.save(modelo));
    }

    @Transactional
    public ModeloResponse update(BigDecimal id, ModeloRequest request) {
        Modelo modelo = getModeloById(id);
        modeloMapper.updateEntity(request, modelo);
        modelo.setMarca(marcaRepository.findById(request.getIdMarca())
            .orElseThrow(() -> new RuntimeException("Marca no encontrada")));
        modelo.setCategoria(categoriaRepository.findById(request.getIdCategoria())
            .orElseThrow(() -> new RuntimeException("Categoría no encontrada")));
        return modeloMapper.toResponse(modeloRepository.save(modelo));
    }

    @Transactional
    public void deleteById(BigDecimal id) {
        modeloRepository.delete(getModeloById(id));
    }

    private Modelo getModeloById(BigDecimal id) {
        return modeloRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Modelo no encontrado con ID: " + id));
    }
}