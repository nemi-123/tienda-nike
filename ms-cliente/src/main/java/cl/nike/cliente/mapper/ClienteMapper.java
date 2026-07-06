package cl.nike.cliente.mapper;

import cl.nike.cliente.dto.ClienteRequest;
import cl.nike.cliente.dto.ClienteResponse;
import cl.nike.cliente.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy; // <--- Importante importar esto
import java.util.List;

// unmappedTargetPolicy = ReportingPolicy.IGNORE silenciará todos los warnings de este mapper automáticamente
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClienteMapper {

    @Mapping(target = "ciudad", ignore = true)
    @Mapping(target = "compras", ignore = true)
    Cliente toEntity(ClienteRequest request);

    @Mapping(target = "idCiudad", source = "ciudad.idCiudad")
    ClienteResponse toResponse(Cliente cliente);

    List<ClienteResponse> toResponseList(List<Cliente> clientes);

    @Mapping(target = "ciudad", ignore = true)
    @Mapping(target = "compras", ignore = true)
    void updateEntity(ClienteRequest request, @MappingTarget Cliente cliente);
}