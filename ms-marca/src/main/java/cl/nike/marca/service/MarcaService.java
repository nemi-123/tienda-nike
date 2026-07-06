package cl.nike.marca.service;

import cl.nike.marca.dto.MarcaRequest;
import cl.nike.marca.dto.MarcaResponse;
import cl.nike.marca.mapper.MarcaMapper;
import cl.nike.marca.model.Marca;
import cl.nike.marca.repository.MarcaRepository;
import cl.nike.marca.repository.PaisRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MarcaService {

    private final MarcaRepository marcaRepository;
    private final PaisRepository paisRepository;
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
        marca.setPais(paisRepository.findById(request.getIdPais())
                .orElseThrow(() -> new RuntimeException("País no encontrado con ID: " + request.getIdPais())));
        return marcaMapper.toResponse(marcaRepository.save(marca));
    }

    @Transactional
    public MarcaResponse update(BigDecimal id, MarcaRequest request) {
        Marca marca = getMarcaById(id);
        marcaMapper.updateEntity(request, marca);
        marca.setPais(paisRepository.findById(request.getIdPais())
                .orElseThrow(() -> new RuntimeException("País no encontrado con ID: " + request.getIdPais())));
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