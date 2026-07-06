package cl.nike.marca.mapper;

import cl.nike.marca.dto.PaisRequest;
import cl.nike.marca.dto.PaisResponse;
import cl.nike.marca.model.Pais;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PaisMapper {

    @Mapping(target = "marcas", ignore = true)
    Pais toEntity(PaisRequest request);

    PaisResponse toResponse(Pais pais);

    List<PaisResponse> toResponseList(List<Pais> paises);

    @Mapping(target = "marcas", ignore = true)
    void updateEntity(PaisRequest request, @MappingTarget Pais pais);
}