package cl.nike.vendedor.mapper;

import cl.nike.vendedor.dto.VentaRequest;
import cl.nike.vendedor.dto.VentaResponse;
import cl.nike.vendedor.model.Venta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface VentaMapper {

    @Mapping(target = "vendedor", ignore = true)
    Venta toEntity(VentaRequest request);

    @Mapping(target = "idVendedor", source = "vendedor.idVendedor")
    VentaResponse toResponse(Venta venta);

    List<VentaResponse> toResponseList(List<Venta> ventas);

    @Mapping(target = "vendedor", ignore = true)
    void updateEntity(VentaRequest request, @MappingTarget Venta venta);
}