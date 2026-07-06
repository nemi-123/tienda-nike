@echo off
echo.
echo === REINSTALACION DE DEPENDENCIAS MAVEN ===
echo.

REM Paso 1: Eliminar carpeta local de dependencias
echo Eliminando carpeta .m2 ...
rmdir /s /q %USERPROFILE%\.m2

REM Paso 2: Eliminar carpetas target de los proyectos
echo Eliminando carpetas target ...
rmdir /s /q eureka\target

rmdir /s /q ms-marca\target

rmdir /s /q ms-modelo\target

rmdir /s /q ms-categoria\target

rmdir /s /q ms-zapatilla\target

rmdir /s /q ms-cliente\target

rmdir /s /q ms-vendedor\target

rmdir /s /q ms-venta\target

rmdir /s /q ms-detallevta\target

rmdir /s /q ms-proveedor\target

rmdir /s /q ms-compra\target

REM Paso 3: Instalar todas las dependencias forzadamente
echo Descargando dependencias nuevamente con Maven ...
mvn clean install -U -DskipTests

echo.
echo === PROCESO COMPLETADO ===
pause
