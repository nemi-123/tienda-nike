package cl.nike.marca.mapper;

import cl.nike.marca.dto.MarcaRequest;
import cl.nike.marca.dto.MarcaResponse;
import cl.nike.marca.model.Marca;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MarcaMapper {

    @Mapping(target = "pais", ignore = true)
    @Mapping(target = "modelos", ignore = true)
    Marca toEntity(MarcaRequest request);

    @Mapping(target = "idPais", source = "pais.idPais")
    MarcaResponse toResponse(Marca marca);

    List<MarcaResponse> toResponseList(List<Marca> marcas);

    @Mapping(target = "pais", ignore = true)
    @Mapping(target = "modelos", ignore = true)
    void updateEntity(MarcaRequest request, @MappingTarget Marca marca);
}