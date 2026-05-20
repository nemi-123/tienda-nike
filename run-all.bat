@echo off
echo ===== Iniciando Eureka Server =====
start "eureka" mvn -f eureka spring-boot:run

timeout /t 5 /nobreak > nul

echo ===== Iniciando Microservicios =====
start "ms-marca" mvn -f ms-marca spring-boot:run
start "ms-modelo" mvn -f ms-modelo spring-boot:run
start "ms-categoria" mvn -f ms-categoria spring-boot:run
start "ms-zapatilla" mvn -f ms-zapatilla spring-boot:run
start "ms-cliente" mvn -f ms-cliente spring-boot:run
start "ms-vendedor" mvn -f ms-vendedor spring-boot:run
start "ms-venta" mvn -f ms-venta spring-boot:run
start "ms-detallevta" mvn -f ms-detallevta spring-boot:run
start "ms-proveedor" mvn -f ms-proveedor spring-boot:run
start "ms-compra" mvn -f ms-compra spring-boot:run
rem Agrega aqui los demas microservicios si necesitas

echo Todos los servicios han sido lanzados.
