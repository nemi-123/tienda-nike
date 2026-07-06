package cl.nike.vendedor.service;

import cl.nike.vendedor.dto.SucursalRequest;
import cl.nike.vendedor.dto.SucursalResponse;
import cl.nike.vendedor.mapper.SucursalMapper;
import cl.nike.vendedor.model.Sucursal;
import cl.nike.vendedor.repository.SucursalRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SucursalService {

    private final SucursalRepository sucursalRepository;
    private final SucursalMapper sucursalMapper;

    public List<SucursalResponse> findAll() {
        return sucursalMapper.toResponseList(sucursalRepository.findAll());
    }

    public SucursalResponse findById(BigDecimal id) {
        return sucursalMapper.toResponse(getSucursalById(id));
    }

    @Transactional
    public SucursalResponse create(SucursalRequest request) {
        Sucursal sucursal = sucursalMapper.toEntity(request);
        return sucursalMapper.toResponse(sucursalRepository.save(sucursal));
    }

    @Transactional
    public SucursalResponse update(BigDecimal id, SucursalRequest request) {
        Sucursal sucursal = getSucursalById(id);
        sucursalMapper.updateEntity(request, sucursal);
        return sucursalMapper.toResponse(sucursalRepository.save(sucursal));
    }

    @Transactional
    public void deleteById(BigDecimal id) {
        Sucursal sucursal = getSucursalById(id);
        // Regla: No eliminar si tiene vendedores asociados
        if (sucursal.getVendedores() != null && !sucursal.getVendedores().isEmpty()) {
            throw new RuntimeException("No se puede eliminar la sucursal porque tiene vendedores asociados");
        }
        sucursalRepository.delete(sucursal);
    }

    private Sucursal getSucursalById(BigDecimal id) {
        return sucursalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada con ID: " + id));
    }
}