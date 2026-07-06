package cl.nike.cliente.mapper;

import cl.nike.cliente.dto.CompracliRequest;
import cl.nike.cliente.dto.CompracliResponse;
import cl.nike.cliente.model.Compracli;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CompracliMapper {

    @Mapping(target = "cliente", ignore = true)
    Compracli toEntity(CompracliRequest request);

    @Mapping(target = "idCliente", source = "cliente.idCliente")
    CompracliResponse toResponse(Compracli compracli);

    List<CompracliResponse> toResponseList(List<Compracli> compras);

    @Mapping(target = "cliente", ignore = true)
    void updateEntity(CompracliRequest request, @MappingTarget Compracli compracli);
}