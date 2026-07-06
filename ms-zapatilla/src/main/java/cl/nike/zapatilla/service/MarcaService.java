package cl.nike.zapatilla.service;

import cl.nike.zapatilla.dto.MarcaRequest;
import cl.nike.zapatilla.dto.MarcaResponse;
import cl.nike.zapatilla.mapper.MarcaMapper;
import cl.nike.zapatilla.model.Marca;
import cl.nike.zapatilla.repository.MarcaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor 
public class MarcaService {

    private final MarcaRepository marcaRepository;
    private final MarcaMapper marcaMapper;

    @Transactional(readOnly = true)
    public List<MarcaResponse> findAll() {
        List<Marca> marcas = marcaRepository.findAll();
        return marcaMapper.toResponseList(marcas);
    }

    @Transactional(readOnly = true)
    public MarcaResponse findById(BigDecimal id) {
        Marca marca = marcaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marca no encontrada con el ID: " + id));
        return marcaMapper.toResponse(marca);
    }

    @Transactional
    public MarcaResponse create(MarcaRequest request) {
        // 1. Validar si ya existe una marca con ese nombre usando Streams sobre findAll()
        boolean nombreExiste = marcaRepository.findAll().stream()
                .anyMatch(m -> m.getNombre().equalsIgnoreCase(request.getNombre()));
        
        if (nombreExiste) {
            throw new RuntimeException("Ya existe una marca registrada con el nombre: " + request.getNombre());
        }

        // 2. Validar si el ID manual ya existe en la BD (Método nativo de JpaRepository)
        if (marcaRepository.existsById(request.getIdmarca())) {
            throw new RuntimeException("Ya existe una marca registrada con el ID: " + request.getIdmarca());
        }

        // 3. Mapear DTO a Entidad
        Marca nuevaMarca = marcaMapper.toEntity(request);
        nuevaMarca.setIdmarca(request.getIdmarca());

        // 4. Guardar y retornar la respuesta mapeada
        Marca marcaGuardada = marcaRepository.save(nuevaMarca);
        return marcaMapper.toResponse(marcaGuardada);
    }

    @Transactional
    public MarcaResponse update(BigDecimal id, MarcaRequest request) {
        // 1. Buscar la entidad existente
        Marca marcaExistente = marcaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se puede actualizar. Marca no encontrada con el ID: " + id));

        // 2. Validar que si cambia el nombre, este no le pertenezca a otra marca utilizando Streams
        if (!marcaExistente.getNombre().equalsIgnoreCase(request.getNombre())) {
            boolean nombreExiste = marcaRepository.findAll().stream()
                    .anyMatch(m -> m.getNombre().equalsIgnoreCase(request.getNombre()));
            if (nombreExiste) {
                throw new RuntimeException("Ya existe otra marca con el nombre: " + request.getNombre());
            }
        }

        // 3. MapStruct actualiza los campos permitidos
        marcaMapper.updateEntity(request, marcaExistente);

        // 4. Guardar cambios
        Marca marcaActualizada = marcaRepository.save(marcaExistente);
        return marcaMapper.toResponse(marcaActualizada);
    }

    @Transactional
    public void deleteById(BigDecimal id) {
        if (!marcaRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. Marca no encontrada con el ID: " + id);
        }
        marcaRepository.deleteById(id);
    }
}