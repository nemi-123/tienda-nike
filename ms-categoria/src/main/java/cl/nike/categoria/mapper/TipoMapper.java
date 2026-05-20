package cl.nike.categoria.mapper;

import cl.nike.categoria.dto.TipoRequest;
import cl.nike.categoria.dto.TipoResponse;
import cl.nike.categoria.model.Tipo;
import org.springframework.stereotype.Component;

@Component
public class TipoMapper {

    public TipoResponse toResponse(Tipo tipo) {
        if (tipo == null) {
            return null;
        }
        TipoResponse response = new TipoResponse();
        response.setIdTipo(tipo.getIdTipo());
        response.setNombreTipo(tipo.getNombre()); // Mapea 'nombre' a 'nombreTipo'
        return response;
    }

    public Tipo toEntity(TipoRequest request) {
        if (request == null) {
            return null;
        }
        return Tipo.builder()
                .idTipo(request.getIdTipo())
                .nombre(request.getNombreTipo()) // Mapea 'nombreTipo' a 'nombre'
                // 'categorias' se queda en null o se maneja en la lógica de negocio
                .build();
    }
}