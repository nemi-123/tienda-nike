package cl.nike.categoria.mapper;

import cl.nike.categoria.dto.TipoRequest;
import cl.nike.categoria.dto.TipoResponse;
import cl.nike.categoria.model.Tipo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TipoMapper {

    @Mapping(target = "categorias", ignore = true)
    Tipo toEntity(TipoRequest request);

    TipoResponse toResponse(Tipo tipo);

    List<TipoResponse> toResponseList(List<Tipo> tipos);

    @Mapping(target = "categorias", ignore = true)
    void updateEntity(TipoRequest request, @MappingTarget Tipo tipo);
}