package cl.nike.venta.service;

import cl.nike.venta.dto.DetallevtaRequest;
import cl.nike.venta.dto.DetallevtaResponse;
import cl.nike.venta.mapper.DetallevtaMapper;
import cl.nike.venta.model.Detallevta;
import cl.nike.venta.model.Venta;
import cl.nike.venta.repository.DetallevtaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DetallevtaService {

    private final DetallevtaRepository detallevtaRepository;
    private final DetallevtaMapper detallevtaMapper;

    public List<DetallevtaResponse> findAll() {
        List<Detallevta> detalles = detallevtaRepository.findAll();
        List<DetallevtaResponse> respuestas = new ArrayList<>();
        
        for (Detallevta detalle : detalles) {
            respuestas.add(detallevtaMapper.toResponse(detalle));
        }
        
        return respuestas;
    }

    public DetallevtaResponse findById(BigDecimal id) {
        return detallevtaMapper.toResponse(getDetalleById(id));
    }

    @Transactional
    public DetallevtaResponse create(DetallevtaRequest request) {
        validateIdUnico(request.getIddetalle());
        
        Detallevta detalle = Objects.requireNonNull(detallevtaMapper.toEntity(request),"El detalle no puede ser nulo");
        detallevtaRepository.save(detalle);
        
        return detallevtaMapper.toResponse(detalle);
    }

    @Transactional
    public DetallevtaResponse update(BigDecimal id, DetallevtaRequest request) {
        Detallevta detalleExistente = getDetalleById(id);
        
        // Actualizamos los campos primitivos
        detalleExistente.setCantidad(request.getCantidad());
        detalleExistente.setSubtotal(request.getSubtotal());
        
        // Actualizamos la relación con la Venta usando el Builder
        Venta nuevaVenta = Venta.builder()
                .idventa(request.getIdventa())
                .build();
        detalleExistente.setVenta(nuevaVenta);
        
        detallevtaRepository.save(detalleExistente);
        return detallevtaMapper.toResponse(detalleExistente);
    }

    @Transactional
    public void deleteById(BigDecimal id) {
        // Guardián para verificar la existencia antes de borrar
        getDetalleById(id);
        
        // Uso directo de la ID para evitar alertas amarillas en el IDE
        BigDecimal id2 = Objects.requireNonNull(id, "El id no puede ser nulo");
        detallevtaRepository.deleteById(id2);
    }

    // --- MÉTODOS PRIVADOS AUXILIARES (Estilo del Profesor) ---

    private Detallevta getDetalleById(BigDecimal id) {
        BigDecimal id2 = Objects.requireNonNull(id, "El id no puede ser nulo");
        return detallevtaRepository.findById(id2)
                .orElseThrow(() -> new RuntimeException("Detalle de venta no encontrado con el ID: " + id));
    }

    private void validateIdUnico(BigDecimal id) {
        BigDecimal id2 = Objects.requireNonNull(id, "El id no puede ser nulo");
        if (detallevtaRepository.existsById(id2)) {
            throw new RuntimeException("El ID de detalle " + id + " ya existe en la base de datos.");
        }
    }
}