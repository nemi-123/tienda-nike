package cl.nike.compra.mapper;

import cl.nike.compra.dto.CompraRequest;
import cl.nike.compra.dto.CompraResponse;
import cl.nike.compra.model.Compra;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CompraMapper {

    @Mapping(target = "proveedor", ignore = true)
    @Mapping(target = "detalles", ignore = true)
    Compra toEntity(CompraRequest request);

    @Mapping(target = "idProveedor", source = "proveedor.idProveedor")
    CompraResponse toResponse(Compra compra);

    List<CompraResponse> toResponseList(List<Compra> compras);

    @Mapping(target = "proveedor", ignore = true)
    @Mapping(target = "detalles", ignore = true)
    void updateEntity(CompraRequest request, @MappingTarget Compra compra);
}