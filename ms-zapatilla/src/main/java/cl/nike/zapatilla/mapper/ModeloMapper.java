package cl.nike.zapatilla.mapper;

import cl.nike.zapatilla.dto.ModeloRequest;
import cl.nike.zapatilla.dto.ModeloResponse;
import cl.nike.zapatilla.model.Marca;
import cl.nike.zapatilla.model.Modelo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ModeloMapper {

    // 1. DTO Request a Entidad
    // Ignoramos 'idmodelo' porque usualmente se maneja de forma controlada.
    // Ignoramos 'zapatillas' para resguardar la relación Lazy.
    // Usamos una expresión custom o método para mapear el idmarca a la entidad Marca de referencia.
    @Mapping(target = "idmodelo", ignore = true)
    @Mapping(target = "zapatillas", ignore = true)
    @Mapping(target = "marca", source = "idmarca", qualifiedByName = "idToMarca")
    Modelo toEntity(ModeloRequest request);

    // 2. Entidad a DTO Response
    // Extraemos el idmarca desde el objeto Marca interno de forma automática y segura
    @Mapping(target = "idmarca", source = "marca.idmarca")
    ModeloResponse toResponse(Modelo modelo);

    // 3. Mapeo automático de Listas (para el findAll de modelos)
    List<ModeloResponse> toResponseList(List<Modelo> modelos);

    // 4. Actualización sobre el Destino (para el método Update del Service)
    // Protege los IDs y colecciones de modificaciones accidentales
    @Mapping(target = "idmodelo", ignore = true)
    @Mapping(target = "zapatillas", ignore = true)
    @Mapping(target = "marca", source = "idmarca", qualifiedByName = "idToMarca")
    void updateEntity(ModeloRequest request, @MappingTarget Modelo modelo);

    // Método de soporte para construir el objeto Marca de referencia solo con su ID
    @Named("idToMarca")
    default Marca idToMarca(BigDecimal idmarca) {
        if (idmarca == null) {
            return null;
        }
        return Marca.builder()
                .idmarca(idmarca)
                .build();
    }
}