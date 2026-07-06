package cl.nike.vendedor.mapper;

import cl.nike.vendedor.dto.VendedorRequest;
import cl.nike.vendedor.dto.VendedorResponse;
import cl.nike.vendedor.model.Vendedor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface VendedorMapper {

    @Mapping(target = "sucursal", ignore = true)
    @Mapping(target = "ventas", ignore = true)
    Vendedor toEntity(VendedorRequest request);

    @Mapping(target = "idSucursal", source = "sucursal.idSucursal")
    VendedorResponse toResponse(Vendedor vendedor);

    List<VendedorResponse> toResponseList(List<Vendedor> vendedores);

    @Mapping(target = "sucursal", ignore = true)
    @Mapping(target = "ventas", ignore = true)
    void updateEntity(VendedorRequest request, @MappingTarget Vendedor vendedor);
}