package cl.nike.categoria.mapper;

import cl.nike.categoria.dto.CategoriaRequest;
import cl.nike.categoria.dto.CategoriaResponse;
import cl.nike.categoria.model.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    // 1. De Request a Entidad (Para crear)
    @Mapping(target = "productos", ignore = true)
    @Mapping(target = "tipo.idTipo", source = "idTipo") // Mapea el número al ID del objeto Tipo
    Categoria toEntity(CategoriaRequest request);

    // 2. De Entidad a Response (Para mostrar)
    @Mapping(target = "idTipo", source = "tipo.idTipo") // Extrae el número desde el objeto Tipo
    CategoriaResponse toResponse(Categoria categoria);

    List<CategoriaResponse> toResponseList(List<Categoria> categorias);

    // 3. De Request a Entidad existente (Para actualizar)
    @Mapping(target = "productos", ignore = true)
    @Mapping(target = "tipo", ignore = true)
    void updateEntity(CategoriaRequest request, @MappingTarget Categoria categoria);
}