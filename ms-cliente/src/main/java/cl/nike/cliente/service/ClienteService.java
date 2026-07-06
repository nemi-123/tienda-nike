package cl.nike.cliente.service;

import cl.nike.cliente.dto.ClienteRequest;
import cl.nike.cliente.dto.ClienteResponse;
import cl.nike.cliente.mapper.ClienteMapper;
import cl.nike.cliente.model.Ciudad;
import cl.nike.cliente.model.Cliente;
import cl.nike.cliente.repository.CiudadRepository;
import cl.nike.cliente.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final CiudadRepository ciudadRepository;
    private final ClienteMapper clienteMapper;

    public List<ClienteResponse> findAll() {
        return clienteMapper.toResponseList(clienteRepository.findAll());
    }

    public ClienteResponse findById(BigDecimal id) {
        return clienteMapper.toResponse(getClienteById(id));
    }

    @Transactional
    public ClienteResponse create(ClienteRequest request) {
        Cliente cliente = clienteMapper.toEntity(request);
        cliente.setCiudad(getCiudadById(request.getIdCiudad()));
        return clienteMapper.toResponse(clienteRepository.save(cliente));
    }

    @Transactional
    public ClienteResponse update(BigDecimal id, ClienteRequest request) {
        Cliente cliente = getClienteById(id);
        clienteMapper.updateEntity(request, cliente);
        cliente.setCiudad(getCiudadById(request.getIdCiudad()));
        return clienteMapper.toResponse(clienteRepository.save(cliente));
    }

    @Transactional
    public void deleteById(BigDecimal id) {
        Cliente cliente = getClienteById(id);
        if (cliente.getCompras() != null && !cliente.getCompras().isEmpty()) {
            throw new RuntimeException("No se puede eliminar el cliente porque tiene compras asociadas");
        }
        clienteRepository.delete(cliente);
    }

    private Cliente getClienteById(BigDecimal id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
    }

    private Ciudad getCiudadById(BigDecimal id) {
        return ciudadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada con ID: " + id));
    }
}