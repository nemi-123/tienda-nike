package cl.nike.detallevta.service;

import cl.nike.detallevta.dto.ProductoRequest;
import cl.nike.detallevta.dto.ProductoResponse;
import cl.nike.detallevta.mapper.ProductoMapper;
import cl.nike.detallevta.model.Producto;
import cl.nike.detallevta.repository.ProductoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    public List<ProductoResponse> findAll() {
        return productoMapper.toResponseList(productoRepository.findAll());
    }

    public ProductoResponse findById(BigDecimal id) {
        return productoMapper.toResponse(getProductoById(id));
    }

    @Transactional
    public ProductoResponse create(ProductoRequest request) {
        Producto producto = productoMapper.toEntity(request);
        return productoMapper.toResponse(productoRepository.save(producto));
    }

    @Transactional
    public ProductoResponse update(BigDecimal id, ProductoRequest request) {
        Producto producto = getProductoById(id);
        productoMapper.updateEntity(request, producto);
        return productoMapper.toResponse(productoRepository.save(producto));
    }

    @Transactional
    public void deleteById(BigDecimal id) {
        Producto producto = getProductoById(id);
        if (producto.getDetalles() != null && !producto.getDetalles().isEmpty()) {
            throw new RuntimeException("No se puede eliminar el producto porque tiene detalles de venta asociados");
        }
        productoRepository.delete(producto);
    }

    private Producto getProductoById(BigDecimal id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }
}