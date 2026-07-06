package cl.nike.detallevta.mapper;

import cl.nike.detallevta.dto.DetallevtaRequest;
import cl.nike.detallevta.dto.DetallevtaResponse;
import cl.nike.detallevta.model.Detallevta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface DetallevtaMapper {

    @Mapping(target = "venta", ignore = true)
    @Mapping(target = "producto", ignore = true)
    Detallevta toEntity(DetallevtaRequest request);

    @Mapping(target = "idVenta", source = "venta.idVenta")
    @Mapping(target = "idProducto", source = "producto.idProducto")
    DetallevtaResponse toResponse(Detallevta detalle);

    List<DetallevtaResponse> toResponseList(List<Detallevta> detalles);

    @Mapping(target = "venta", ignore = true)
    @Mapping(target = "producto", ignore = true)
    void updateEntity(DetallevtaRequest request, @MappingTarget Detallevta detalle);
}