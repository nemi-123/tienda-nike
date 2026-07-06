package cl.nike.proveedor.service;

import cl.nike.proveedor.dto.ProveedorRequest;
import cl.nike.proveedor.dto.ProveedorResponse;
import cl.nike.proveedor.mapper.ProveedorMapper;
import cl.nike.proveedor.model.Ciudad;
import cl.nike.proveedor.model.Proveedor;
import cl.nike.proveedor.repository.CiudadRepository;
import cl.nike.proveedor.repository.ProveedorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProveedorService {

    private final ProveedorRepository proveedorRepository;
    private final CiudadRepository ciudadRepository;
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
        proveedor.setCiudad(getCiudadById(request.getIdCiudad()));
        return proveedorMapper.toResponse(proveedorRepository.save(proveedor));
    }

    @Transactional
    public ProveedorResponse update(BigDecimal id, ProveedorRequest request) {
        Proveedor proveedor = getProveedorById(id);
        proveedorMapper.updateEntity(request, proveedor);
        proveedor.setCiudad(getCiudadById(request.getIdCiudad()));
        return proveedorMapper.toResponse(proveedorRepository.save(proveedor));
    }

    @Transactional
    public void deleteById(BigDecimal id) {
        Proveedor proveedor = getProveedorById(id);
        // Validar integridad referencial (no borrar si tiene compras)
        if (proveedor.getCompras() != null && !proveedor.getCompras().isEmpty()) {
            throw new RuntimeException("No se puede eliminar el proveedor porque tiene compras asociadas");
        }
        proveedorRepository.delete(proveedor);
    }

    private Proveedor getProveedorById(BigDecimal id) {
        return proveedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con ID: " + id));
    }

    private Ciudad getCiudadById(BigDecimal id) {
        return ciudadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada con ID: " + id));
    }
}