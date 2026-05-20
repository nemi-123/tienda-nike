package cl.nike.categoria.service;

import cl.nike.categoria.dto.CategoriaRequest;
import cl.nike.categoria.dto.CategoriaResponse;

import java.math.BigDecimal;
import java.util.List;

public interface CategoriaService {
    List<CategoriaResponse> listarTodas();
    CategoriaResponse buscarPorId(BigDecimal id);
    CategoriaResponse crear(CategoriaRequest request);
    CategoriaResponse actualizar(BigDecimal id, CategoriaRequest request);
    void eliminar(BigDecimal id);
}