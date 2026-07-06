package cl.nike.compra.service;

import cl.nike.compra.dto.DetallecmpRequest;
import cl.nike.compra.dto.DetallecmpResponse;
import cl.nike.compra.mapper.DetallecmpMapper;
import cl.nike.compra.model.Compra;
import cl.nike.compra.model.Detallecmp;
import cl.nike.compra.repository.CompraRepository;
import cl.nike.compra.repository.DetallecmpRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DetallecmpService {

    private final DetallecmpRepository detalleRepository;
    private final CompraRepository compraRepository;
    private final DetallecmpMapper detalleMapper;

    public List<DetallecmpResponse> findAll() {
        return detalleMapper.toResponseList(detalleRepository.findAll());
    }

    public DetallecmpResponse findById(BigDecimal id) {
        return detalleMapper.toResponse(getDetalleById(id));
    }

    @Transactional
    public DetallecmpResponse create(DetallecmpRequest request) {
        Detallecmp detalle = detalleMapper.toEntity(request);
        detalle.setCompra(getCompraById(request.getIdCompra()));
        return detalleMapper.toResponse(detalleRepository.save(detalle));
    }

    @Transactional
    public DetallecmpResponse update(BigDecimal id, DetallecmpRequest request) {
        Detallecmp detalle = getDetalleById(id);
        detalleMapper.updateEntity(request, detalle);
        detalle.setCompra(getCompraById(request.getIdCompra()));
        return detalleMapper.toResponse(detalleRepository.save(detalle));
    }

    @Transactional
    public void deleteById(BigDecimal id) {
        detalleRepository.delete(getDetalleById(id));
    }

    private Detallecmp getDetalleById(BigDecimal id) {
        return detalleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle de compra no encontrado con ID: " + id));
    }

    private Compra getCompraById(BigDecimal id) {
        return compraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra no encontrada con ID: " + id));
    }
}