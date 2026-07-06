package cl.nike.compra.service;

import cl.nike.compra.dto.ProveedorRequest;
import cl.nike.compra.dto.ProveedorResponse;
import cl.nike.compra.mapper.ProveedorMapper;
import cl.nike.compra.model.Proveedor;
import cl.nike.compra.repository.ProveedorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProveedorService {

    private final ProveedorRepository proveedorRepository;
    private final ProveedorMapper proveedorMapper;

    public List<ProveedorResponse> findAll() {
        return proveedorMapper.toResponseList(proveedorRepository.findAll());
    }

    public ProveedorResponse findById(BigDecimal id) {
        return proveedorMapper.toResponse(getProveedorById(id));
    }

    @Transactional
    public ProveedorResponse create(ProveedorRequest request) {
        Proveedor proveedor = proveedorMapper.toEntity(request);
        return proveedorMapper.toResponse(proveedorRepository.save(proveedor));
    }

    @Transactional
    public ProveedorResponse update(BigDecimal id, ProveedorRequest request) {
        Proveedor proveedor = getProveedorById(id);
        proveedorMapper.updateEntity(request, proveedor);
        return proveedorMapper.toResponse(proveedorRepository.save(proveedor));
    }

    @Transactional
    public void deleteById(BigDecimal id) {
        Proveedor proveedor = getProveedorById(id);
        if (proveedor.getCompras() != null && !proveedor.getCompras().isEmpty()) {
            throw new RuntimeException("No se puede eliminar el proveedor porque tiene compras asociadas");
        }
        proveedorRepository.delete(proveedor);
    }

    private Proveedor getProveedorById(BigDecimal id) {
        return proveedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con ID: " + id));
    }
}