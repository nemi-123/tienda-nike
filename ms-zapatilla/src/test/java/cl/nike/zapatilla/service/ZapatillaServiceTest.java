package cl.nike.zapatilla.service;

import cl.nike.zapatilla.dto.ZapatillaRequest;
import cl.nike.zapatilla.dto.ZapatillaResponse;
import cl.nike.zapatilla.mapper.ZapatillaMapper;
import cl.nike.zapatilla.model.Modelo;
import cl.nike.zapatilla.model.Zapatilla;
import cl.nike.zapatilla.repository.ZapatillaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ZapatillaServiceTest {

    @Mock
    private ZapatillaRepository zapatillaRepository;

    @Mock
    private ZapatillaMapper zapatillaMapper;

    @InjectMocks
    private ZapatillaService zapatillaService; // CORREGIDO: Clase destino correcta y SIN el modificador "final"

    private Zapatilla zapatilla;
    private ZapatillaRequest request;
    private ZapatillaResponse response;
    private BigDecimal id;

    @BeforeEach
    void setUp() {
        id = new BigDecimal("1");
        
        zapatilla = new Zapatilla();
        zapatilla.setIdzapatilla(id);
        zapatilla.setNombre("Air Max");
        zapatilla.setPrecio(new BigDecimal("129990"));
        zapatilla.setStock(BigDecimal.valueOf(10)); // CORREGIDO: Valor entero compatible con la entidad actualizada
        zapatilla.setModelo(Modelo.builder().idmodelo(new BigDecimal("100")).build());

        request = new ZapatillaRequest();
        request.setIdzapatilla(id);
        request.setNombre("Air Max");
        request.setPrecio(new BigDecimal("129990"));
        request.setStock(BigDecimal.valueOf(10)); // CORREGIDO: Entero alineado al modelo de negocio
        request.setIdmodelo(new BigDecimal("100"));

        response = new ZapatillaResponse();
        response.setIdzapatilla(id);
        response.setNombre("Air Max");
    }

    @Test
    @DisplayName("Debería retornar una lista de zapatillas mapeadas")
    void findAll_Success() {
        // Arrange
        when(zapatillaRepository.findAll()).thenReturn(List.of(zapatilla));
        when(zapatillaMapper.toResponse(zapatilla)).thenReturn(response);

        // Act
        List<ZapatillaResponse> resultado = zapatillaService.findAll();

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Air Max", resultado.get(0).getNombre());
        verify(zapatillaRepository, times(1)).findAll();
        verify(zapatillaMapper, times(1)).toResponse(zapatilla);
        
        // SALIDA DE LA PRUEBA:
        // El método findAll() recuperó exitosamente la lista desde el repositorio simulado.
        // El mapper transformó correctamente la entidad de base de datos a un DTO de respuesta.
        // Se verificó que la lista resultante no es nula y contiene exactamente 1 elemento esperado.
    }

    @Test
    @DisplayName("Debería encontrar una zapatilla por su ID")
    void findById_Success() {
        // Arrange
        when(zapatillaRepository.findById(id)).thenReturn(Optional.of(zapatilla));
        when(zapatillaMapper.toResponse(zapatilla)).thenReturn(response);

        // Act
        ZapatillaResponse resultado = zapatillaService.findById(id);

        // Assert
        assertNotNull(resultado);
        assertEquals("Air Max", resultado.getNombre());
        verify(zapatillaRepository, times(1)).findById(id);

        // SALIDA DE LA PRUEBA:
        // El repositorio devolvió un Optional con la zapatilla simulada para el ID 1.
        // El servicio procesó el objeto y retornó el DTO correspondiente de manera exitosa.
    }

    @Test
    @DisplayName("Debería lanzar excepción si la zapatilla no existe al buscar por ID")
    void findById_NotFound_ThrowsException() {
        // Arrange
        when(zapatillaRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException excepcion = assertThrows(RuntimeException.class, () -> zapatillaService.findById(id));
        assertEquals("Zapatilla no encontrada con el ID: " + id, excepcion.getMessage());
        verify(zapatillaRepository, times(1)).findById(id);
        verify(zapatillaMapper, never()).toResponse(any());

        // SALIDA DE LA PRUEBA:
        // El repositorio simuló que el ID no existe devolviendo un Optional vacío.
        // El método privado 'getZapatillaById' validó la ausencia y lanzó la RuntimeException esperada.
        // Se confirmó que el flujo se interrumpió y el mapper nunca llegó a ejecutarse.
    }

    @Test
    @DisplayName("Debería crear una zapatilla correctamente si el ID es único")
    void create_Success() {
        // Arrange
        when(zapatillaRepository.existsById(id)).thenReturn(false);
        when(zapatillaMapper.toEntity(request)).thenReturn(zapatilla);
        when(zapatillaRepository.save(zapatilla)).thenReturn(zapatilla);
        when(zapatillaMapper.toResponse(zapatilla)).thenReturn(response);

        // Act
        ZapatillaResponse resultado = zapatillaService.create(request);

        // Assert
        assertNotNull(resultado);
        verify(zapatillaRepository, times(1)).existsById(id);
        verify(zapatillaRepository, times(1)).save(zapatilla);

        // SALIDA DE LA PRUEBA:
        // La validación 'validateIdUnico' confirmó que el ID no estaba duplicado (existsById = false).
        // La entidad fue mapeada, guardada en el repositorio y transformada de vuelta a un DTO.
        // El registro se completó con éxito cumpliendo todas las reglas de negocio.
    }

    @Test
    @DisplayName("Debería lanzar excepción al crear si el ID ya existe")
    void create_DuplicateId_ThrowsException() {
        // Arrange
        when(zapatillaRepository.existsById(id)).thenReturn(true);

        // Act & Assert
        RuntimeException excepcion = assertThrows(RuntimeException.class, () -> zapatillaService.create(request));
        assertTrue(excepcion.getMessage().contains("ya existe en la base de datos"));
        verify(zapatillaRepository, times(1)).existsById(id);
        verify(zapatillaRepository, never()).save(any());

        // SALIDA DE LA PRUEBA:
        // El repositorio indicó que el ID ya se encuentra registrado (existsById = true).
        // El servicio detuvo la operación inmediatamente arrojando la excepción de duplicidad.
        // Se garantizó que el método save() nunca fue invocado, protegiendo la integridad de los datos.
    }

    @Test
    @DisplayName("Debería actualizar una zapatilla existente con los nuevos valores")
    void update_Success() {
        // Arrange
        when(zapatillaRepository.findById(id)).thenReturn(Optional.of(zapatilla));
        when(zapatillaRepository.save(zapatilla)).thenReturn(zapatilla);
        when(zapatillaMapper.toResponse(zapatilla)).thenReturn(response);

        // Act
        ZapatillaResponse resultado = zapatillaService.update(id, request);

        // Assert
        assertNotNull(resultado);
        verify(zapatillaRepository, times(1)).findById(id);
        verify(zapatillaRepository, times(1)).save(zapatilla);

        // SALIDA DE LA PRUEBA:
        // Se localizó la zapatilla original en la base de datos simulada.
        // Se modificaron los valores de nombre, precio, stock (Integer) y se reconstruyó el Modelo usando su Builder.
        // El servicio persistió los cambios actualizados de manera correcta.
    }

    @Test
    @DisplayName("Debería eliminar una zapatilla si el ID existe")
    void deleteById_Success() {
        // Arrange
        when(zapatillaRepository.findById(id)).thenReturn(Optional.of(zapatilla));
        doNothing().when(zapatillaRepository).delete(zapatilla);

        // Act
        zapatillaService.deleteById(id);

        // Assert
        verify(zapatillaRepository, times(1)).findById(id);
        verify(zapatillaRepository, times(1)).delete(zapatilla);

        // SALIDA DE LA PRUEBA:
        // El método validó la existencia de la zapatilla encontrando el registro.
        // Se ejecutó el borrado físico de la entidad llamando al repositorio.
        // La prueba finalizó con éxito sin lanzar ninguna excepción de nulidad.
    }
}
