package cl.nike.compra.service;

import cl.nike.compra.dto.CompraRequest;
import cl.nike.compra.dto.CompraResponse;
import cl.nike.compra.mapper.CompraMapper;
import cl.nike.compra.model.Compra;
import cl.nike.compra.model.Proveedor;
import cl.nike.compra.repository.CompraRepository;
import cl.nike.compra.repository.ProveedorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompraService {

    private final CompraRepository compraRepository;
    private final ProveedorRepository proveedorRepository;
    private final CompraMapper compraMapper;

    public List<CompraResponse> findAll() {
        return compraMapper.toResponseList(compraRepository.findAll());
    }

    public CompraResponse findById(BigDecimal id) {
        return compraMapper.toResponse(getCompraById(id));
    }

    @Transactional
    public CompraResponse create(CompraRequest request) {
        Compra compra = compraMapper.toEntity(request);
        compra.setProveedor(getProveedorById(request.getIdProveedor()));
        return compraMapper.toResponse(compraRepository.save(compra));
    }

    @Transactional
    public CompraResponse update(BigDecimal id, CompraRequest request) {
        Compra compra = getCompraById(id);
        compraMapper.updateEntity(request, compra);
        compra.setProveedor(getProveedorById(request.getIdProveedor()));
        return compraMapper.toResponse(compraRepository.save(compra));
    }

    @Transactional
    public void deleteById(BigDecimal id) {
        Compra compra = getCompraById(id);
        if (compra.getDetalles() != null && !compra.getDetalles().isEmpty()) {
            throw new RuntimeException("No se puede eliminar la compra porque tiene detalles asociados");
        }
        compraRepository.delete(compra);
    }

    private Compra getCompraById(BigDecimal id) {
        return compraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra no encontrada con ID: " + id));
    }

    private Proveedor getProveedorById(BigDecimal id) {
        return proveedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con ID: " + id));
    }
}