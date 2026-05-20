@echo off
echo.
echo === COMPILANDO MICROSERVICIOS ===
echo.
call cd C:\tienda\ms-marca
call mvn clean install -U
call cd C:\tienda\ms-modelo
call mvn clean install -U
call cd C:\tienda\ms-categoria
call mvn clean install -U
call cd C:\tienda\ms-zapatilla
call mvn clean install -U
call cd C:\tienda\ms-cliente
call mvn clean install -U
call cd C:\tienda\ms-vendedor
call mvn clean install -U
call cd C:\tienda\ms-venta
call mvn clean install -U
call cd C:\tienda\ms-detallevta
call mvn clean install -U
call cd C:\tienda\ms-proveedor
call mvn clean install -U
call cd C:\tienda\ms-compra
call mvn clean install -U
echo.
echo === COMPILACION COMPLETADA ===
pause
