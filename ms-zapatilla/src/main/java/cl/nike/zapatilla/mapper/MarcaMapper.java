package cl.nike.zapatilla.mapper;

import cl.nike.zapatilla.dto.MarcaRequest;
import cl.nike.zapatilla.dto.MarcaResponse;
import cl.nike.zapatilla.model.Marca;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MarcaMapper {

    // Transforma el Request (DTO) a la Entidad para guardarla en la BD.
    // Ignoramos 'idmarca' porque se gestiona de forma manual/específica en el Service,
    // e ignoramos 'modelos' porque el Service debe manejar la relación Lazy/Asociaciones de forma controlada.
    @Mapping(target = "idmarca", ignore = true)
    @Mapping(target = "modelos", ignore = true)
    Marca toEntity(MarcaRequest request);

    // Transforma la Entidad a Response para devolver al cliente.
    // Aquí sí queremos que se mapeen los atributos para mostrarlos.
    MarcaResponse toResponse(Marca marca);

    // Mapea automáticamente colecciones de Marcas a Respuestas (usado en el findAll)
    List<MarcaResponse> toResponseList(List<Marca> marcas);

    // Este método realiza una "Actualización sobre el Destino" usando @MappingTarget.
    // En lugar de crear un objeto Marca nuevo, MapStruct copia los datos del 'request'
    // directamente sobre la instancia de 'marca' que ya recuperamos de la base de datos.
    // 1. Mantiene la identidad de la entidad: Al trabajar sobre el mismo objeto, JPA sabe que debe hacer un UPDATE.
    // 2. Protege el ID: Se ignora 'idmarca' del request para que no se altere la llave primaria por accidente.
    // 3. Respeta la lógica de negocio: Ignoramos 'modelos' para resguardar la integridad referencial.
    @Mapping(target = "idmarca", ignore = true)
    @Mapping(target = "modelos", ignore = true)
    void updateEntity(MarcaRequest request, @MappingTarget Marca marca);
}