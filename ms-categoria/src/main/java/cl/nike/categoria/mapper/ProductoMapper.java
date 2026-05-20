package cl.nike.categoria.mapper;

import cl.nike.categoria.dto.ProductoRequest;
import cl.nike.categoria.dto.ProductoResponse;
import cl.nike.categoria.model.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    public ProductoResponse toResponse(Producto producto) {
        if (producto == null) {
            return null;
        }
        return new ProductoResponse(
                producto.getIdProducto(),
                producto.getNombre(), // de nombre a nombreProducto
                producto.getPrecio()
        );
    }

    public Producto toEntity(ProductoRequest request) {
        if (request == null) {
            return null;
        }
        return Producto.builder()
                .idProducto(request.getIdProducto())
                .nombre(request.getNombreProducto()) // de nombreProducto a nombre
                .precio(request.getPrecio())
                // La relación 'categoria' se asignará en el Service si viene en el request
                .build();
    }
}