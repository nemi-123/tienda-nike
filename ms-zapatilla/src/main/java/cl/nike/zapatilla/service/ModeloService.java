package cl.nike.zapatilla.service;

import cl.nike.zapatilla.dto.ModeloRequest;
import cl.nike.zapatilla.dto.ModeloResponse;
import cl.nike.zapatilla.mapper.ModeloMapper;
import cl.nike.zapatilla.model.Marca;
import cl.nike.zapatilla.model.Modelo;

import cl.nike.zapatilla.repository.ModeloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Servicio encargado de aplicar las reglas de negocio de modelos:
 * - Gestiona operaciones CRUD, validaciones de negocio y reglas de integridad.
 * - Basado exactamente en el diseño y buenas prácticas del profesor.
 */
@Service
@RequiredArgsConstructor // Inyección automática de dependencias por Lombok (sin constructores manuales)
public class ModeloService {

    private final ModeloRepository modeloRepository;
    private final ModeloMapper modeloMapper;

    public List<ModeloResponse> findAll() {
        List<Modelo> modelos = modeloRepository.findAll();
        List<ModeloResponse> respuestas = new ArrayList<>();
        
        for (Modelo modelo : modelos) {
            respuestas.add(modeloMapper.toResponse(modelo));
        }
        
        return respuestas;
    }

    public ModeloResponse findById(BigDecimal id) {
        return modeloMapper.toResponse(getModeloById(id));
    }

    @Transactional
    public ModeloResponse create(ModeloRequest request) {
        validateIdUnico(request.getIdmodelo());
        
        Modelo modelo = Objects.requireNonNull(modeloMapper.toEntity(request),"modelo no puede ser nulo");
        modeloRepository.save(modelo);
        
        return modeloMapper.toResponse(modelo);
    }

    @Transactional
    public ModeloResponse update(BigDecimal id, ModeloRequest request) {
        Modelo modeloExistente = getModeloById(id);
        
        // Actualizamos los campos directos
        modeloExistente.setNombre(request.getNombre());
        
        // Actualizamos la relación con la Marca usando el patrón Builder
        Marca nuevaMarca = Marca.builder()
                .idmarca(request.getIdmarca())
                .build();
        modeloExistente.setMarca(nuevaMarca);
        
        modeloRepository.save(modeloExistente);
        return modeloMapper.toResponse(modeloExistente);
    }

    @Transactional
    public void deleteById(BigDecimal id) {
        // Primero validamos si existe (así salta el error si no se encuentra)
        getModeloById(id);
        
        // Borramos usando el ID directo para que tu IDE no te muestre la advertencia en amarillo
        BigDecimal id2 = Objects.requireNonNull(id, "Id no puede ser nulo");
        modeloRepository.deleteById(id2);
    }

    // --- MÉTODOS PRIVADOS AUXILIARES (Estilo del Profesor) ---

    private Modelo getModeloById(BigDecimal id) {

        BigDecimal id2 = Objects.requireNonNull(id, "id no puede ser nulo"); 
        return modeloRepository.findById(id2)
                .orElseThrow(() -> new RuntimeException("Modelo no encontrado con el ID: " + id));
    }

    private void validateIdUnico(BigDecimal id) {

        BigDecimal id2 = Objects.requireNonNull(id, "id no puede ser nulo");
        if (modeloRepository.existsById(id2)) {
            throw new RuntimeException("El ID de modelo " + id + " ya existe en la base de datos.");
        }
    }
}