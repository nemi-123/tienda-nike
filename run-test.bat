do@echo off
echo ===== Iniciando Eureka Server =====
start "EUREKA" java -jar eureka\target\cl.nike-eureka-1.0-SNAPSHOT.jar --spring.profiles.active=test

timeout /t 5 /nobreak > nul

echo ===== Iniciando Microservicios =====
start "MS-MARCA" java -jar ms-marca\target\cl.nike-marca-0.0.1-SNAPSHOT.jar --spring.profiles.active=test

start "MS-MODELO" java -jar ms-modelo\target\cl.nike-modelo-0.0.1-SNAPSHOT.jar --spring.profiles.active=test

start "MS-CATEGORIA" java -jar ms-categoria\target\cl.nike-categoria-0.0.1-SNAPSHOT.jar --spring.profiles.active=test

start "MS-ZAPATILLA" java -jar ms-zapatilla\target\cl.nike-zapatilla-0.0.1-SNAPSHOT.jar --spring.profiles.active=test

start "MS-CLIENTE" java -jar ms-cliente\target\cl.nike-cliente-0.0.1-SNAPSHOT.jar --spring.profiles.active=test

start "MS-VENDEDOR" java -jar ms-vendedor\target\cl.nike-vendedor-0.0.1-SNAPSHOT.jar --spring.profiles.active=test

start "MS-VENTA" java -jar ms-venta\target\cl.nike-venta-0.0.1-SNAPSHOT.jar --spring.profiles.active=test

start "MS-DETALLEVTA" java -jar ms-detallevta\target\cl.nike-detallevta-0.0.1-SNAPSHOT.jar --spring.profiles.active=test

start "MS-PROVEEDOR" java -jar ms-proveedor\target\cl.nike-proveedor-0.0.1-SNAPSHOT.jar --spring.profiles.active=test

start "MS-COMPRA" java -jar ms-compra\target\cl.nike-compra-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
rem Agrega aqui los demas microservicios si necesitas

echo Todos los servicios han sido lanzados.
