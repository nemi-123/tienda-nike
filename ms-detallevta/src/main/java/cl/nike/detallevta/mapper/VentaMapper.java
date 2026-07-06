package cl.nike.detallevta.mapper;

import cl.nike.detallevta.dto.VentaRequest;
import cl.nike.detallevta.dto.VentaResponse;
import cl.nike.detallevta.model.Venta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface VentaMapper {

    @Mapping(target = "detalles", ignore = true)
    Venta toEntity(VentaRequest request);

    VentaResponse toResponse(Venta venta);

    List<VentaResponse> toResponseList(List<Venta> ventas);

    @Mapping(target = "detalles", ignore = true)
    void updateEntity(VentaRequest request, @MappingTarget Venta venta);
}