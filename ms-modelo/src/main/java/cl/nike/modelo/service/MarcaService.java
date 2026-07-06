package cl.nike.modelo.service;

import cl.nike.modelo.dto.MarcaRequest;
import cl.nike.modelo.dto.MarcaResponse;
import cl.nike.modelo.mapper.MarcaMapper;
import cl.nike.modelo.model.Marca;
import cl.nike.modelo.repository.MarcaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MarcaService {

    private final MarcaRepository marcaRepository;
    private final MarcaMapper marcaMapper;

    public List<MarcaResponse> findAll() {
        return marcaMapper.toResponseList(marcaRepository.findAll());
    }

    public MarcaResponse findById(BigDecimal id) {
        return marcaMapper.toResponse(getMarcaById(id));
    }

    @Transactional
    public MarcaResponse create(MarcaRequest request) {
        Marca marca = marcaMapper.toEntity(request);
        return marcaMapper.toResponse(marcaRepository.save(marca));
    }

    @Transactional
    public MarcaResponse update(BigDecimal id, MarcaRequest request) {
        Marca marca = getMarcaById(id);
        marcaMapper.updateEntity(request, marca);
        return marcaMapper.toResponse(marcaRepository.save(marca));
    }

    @Transactional
    public void deleteById(BigDecimal id) {
        Marca marca = getMarcaById(id);
        if (marca.getModelos() != null && !marca.getModelos().isEmpty()) {
            throw new RuntimeException("No se puede eliminar la marca porque tiene modelos asociados");
        }
        marcaRepository.delete(marca);
    }

    private Marca getMarcaById(BigDecimal id) {
        return marcaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marca no encontrada con ID: " + id));
    }
}