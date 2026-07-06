package cl.nike.detallevta.service;

import cl.nike.detallevta.dto.VentaRequest;
import cl.nike.detallevta.dto.VentaResponse;
import cl.nike.detallevta.mapper.VentaMapper;
import cl.nike.detallevta.model.Venta;
import cl.nike.detallevta.repository.VentaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VentaService {

    private final VentaRepository ventaRepository;
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
        return ventaMapper.toResponse(ventaRepository.save(venta));
    }

    @Transactional
    public VentaResponse update(BigDecimal id, VentaRequest request) {
        Venta venta = getVentaById(id);
        ventaMapper.updateEntity(request, venta);
        return ventaMapper.toResponse(ventaRepository.save(venta));
    }

    @Transactional
    public void deleteById(BigDecimal id) {
        Venta venta = getVentaById(id);
        if (venta.getDetalles() != null && !venta.getDetalles().isEmpty()) {
            throw new RuntimeException("No se puede eliminar la venta porque tiene detalles asociados");
        }
        ventaRepository.delete(venta);
    }

    private Venta getVentaById(BigDecimal id) {
        return ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con ID: " + id));
    }
}