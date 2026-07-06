package cl.nike.marca.service;

import cl.nike.marca.dto.ModeloRequest;
import cl.nike.marca.dto.ModeloResponse;
import cl.nike.marca.mapper.ModeloMapper;
import cl.nike.marca.model.Modelo;
import cl.nike.marca.repository.MarcaRepository;
import cl.nike.marca.repository.ModeloRepository;
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
                .orElseThrow(() -> new RuntimeException("Marca no encontrada con ID: " + request.getIdMarca())));
        return modeloMapper.toResponse(modeloRepository.save(modelo));
    }

    @Transactional
    public ModeloResponse update(BigDecimal id, ModeloRequest request) {
        Modelo modelo = getModeloById(id);
        modeloMapper.updateEntity(request, modelo);
        modelo.setMarca(marcaRepository.findById(request.getIdMarca())
                .orElseThrow(() -> new RuntimeException("Marca no encontrada con ID: " + request.getIdMarca())));
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