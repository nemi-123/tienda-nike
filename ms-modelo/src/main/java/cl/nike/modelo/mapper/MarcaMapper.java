package cl.nike.modelo.mapper;

import cl.nike.modelo.dto.MarcaRequest;
import cl.nike.modelo.dto.MarcaResponse;
import cl.nike.modelo.model.Marca;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MarcaMapper {

    @Mapping(target = "modelos", ignore = true)
    Marca toEntity(MarcaRequest request);

    MarcaResponse toResponse(Marca marca);

    List<MarcaResponse> toResponseList(List<Marca> marcas);

    @Mapping(target = "modelos", ignore = true)
    void updateEntity(MarcaRequest request, @MappingTarget Marca marca);
}