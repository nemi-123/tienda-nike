package cl.nike.categoria.mapper;

import cl.nike.categoria.dto.CategoriaRequest;
import cl.nike.categoria.dto.CategoriaResponse;
import cl.nike.categoria.model.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    /**
     * De Modelo (Base de datos) a DTO de Salida
     */
    public CategoriaResponse toResponse(Categoria categoria) {
        if (categoria == null) {
            return null;
        }
        
        CategoriaResponse response = new CategoriaResponse();
        response.setIdCategoria(categoria.getIdCategoria());
        response.setNombreCategoria(categoria.getNombre()); // Mapea 'nombre' a 'nombreCategoria'
        return response;
    }

    /**
     * De DTO de Entrada a Modelo (Base de datos)
     */
    public Categoria toEntity(CategoriaRequest request) {
        if (request == null) {
            return null;
        }
        
        return Categoria.builder()
                .idCategoria(request.getIdCategoria())
                .nombre(request.getNombreCategoria()) // Mapea 'nombreCategoria' a 'nombre'
                // 'tipo' y 'productos' se inicializan en null o se manejan en el servicio
                .build();
    }
}