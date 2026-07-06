package cl.nike.zapatilla.mapper;

import cl.nike.zapatilla.dto.ZapatillaRequest;
import cl.nike.zapatilla.dto.ZapatillaResponse;
import cl.nike.zapatilla.model.Modelo;
import cl.nike.zapatilla.model.Zapatilla;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ZapatillaMapper {


    @Mapping(target = "modelo", source = "idmodelo", qualifiedByName = "idToModelo")
    Zapatilla toEntity(ZapatillaRequest request);


    @Mapping(target = "idmodelo", source = "modelo.idmodelo")
    ZapatillaResponse toResponse(Zapatilla zapatilla);

    List<ZapatillaResponse> toResponseList(List<Zapatilla> zapatillas);


    @Mapping(target = "idzapatilla", ignore = true)
    @Mapping(target = "modelo", source = "idmodelo", qualifiedByName = "idToModelo")
    void updateEntity(ZapatillaRequest request, @MappingTarget Zapatilla zapatilla);

    @Named("idToModelo")
    default Modelo idToModelo(BigDecimal idmodelo) {
        if (idmodelo == null) {
            return null;
        }
        return Modelo.builder()
                .idmodelo(idmodelo)
                .build();
    }
}