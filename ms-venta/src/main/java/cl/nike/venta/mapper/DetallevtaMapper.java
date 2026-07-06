package cl.nike.venta.mapper;

import cl.nike.venta.dto.DetallevtaRequest;
import cl.nike.venta.dto.DetallevtaResponse;
import cl.nike.venta.model.Detallevta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DetallevtaMapper {

    // Transforma el Request (DTO) a la Entidad.
    // Mapeamos el idventa del request directamente al objeto interno Venta
    @Mapping(target = "venta.idventa", source = "idventa")
    Detallevta toEntity(DetallevtaRequest request);

    // Transforma la Entidad a Response para el cliente.
    // Extraemos el idventa desde el objeto interno Venta (MapStruct maneja los null de forma segura)
    @Mapping(target = "idventa", source = "venta.idventa")
    DetallevtaResponse toResponse(Detallevta entity);
}