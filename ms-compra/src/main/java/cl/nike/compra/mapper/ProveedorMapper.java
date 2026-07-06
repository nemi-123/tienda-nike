package cl.nike.compra.mapper;

import cl.nike.compra.dto.ProveedorRequest;
import cl.nike.compra.dto.ProveedorResponse;
import cl.nike.compra.model.Proveedor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProveedorMapper {

    @Mapping(target = "compras", ignore = true)
    Proveedor toEntity(ProveedorRequest request);

    ProveedorResponse toResponse(Proveedor proveedor);

    List<ProveedorResponse> toResponseList(List<Proveedor> proveedores);

    @Mapping(target = "compras", ignore = true)
    void updateEntity(ProveedorRequest request, @MappingTarget Proveedor proveedor);
}