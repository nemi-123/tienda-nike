package cl.nike.categoria.mapper;

import cl.nike.categoria.dto.ProductoRequest;
import cl.nike.categoria.dto.ProductoResponse;

import cl.nike.categoria.model.Producto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    @Mapping(target = "idProducto", ignore = true)
    @Mapping(target = "categoria", ignore = true)
    Producto toEntity(ProductoRequest request);

    @Mapping(target = "idCategoria", source = "categoria.idCategoria")
    ProductoResponse toResponse(Producto producto);

    List<ProductoResponse> toResponseList(List<Producto> productos);

    @Mapping(target = "idProducto", ignore = true)
    @Mapping(target = "categoria", ignore = true)
    void updateEntity(ProductoRequest request, @MappingTarget Producto producto);
}