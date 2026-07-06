package cl.nike.proveedor.service;

import cl.nike.proveedor.dto.CompraRequest;
import cl.nike.proveedor.dto.CompraResponse;
import cl.nike.proveedor.mapper.CompraMapper;
import cl.nike.proveedor.model.Compra;
import cl.nike.proveedor.model.Proveedor;
import cl.nike.proveedor.repository.CompraRepository;
import cl.nike.proveedor.repository.ProveedorRepository;
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
        compraRepository.delete(getCompraById(id));
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