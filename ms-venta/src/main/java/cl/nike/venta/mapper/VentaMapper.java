package cl.nike.venta.mapper;

import cl.nike.venta.dto.VentaRequest;
import cl.nike.venta.dto.VentaResponse;
import cl.nike.venta.model.Venta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;

@Mapper(componentModel = "spring", imports = ArrayList.class)
public interface VentaMapper {

    // 1. De Request (Postman) a Entidad (Base de datos)
    @Mapping(target = "cliente.idcliente", source = "idcliente")
    @Mapping(target = "detalles", expression = "java(new ArrayList<>())") // Evita NullPointerException al crear la entidad
    Venta toEntity(VentaRequest request);

    // 2. De Entidad a Response (Lo que se verá en Postman)
    @Mapping(target = "idcliente", source = "cliente.idcliente")
    // Si tu VentaResponse tiene una lista de detalles (ej. List<DetallevtaResponse> detalles), 
    // MapStruct la mapeará automáticamente si tienes su respectivo DetallevtaMapper.
    VentaResponse toResponse(Venta entity);
}