package cl.nike.marca.service;

import cl.nike.marca.dto.PaisRequest;
import cl.nike.marca.dto.PaisResponse;
import cl.nike.marca.mapper.PaisMapper;
import cl.nike.marca.model.Pais;
import cl.nike.marca.repository.PaisRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaisService {

    private final PaisRepository paisRepository;
    private final PaisMapper paisMapper;

    public List<PaisResponse> findAll() {
        return paisMapper.toResponseList(paisRepository.findAll());
    }

    public PaisResponse findById(BigDecimal id) {
        return paisMapper.toResponse(getPaisById(id));
    }

    @Transactional
    public PaisResponse create(PaisRequest request) {
        Pais pais = paisMapper.toEntity(request);
        return paisMapper.toResponse(paisRepository.save(pais));
    }

    @Transactional
    public PaisResponse update(BigDecimal id, PaisRequest request) {
        Pais pais = getPaisById(id);
        paisMapper.updateEntity(request, pais);
        return paisMapper.toResponse(paisRepository.save(pais));
    }

    @Transactional
    public void deleteById(BigDecimal id) {
        Pais pais = getPaisById(id);
        if (pais.getMarcas() != null && !pais.getMarcas().isEmpty()) {
            throw new RuntimeException("No se puede eliminar el país porque tiene marcas asociadas");
        }
        paisRepository.delete(pais);
    }

    private Pais getPaisById(BigDecimal id) {
        return paisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("País no encontrado con ID: " + id));
    }
}