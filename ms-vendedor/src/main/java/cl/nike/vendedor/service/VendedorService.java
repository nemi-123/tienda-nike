package cl.nike.vendedor.service;

import cl.nike.vendedor.dto.VendedorRequest;
import cl.nike.vendedor.dto.VendedorResponse;
import cl.nike.vendedor.mapper.VendedorMapper;
import cl.nike.vendedor.model.Sucursal;
import cl.nike.vendedor.model.Vendedor;
import cl.nike.vendedor.repository.SucursalRepository;
import cl.nike.vendedor.repository.VendedorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VendedorService {

    private final VendedorRepository vendedorRepository;
    private final SucursalRepository sucursalRepository;
    private final VendedorMapper vendedorMapper;

    public List<VendedorResponse> findAll() {
        return vendedorMapper.toResponseList(vendedorRepository.findAll());
    }

    public VendedorResponse findById(BigDecimal id) {
        return vendedorMapper.toResponse(getVendedorById(id));
    }

    @Transactional
    public VendedorResponse create(VendedorRequest request) {
        Vendedor vendedor = vendedorMapper.toEntity(request);
        vendedor.setSucursal(getSucursalById(request.getIdSucursal()));
        return vendedorMapper.toResponse(vendedorRepository.save(vendedor));
    }

    @Transactional
    public VendedorResponse update(BigDecimal id, VendedorRequest request) {
        Vendedor vendedor = getVendedorById(id);
        vendedorMapper.updateEntity(request, vendedor);
        vendedor.setSucursal(getSucursalById(request.getIdSucursal()));
        return vendedorMapper.toResponse(vendedorRepository.save(vendedor));
    }

    @Transactional
    public void deleteById(BigDecimal id) {
        Vendedor vendedor = getVendedorById(id);
        if (vendedor.getVentas() != null && !vendedor.getVentas().isEmpty()) {
            throw new RuntimeException("No se puede eliminar el vendedor porque tiene ventas asociadas");
        }
        vendedorRepository.delete(vendedor);
    }

    private Vendedor getVendedorById(BigDecimal id) {
        return vendedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendedor no encontrado con ID: " + id));
    }

    private Sucursal getSucursalById(BigDecimal id) {
        return sucursalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada con ID: " + id));
    }
}