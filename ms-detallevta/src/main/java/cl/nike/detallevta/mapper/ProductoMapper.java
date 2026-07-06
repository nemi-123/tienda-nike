package cl.nike.detallevta.mapper;

import cl.nike.detallevta.dto.ProductoRequest;
import cl.nike.detallevta.dto.ProductoResponse;
import cl.nike.detallevta.model.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    @Mapping(target = "detalles", ignore = true)
    Producto toEntity(ProductoRequest request);

    ProductoResponse toResponse(Producto producto);

    List<ProductoResponse> toResponseList(List<Producto> productos);

    @Mapping(target = "detalles", ignore = true)
    void updateEntity(ProductoRequest request, @MappingTarget Producto producto);
}