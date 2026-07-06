package cl.nike.venta.mapper;

import cl.nike.venta.dto.ClienteRequest;
import cl.nike.venta.dto.ClienteResponse;
import cl.nike.venta.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    // Transforma el Request (DTO) a la Entidad para guardarla en la BD.
    // Ignoramos el 'idcliente' (si lo genera la BD o se maneja en el service) 
    // y la lista de 'ventas' para evitar problemas con la relación bidireccional.
    @Mapping(target = "idcliente", ignore = true)
    @Mapping(target = "ventas",    ignore = true)
    Cliente toEntity(ClienteRequest request);

    // Transforma la Entidad a Response para devolver al cliente.
    ClienteResponse toResponse(Cliente cliente);

    // Transforma una lista de Entidades a una lista de Responses.
    List<ClienteResponse> toResponseList(List<Cliente> clientes);

    // Actualización sobre el destino: copia los datos del request en el
    // objeto persistido para que JPA reconozca el UPDATE sin cambiar el ID.
    @Mapping(target = "idcliente", ignore = true)
    @Mapping(target = "ventas",    ignore = true)
    void updateEntity(ClienteRequest request, @MappingTarget Cliente cliente);
}