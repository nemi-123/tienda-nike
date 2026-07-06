package cl.nike.detallevta.service;

import cl.nike.detallevta.dto.DetallevtaRequest;
import cl.nike.detallevta.dto.DetallevtaResponse;
import cl.nike.detallevta.mapper.DetallevtaMapper;
import cl.nike.detallevta.model.Detallevta;
import cl.nike.detallevta.repository.DetallevtaRepository;
import cl.nike.detallevta.repository.ProductoRepository;
import cl.nike.detallevta.repository.VentaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DetallevtaService {

    private final DetallevtaRepository detalleRepository;
    private final VentaRepository ventaRepository;
    private final ProductoRepository productoRepository;
    private final DetallevtaMapper detalleMapper;

    public List<DetallevtaResponse> findAll() {
        return detalleMapper.toResponseList(detalleRepository.findAll());
    }

    public DetallevtaResponse findById(BigDecimal id) {
        return detalleMapper.toResponse(getDetalleById(id));
    }

    @Transactional
    public DetallevtaResponse create(DetallevtaRequest request) {
        Detallevta detalle = detalleMapper.toEntity(request);
        detalle.setVenta(ventaRepository.findById(request.getIdVenta())
            .orElseThrow(() -> new RuntimeException("Venta no encontrada")));
        detalle.setProducto(productoRepository.findById(request.getIdProducto())
            .orElseThrow(() -> new RuntimeException("Producto no encontrado")));
        return detalleMapper.toResponse(detalleRepository.save(detalle));
    }

    @Transactional
    public DetallevtaResponse update(BigDecimal id, DetallevtaRequest request) {
        Detallevta detalle = getDetalleById(id);
        detalleMapper.updateEntity(request, detalle);
        detalle.setVenta(ventaRepository.findById(request.getIdVenta())
            .orElseThrow(() -> new RuntimeException("Venta no encontrada")));
        detalle.setProducto(productoRepository.findById(request.getIdProducto())
            .orElseThrow(() -> new RuntimeException("Producto no encontrado")));
        return detalleMapper.toResponse(detalleRepository.save(detalle));
    }

    @Transactional
    public void deleteById(BigDecimal id) {
        detalleRepository.delete(getDetalleById(id));
    }

    private Detallevta getDetalleById(BigDecimal id) {
        return detalleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle de venta no encontrado: " + id));
    }
}