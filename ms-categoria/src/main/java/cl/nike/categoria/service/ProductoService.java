package cl.nike.categoria.service;

import cl.nike.categoria.dto.ProductoRequest;
import cl.nike.categoria.dto.ProductoResponse;
import cl.nike.categoria.mapper.ProductoMapper;
import cl.nike.categoria.model.Categoria;
import cl.nike.categoria.model.Producto;
import cl.nike.categoria.repository.CategoriaRepository;
import cl.nike.categoria.repository.ProductoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
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
        // Establecer la relación con la categoría
        Categoria categoria = getCategoriaById(request.getIdCategoria());
        producto.setCategoria(categoria);
        
        return productoMapper.toResponse(productoRepository.save(producto));
    }

    @Transactional
    public ProductoResponse update(BigDecimal id, ProductoRequest request) {
        Producto producto = getProductoById(id);
        productoMapper.updateEntity(request, producto);
        
        // Actualizar relación si cambió
        if (!producto.getCategoria().getIdCategoria().equals(request.getIdCategoria())) {
            producto.setCategoria(getCategoriaById(request.getIdCategoria()));
        }
        
        return productoMapper.toResponse(productoRepository.save(producto));
    }

    @Transactional
    public void deleteById(BigDecimal id) {
        productoRepository.delete(getProductoById(id));
    }

    private Producto getProductoById(BigDecimal id) {
        return productoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }

    private Categoria getCategoriaById(BigDecimal id) {
        return categoriaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + id));
    }
}