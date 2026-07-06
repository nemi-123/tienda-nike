package cl.nike.vendedor.mapper;

import cl.nike.vendedor.dto.SucursalRequest;
import cl.nike.vendedor.dto.SucursalResponse;
import cl.nike.vendedor.model.Sucursal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface SucursalMapper {

    @Mapping(target = "vendedores", ignore = true)
    Sucursal toEntity(SucursalRequest request);

    SucursalResponse toResponse(Sucursal sucursal);

    List<SucursalResponse> toResponseList(List<Sucursal> sucursales);

    @Mapping(target = "vendedores", ignore = true)
    void updateEntity(SucursalRequest request, @MappingTarget Sucursal sucursal);
}