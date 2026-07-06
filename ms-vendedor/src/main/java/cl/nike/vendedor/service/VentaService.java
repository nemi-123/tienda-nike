package cl.nike.vendedor.service;

import cl.nike.vendedor.dto.VentaRequest;
import cl.nike.vendedor.dto.VentaResponse;
import cl.nike.vendedor.mapper.VentaMapper;
import cl.nike.vendedor.model.Venta;
import cl.nike.vendedor.repository.VentaRepository;
import cl.nike.vendedor.repository.VendedorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VentaService {

    private final VentaRepository ventaRepository;
    private final VendedorRepository vendedorRepository;
    private final VentaMapper ventaMapper;

    public List<VentaResponse> findAll() {
        return ventaMapper.toResponseList(ventaRepository.findAll());
    }

    public VentaResponse findById(BigDecimal id) {
        return ventaMapper.toResponse(getVentaById(id));
    }

    @Transactional
    public VentaResponse create(VentaRequest request) {
        Venta venta = ventaMapper.toEntity(request);
        venta.setVendedor(vendedorRepository.findById(request.getIdVendedor())
                .orElseThrow(() -> new RuntimeException("Vendedor no encontrado con ID: " + request.getIdVendedor())));
        return ventaMapper.toResponse(ventaRepository.save(venta));
    }

    @Transactional
    public VentaResponse update(BigDecimal id, VentaRequest request) {
        Venta venta = getVentaById(id);
        ventaMapper.updateEntity(request, venta);
        venta.setVendedor(vendedorRepository.findById(request.getIdVendedor())
                .orElseThrow(() -> new RuntimeException("Vendedor no encontrado con ID: " + request.getIdVendedor())));
        return ventaMapper.toResponse(ventaRepository.save(venta));
    }

    @Transactional
    public void deleteById(BigDecimal id) {
        ventaRepository.delete(getVentaById(id));
    }

    private Venta getVentaById(BigDecimal id) {
        return ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con ID: " + id));
    }
}