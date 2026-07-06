package cl.nike.compra.mapper;

import cl.nike.compra.dto.DetallecmpRequest;
import cl.nike.compra.dto.DetallecmpResponse;
import cl.nike.compra.model.Detallecmp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface DetallecmpMapper {

    @Mapping(target = "compra", ignore = true)
    Detallecmp toEntity(DetallecmpRequest request);

    @Mapping(target = "idCompra", source = "compra.idCompra")
    DetallecmpResponse toResponse(Detallecmp detalle);

    List<DetallecmpResponse> toResponseList(List<Detallecmp> detalles);

    @Mapping(target = "compra", ignore = true)
    void updateEntity(DetallecmpRequest request, @MappingTarget Detallecmp detalle);
}