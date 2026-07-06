package cl.nike.cliente.service;

import cl.nike.cliente.dto.CiudadRequest;
import cl.nike.cliente.dto.CiudadResponse;
import cl.nike.cliente.mapper.CiudadMapper;
import cl.nike.cliente.model.Ciudad;
import cl.nike.cliente.repository.CiudadRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CiudadService {

    private final CiudadRepository ciudadRepository;
    private final CiudadMapper ciudadMapper;

    public List<CiudadResponse> findAll() {
        return ciudadMapper.toResponseList(ciudadRepository.findAll());
    }

    public CiudadResponse findById(BigDecimal id) {
        return ciudadMapper.toResponse(getCiudadById(id));
    }

    @Transactional
    public CiudadResponse create(CiudadRequest request) {
        Ciudad ciudad = ciudadMapper.toEntity(request);
        return ciudadMapper.toResponse(ciudadRepository.save(ciudad));
    }

    @Transactional
    public CiudadResponse update(BigDecimal id, CiudadRequest request) {
        Ciudad ciudad = getCiudadById(id);
        ciudadMapper.updateEntity(request, ciudad);
        return ciudadMapper.toResponse(ciudadRepository.save(ciudad));
    }

    @Transactional
    public void deleteById(BigDecimal id) {
        Ciudad ciudad = getCiudadById(id);
        // Regla: No eliminar si tiene clientes asociados
        if (ciudad.getClientes() != null && !ciudad.getClientes().isEmpty()) {
            throw new RuntimeException("No se puede eliminar la ciudad porque tiene clientes asociados");
        }
        ciudadRepository.delete(ciudad);
    }

    private Ciudad getCiudadById(BigDecimal id) {
        return ciudadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada con ID: " + id));
    }
}