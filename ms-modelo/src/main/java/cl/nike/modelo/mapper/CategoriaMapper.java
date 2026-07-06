package cl.nike.modelo.mapper;

import cl.nike.modelo.dto.CategoriaRequest;
import cl.nike.modelo.dto.CategoriaResponse;
import cl.nike.modelo.model.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    @Mapping(target = "modelos", ignore = true)
    Categoria toEntity(CategoriaRequest request);

    CategoriaResponse toResponse(Categoria categoria);

    List<CategoriaResponse> toResponseList(List<Categoria> categorias);

    @Mapping(target = "modelos", ignore = true)
    void updateEntity(CategoriaRequest request, @MappingTarget Categoria categoria);
}