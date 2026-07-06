package cl.nike.venta.service;

import cl.nike.venta.dto.ClienteRequest;
import cl.nike.venta.dto.ClienteResponse;
import cl.nike.venta.mapper.ClienteMapper;
import cl.nike.venta.model.Cliente;
import cl.nike.venta.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Servicio encargado de aplicar las reglas de negocio de clientes:
 * - Gestiona operaciones CRUD, validaciones de negocio y reglas de integridad.
 * - Basado exactamente en el diseño y buenas prácticas del profesor.
 */
@Service
@RequiredArgsConstructor // Inyección automática de dependencias por Lombok
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public List<ClienteResponse> findAll() {
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteResponse> respuestas = new ArrayList<>();
        
        for (Cliente cliente : clientes) {
            respuestas.add(clienteMapper.toResponse(cliente));
        }
        
        return respuestas;
    }

    public ClienteResponse findById(BigDecimal id) {
        return clienteMapper.toResponse(getClienteById(id));
    }

    @Transactional
    public ClienteResponse create(ClienteRequest request) {
        // Aquí ya NO marcará error porque agregamos el método abajo
        validateIdUnico(request.getIdcliente());
        
        Cliente cliente = Objects.requireNonNull(clienteMapper.toEntity(request),"El cliente no puede ser nulo");
        clienteRepository.save(cliente);
        
        return clienteMapper.toResponse(cliente);
    }

    @Transactional
    public ClienteResponse update(BigDecimal id, ClienteRequest request) {
        Cliente clienteExistente = getClienteById(id);
        
        // Actualizamos los campos directos
        clienteExistente.setNombre(request.getNombre());
        
        clienteRepository.save(clienteExistente);
        return clienteMapper.toResponse(clienteExistente);
    }

    @Transactional
    public void deleteById(BigDecimal id) {
        // Valida si existe primero para arrojar el error correspondiente
        getClienteById(id);
        
        // Borramos usando el ID directo para evitar advertencias amarillas del IDE
        BigDecimal id2 = Objects.requireNonNull(id, "El id no puede ser nulo");
        clienteRepository.deleteById(id2);
    }

    // --- MÉTODOS PRIVADOS AUXILIARES (Estilo del Profesor) ---

    private Cliente getClienteById(BigDecimal id) {
        BigDecimal id2 = Objects.requireNonNull(id, "El id no puede ser nulo");
        return clienteRepository.findById(id2)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con el ID: " + id));
    }

    // ESTE ES EL MÉTODO QUE FALTA EN TU ARCHIVO:
    private void validateIdUnico(BigDecimal id) {
        BigDecimal id2 = Objects.requireNonNull(id, "El id no puede ser nulo");
        if (clienteRepository.existsById(id2)) {
            throw new RuntimeException("El ID de cliente " + id + " ya existe en la base de datos.");
        }
    }
}