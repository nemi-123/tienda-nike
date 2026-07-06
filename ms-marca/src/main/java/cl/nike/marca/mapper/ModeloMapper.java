package cl.nike.marca.mapper;

import cl.nike.marca.dto.ModeloRequest;
import cl.nike.marca.dto.ModeloResponse;
import cl.nike.marca.model.Modelo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ModeloMapper {

    @Mapping(target = "marca", ignore = true)
    Modelo toEntity(ModeloRequest request);

    @Mapping(target = "idMarca", source = "marca.idMarca")
    ModeloResponse toResponse(Modelo modelo);

    List<ModeloResponse> toResponseList(List<Modelo> modelos);

    @Mapping(target = "marca", ignore = true)
    void updateEntity(ModeloRequest request, @MappingTarget Modelo modelo);
}