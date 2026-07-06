package cl.nike.proveedor.mapper;

import cl.nike.proveedor.dto.ProveedorRequest;
import cl.nike.proveedor.dto.ProveedorResponse;
import cl.nike.proveedor.model.Proveedor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProveedorMapper {

    @Mapping(target = "ciudad", ignore = true)
    @Mapping(target = "compras", ignore = true)
    Proveedor toEntity(ProveedorRequest request);

    @Mapping(target = "idCiudad", source = "ciudad.idCiudad")
    ProveedorResponse toResponse(Proveedor proveedor);

    List<ProveedorResponse> toResponseList(List<Proveedor> proveedores);

    @Mapping(target = "ciudad", ignore = true)
    @Mapping(target = "compras", ignore = true)
    void updateEntity(ProveedorRequest request, @MappingTarget Proveedor proveedor);
}