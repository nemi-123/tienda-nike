package cl.nike.categoria.service;

import cl.nike.categoria.dto.ProductoRequest;
import cl.nike.categoria.dto.ProductoResponse;

import java.math.BigDecimal;
import java.util.List;

public interface ProductoService {
    List<ProductoResponse> listarTodos();
    ProductoResponse buscarPorId(BigDecimal id);
    ProductoResponse crear(ProductoRequest request);
    ProductoResponse actualizar(BigDecimal id, ProductoRequest request);
    void eliminar(BigDecimal id);
}