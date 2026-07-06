package cl.nike.modelo.mapper;

import cl.nike.modelo.dto.ModeloRequest;
import cl.nike.modelo.dto.ModeloResponse;
import cl.nike.modelo.model.Modelo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ModeloMapper {

    @Mapping(target = "marca", ignore = true)
    @Mapping(target = "categoria", ignore = true)
    Modelo toEntity(ModeloRequest request);

    @Mapping(target = "idMarca", source = "marca.idMarca")
    @Mapping(target = "idCategoria", source = "categoria.idCategoria")
    ModeloResponse toResponse(Modelo modelo);

    List<ModeloResponse> toResponseList(List<Modelo> modelos);

    @Mapping(target = "marca", ignore = true)
    @Mapping(target = "categoria", ignore = true)
    void updateEntity(ModeloRequest request, @MappingTarget Modelo modelo);
}