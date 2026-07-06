package cl.nike.proveedor.mapper;

import cl.nike.proveedor.dto.CiudadRequest;
import cl.nike.proveedor.dto.CiudadResponse;
import cl.nike.proveedor.model.Ciudad;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CiudadMapper {

    @Mapping(target = "proveedores", ignore = true)
    Ciudad toEntity(CiudadRequest request);

    CiudadResponse toResponse(Ciudad ciudad);

    List<CiudadResponse> toResponseList(List<Ciudad> ciudades);

    @Mapping(target = "proveedores", ignore = true)
    void updateEntity(CiudadRequest request, @MappingTarget Ciudad ciudad);
}