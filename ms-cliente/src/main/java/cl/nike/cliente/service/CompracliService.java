package cl.nike.cliente.service;

import cl.nike.cliente.dto.CompracliRequest;
import cl.nike.cliente.dto.CompracliResponse;
import cl.nike.cliente.mapper.CompracliMapper;
import cl.nike.cliente.model.Cliente;
import cl.nike.cliente.model.Compracli;
import cl.nike.cliente.repository.ClienteRepository;
import cl.nike.cliente.repository.CompracliRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompracliService {

    private final CompracliRepository compracliRepository;
    private final ClienteRepository clienteRepository;
    private final CompracliMapper compracliMapper;

    public List<CompracliResponse> findAll() {
        return compracliMapper.toResponseList(compracliRepository.findAll());
    }

    public CompracliResponse findById(BigDecimal id) {
        return compracliMapper.toResponse(getCompracliById(id));
    }

    @Transactional
    public CompracliResponse create(CompracliRequest request) {
        Compracli compra = compracliMapper.toEntity(request);
        compra.setCliente(getClienteById(request.getIdCliente()));
        return compracliMapper.toResponse(compracliRepository.save(compra));
    }

    @Transactional
    public CompracliResponse update(BigDecimal id, CompracliRequest request) {
        Compracli compra = getCompracliById(id);
        compracliMapper.updateEntity(request, compra);
        compra.setCliente(getClienteById(request.getIdCliente()));
        return compracliMapper.toResponse(compracliRepository.save(compra));
    }

    @Transactional
    public void deleteById(BigDecimal id) {
        compracliRepository.delete(getCompracliById(id));
    }

    private Compracli getCompracliById(BigDecimal id) {
        return compracliRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra no encontrada con ID: " + id));
    }

    private Cliente getClienteById(BigDecimal id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
    }
}