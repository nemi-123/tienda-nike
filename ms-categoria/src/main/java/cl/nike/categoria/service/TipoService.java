package cl.nike.categoria.service;

import cl.nike.categoria.dto.TipoRequest;
import cl.nike.categoria.dto.TipoResponse;

import java.math.BigDecimal;
import java.util.List;

public interface TipoService {
    List<TipoResponse> listarTodos();
    TipoResponse buscarPorId(BigDecimal id);
    TipoResponse crear(TipoRequest request);
    TipoResponse actualizar(BigDecimal id, TipoRequest request);
    void eliminar(BigDecimal id);
}