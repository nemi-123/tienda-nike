package cl.nike.cliente.mapper;

import cl.nike.cliente.dto.CiudadRequest;
import cl.nike.cliente.dto.CiudadResponse;
import cl.nike.cliente.model.Ciudad;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CiudadMapper {

    @Mapping(target = "clientes", ignore = true)
    Ciudad toEntity(CiudadRequest request);

    CiudadResponse toResponse(Ciudad ciudad);

    List<CiudadResponse> toResponseList(List<Ciudad> ciudades);

    @Mapping(target = "clientes", ignore = true)
    void updateEntity(CiudadRequest request, @MappingTarget Ciudad ciudad);
}