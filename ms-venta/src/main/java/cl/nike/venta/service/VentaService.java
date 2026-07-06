package cl.nike.venta.service;

import cl.nike.venta.dto.VentaRequest;
import cl.nike.venta.dto.VentaResponse;
import cl.nike.venta.mapper.VentaMapper;
import cl.nike.venta.model.Cliente;
import cl.nike.venta.model.Venta;
import cl.nike.venta.repository.VentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor // Inyección automática por constructor con Lombok
public class VentaService {

    private final VentaRepository ventaRepository;
    private final VentaMapper ventaMapper;

    public List<VentaResponse> findAll() {
        List<Venta> ventas = ventaRepository.findAll();
        List<VentaResponse> respuestas = new ArrayList<>();
        
        for (Venta venta : ventas) {
            respuestas.add(ventaMapper.toResponse(venta));
        }
        
        return respuestas;
    }

    public VentaResponse findById(BigDecimal id) {
        return ventaMapper.toResponse(getVentaById(id));
    }

    @Transactional
    public VentaResponse create(VentaRequest request) {
        validateIdUnico(request.getIdventa());
        
        Venta venta = Objects.requireNonNull(ventaMapper.toEntity(request),"Venta no puede ser nulo");
        ventaRepository.save(venta);
        
        return ventaMapper.toResponse(venta);
    }

    @Transactional
    public VentaResponse update(BigDecimal id, VentaRequest request) {
        Venta ventaExistente = getVentaById(id);
        
        // Actualizamos los campos directos
        ventaExistente.setFecha(request.getFecha());
        ventaExistente.setTotal(request.getTotal());
        
        // Actualizamos la relación con el Cliente usando el Builder
        Cliente nuevoCliente = Cliente.builder()
                .idcliente(request.getIdcliente())
                .build();
        ventaExistente.setCliente(nuevoCliente);
        
        ventaRepository.save(ventaExistente);
        return ventaMapper.toResponse(ventaExistente);
    }

    @Transactional
    public void deleteById(BigDecimal id) {
        // Guardián para validar si la venta existe antes de borrar
        getVentaById(id);
        
        // Uso directo de la ID para evitar alertas amarillas en el IDE
        BigDecimal id2 = Objects.requireNonNull(id, "El id no puede ser nulo");
        ventaRepository.deleteById(id2);
    }

    // --- MÉTODOS PRIVADOS AUXILIARES (Estilo del Profesor) ---

    private Venta getVentaById(BigDecimal id) {
        BigDecimal id2 = Objects.requireNonNull(id, "El id no puede ser nulo");
        return ventaRepository.findById(id2)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con el ID: " + id));
    }

    private void validateIdUnico(BigDecimal id) {
        BigDecimal id2 = Objects.requireNonNull(id, "El id no puede ser nulo");
        if (ventaRepository.existsById(id2)) {
            throw new RuntimeException("El ID de venta " + id + " ya existe en la base de datos.");
        }
    }
}