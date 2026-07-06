package cl.nike.zapatilla.service;

import cl.nike.zapatilla.dto.ZapatillaRequest;
import cl.nike.zapatilla.dto.ZapatillaResponse;
import cl.nike.zapatilla.mapper.ZapatillaMapper;
import cl.nike.zapatilla.model.Modelo;
import cl.nike.zapatilla.model.Zapatilla;
import cl.nike.zapatilla.repository.ZapatillaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Servicio encargado de aplicar las reglas de negocio de zapatillas:
 * - Gestiona operaciones CRUD, validaciones de negocio y reglas de integridad.
 * - Basado exactamente en el diseño y buenas prácticas del profesor.
 */
@Service
@RequiredArgsConstructor // Inyección automática de dependencias por Lombok
public class ZapatillaService {

    private final ZapatillaRepository zapatillaRepository;
    private final ZapatillaMapper zapatillaMapper;

    public List<ZapatillaResponse> findAll() {
        List<Zapatilla> zapatillas = zapatillaRepository.findAll();
        List<ZapatillaResponse> respuestas = new ArrayList<>();
        
        for (Zapatilla zapatilla : zapatillas) {
            respuestas.add(zapatillaMapper.toResponse(zapatilla));
        }
        
        return respuestas;
    }

    public ZapatillaResponse findById(BigDecimal id) {
        return zapatillaMapper.toResponse(getZapatillaById(id));
    }

    @Transactional
    public ZapatillaResponse create(ZapatillaRequest request) {
        validateIdUnico(request.getIdzapatilla());
        
        Zapatilla zapatilla = Objects.requireNonNull(zapatillaMapper.toEntity(request), "Zapatilla no pude ser nulo");
        zapatillaRepository.save(zapatilla);
        
        return zapatillaMapper.toResponse(zapatilla);
    }

    @Transactional
    public ZapatillaResponse update(BigDecimal id, ZapatillaRequest request) {
        Zapatilla zapatillaExistente = getZapatillaById(id);
        
        // Actualizamos los campos directos
        zapatillaExistente.setNombre(request.getNombre());
        zapatillaExistente.setPrecio(request.getPrecio());
        zapatillaExistente.setStock(request.getStock());
        
        // Actualizamos el Modelo asociado usando el patrón Builder
        Modelo nuevoModelo = Modelo.builder()
                .idmodelo(request.getIdmodelo())
                .build();
        zapatillaExistente.setModelo(nuevoModelo);
        
        zapatillaRepository.save(zapatillaExistente);
        return zapatillaMapper.toResponse(zapatillaExistente);
    }

    @Transactional
    public void deleteById(BigDecimal id) {
        Zapatilla zapatilla = Objects.requireNonNull(getZapatillaById(id),"El id no puede ser nulo");
        zapatillaRepository.delete(zapatilla);
    }

    // =========================================================================
    // AGREGA ESTO: Los métodos de aquí abajo son los que resuelven los errores de la izquierda
    // =========================================================================

    private Zapatilla getZapatillaById(BigDecimal id) {
        BigDecimal id2 = Objects.requireNonNull(id, "El id no puede ser nulo");
        return zapatillaRepository.findById(id2)
                .orElseThrow(() -> new RuntimeException("Zapatilla no encontrada con el ID: " + id));
    }

    private void validateIdUnico(BigDecimal id) {
        BigDecimal id2 = Objects.requireNonNull(id,"El id no puede ser nulo");
        if (zapatillaRepository.existsById(id2)) {
            throw new RuntimeException("El ID de zapatilla " + id + " ya existe en la base de datos.");
        }
    }
}