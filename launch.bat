@echo off
setlocal

:MENU
cls
echo.
echo ============================================
echo   Nike - MENU PRINCIPAL
echo ============================================
echo.
echo   [1] Iniciar todos los servicios (dev)
echo   [2] Iniciar todos los servicios (test)
echo   [3] Compilar microservicios
echo   [4] Reinstalar dependencias Maven
echo.
echo   --- Servicios individuales ---
echo   [5] Iniciar Eureka
echo   [6] Iniciar ms-categoria
echo   [7] Iniciar ms-cliente
echo   [8] Iniciar ms-compra
echo   [9] Iniciar ms-detallevta
echo   [10] Iniciar ms-marca
echo   [11] Iniciar ms-modelo
echo   [12] Iniciar ms-proveedor
echo   [13] Iniciar ms-vendedor
echo   [14] Iniciar ms-venta
echo   [15] Iniciar ms-zapatilla
rem [GATEWAY-INI] Nueva opcion para iniciar el API Gateway de forma individual
echo   [16] Iniciar API Gateway
rem [GATEWAY-FIN]
echo.
echo   [0] Salir
echo.
echo ============================================
set /p opcion="  Selecciona una opcion: "

if "%opcion%"=="1" goto RUN_ALL
if "%opcion%"=="2" goto RUN_TEST
if "%opcion%"=="3" goto COMPILE
if "%opcion%"=="4" goto INSTALL
if "%opcion%"=="5" goto RUN_EUREKA
if "%opcion%"=="6" goto RUN_CATEGORIA
if "%opcion%"=="7" goto RUN_CLIENTE
if "%opcion%"=="8" goto RUN_COMPRA
if "%opcion%"=="9" goto RUN_DETALLEVTA
if "%opcion%"=="10" goto RUN_MARCA
if "%opcion%"=="11" goto RUN_MODELO
if "%opcion%"=="12" goto RUN_PROVEEDOR
if "%opcion%"=="13" goto RUN_VENDEDOR
if "%opcion%"=="14" goto RUN_VENTA
if "%opcion%"=="15" goto RUN_ZAPATILLA
rem [GATEWAY-INI] Redireccion para la nueva opcion del API Gateway
if "%opcion%"=="16" goto RUN_GATEWAY
rem [GATEWAY-FIN]
if "%opcion%"=="0" goto SALIR

echo.
echo   Opcion invalida. Intenta de nuevo.
timeout /t 2 /nobreak > nul
goto MENU

REM ============================================

:RUN_ALL
cls
echo.
echo ===== Iniciando Eureka Server =====
start "EUREKA" mvn -f eureka spring-boot:run
timeout /t 5 /nobreak > nul
echo ===== Iniciando Microservicios =====
start "MS-CATEGORIA" mvn -f ms-categoria spring-boot:run
start "MS-CLIENTE" mvn -f ms-cliente spring-boot:run
start "MS-COMPRA" mvn -f ms-compra spring-boot:run
start "MS-DETALLEVTA" mvn -f ms-detallevta spring-boot:run
start "MS-MARCA" mvn -f ms-marca spring-boot:run
start "MS-MODELO" mvn -f ms-modelo spring-boot:run
start "MS-PROVEEDOR" mvn -f ms-proveedor spring-boot:run
start "MS-VENDEDOR" mvn -f ms-vendedor spring-boot:run
start "MS-VENTA" mvn -f ms-venta spring-boot:run
start "MS-ZAPATILLA" mvn -f ms-zapatilla spring-boot:run
rem [GATEWAY-INI] Se inicia el API Gateway despues de los microservicios para que encuentre servicios en Eureka
timeout /t 5 /nobreak > nul
echo ===== Iniciando API Gateway =====
start "API-GATEWAY" mvn -f api-gateway spring-boot:run
rem [GATEWAY-FIN]
echo Todos los servicios han sido lanzados.
pause
goto MENU

:RUN_TEST
cls
echo.
echo ===== Iniciando Eureka Server (test) =====
start "EUREKA" java -jar eureka\target\cl-nike-eureka-1.0-SNAPSHOT.jar --spring.profiles.active=test
timeout /t 5 /nobreak > nul
echo ===== Iniciando Microservicios (test) =====
start "MS-CATEGORIA" java -jar ms-categoria\\target\\cl-nike-categoria-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
start "MS-CLIENTE" java -jar ms-cliente\\target\\cl-nike-cliente-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
start "MS-COMPRA" java -jar ms-compra\\target\\cl-nike-compra-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
start "MS-DETALLEVTA" java -jar ms-detallevta\\target\\cl-nike-detallevta-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
start "MS-MARCA" java -jar ms-marca\\target\\cl-nike-marca-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
start "MS-MODELO" java -jar ms-modelo\\target\\cl-nike-modelo-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
start "MS-PROVEEDOR" java -jar ms-proveedor\\target\\cl-nike-proveedor-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
start "MS-VENDEDOR" java -jar ms-vendedor\\target\\cl-nike-vendedor-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
start "MS-VENTA" java -jar ms-venta\\target\\cl-nike-venta-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
start "MS-ZAPATILLA" java -jar ms-zapatilla\\target\\cl-nike-zapatilla-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
rem [GATEWAY-INI] Se inicia el API Gateway en modo test
timeout /t 5 /nobreak > nul
echo ===== Iniciando API Gateway (test) =====
start "API-GATEWAY" java -jar api-gateway\\target\\cl-nike-gateway-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
rem [GATEWAY-FIN]
echo Todos los servicios han sido lanzados en modo test.
pause
goto MENU

:COMPILE
cls
echo.
echo ===== Compilando microservicios =====

call cd C:\tienda\ms-categoria

call mvn clean install -U

call cd C:\tienda\ms-cliente

call mvn clean install -U

call cd C:\tienda\ms-compra

call mvn clean install -U

call cd C:\tienda\ms-datallevta

call mvn clean install -U

call cd C:\tienda\ms-marca

call mvn clean install -U

call cd C:\tienda\ms-modelo

call mvn clean install -U

call cd C:\tienda\ms-proveedor

call mvn clean install -U

call cd C:\tienda\ms-vendedor

call mvn clean install -U

call cd C:\tienda\ms-venta

call mvn clean install -U

call cd C:\tienda\ms-zapatilla

call mvn clean install -U
echo Compilacion completada.
pause
goto MENU

:INSTALL
cls
echo.
echo === REINSTALACION DE DEPENDENCIAS MAVEN ===
echo.
echo Eliminando carpeta .m2 ...
rmdir /s /q %USERPROFILE%\.m2
echo Eliminando carpetas target ...
rmdir /s /q C:\tienda\eureka\target

rmdir /s /q C:\tienda\ms-categoria\target

rmdir /s /q C:\tienda\ms-cliente\target
rmdir /s /q C:\tienda\ms-compra\target
rmdir /s /q C:\tienda\ms-detallevta\target
rmdir /s /q C:\tienda\ms-marca\target
rmdir /s /q C:\tienda\ms-modelo\target
rmdir /s /q C:\tienda\ms-proveedor\target
rmdir /s /q C:\tienda\ms-vendedor\target
rmdir /s /q C:\tienda\ms-venta\target
rmdir /s /q C:\tienda\ms-zapatilla\target




echo Descargando dependencias nuevamente con Maven ...
mvn clean install -U -DskipTests
echo.
echo === PROCESO COMPLETADO ===
pause
goto MENU

:RUN_EUREKA
cls
echo.
echo ===== Iniciando Eureka =====
start "EUREKA" mvn -f eureka spring-boot:run
echo Eureka iniciado.
pause
goto MENU

:RUN_CATEGORIA

cls

echo.

echo ===== Iniciando ms-categoria =====

start "MS-CATEGORIA" mvn -f ms-categoria spring-boot:run

echo ms-categoria iniciado.

pause

goto MENU



:RUN_CLIENTE

cls

echo.

echo ===== Iniciando ms-cliente =====

start "MS-CLIENTE" mvn -f ms-cliente spring-boot:run

echo ms-cliente iniciado.

pause

goto MENU



:RUN_COMPRA

cls

echo.

echo ===== Iniciando ms-compra =====

start "MS-COMPRA" mvn -f ms-compra spring-boot:run

echo ms-compra iniciado.

pause

goto MENU

:RUN_DETALLEVTA

cls

echo.

echo ===== Iniciando ms-detallvta =====

start "MS-DETALLEVTA" mvn -f ms-detallevta spring-boot:run

echo ms-detallevta iniciado.

pause

goto MENU

:RUN_MARCA

cls

echo.

echo ===== Iniciando ms-marca =====

start "MS-MARCA" mvn -f ms-marca spring-boot:run

echo ms-marca iniciado.

pause

goto MENU

:RUN_MODELO

cls

echo.

echo ===== Iniciando ms-modelo =====

start "MS-MODELO" mvn -f ms-modelo spring-boot:run

echo ms-modelo iniciado.

pause

goto MENU

:RUN_PROVEEDOR

cls

echo.

echo ===== Iniciando ms-proveedor =====

start "MS-PROVEEDOR" mvn -f ms-proveedor spring-boot:run

echo ms-proveedor iniciado.

pause

goto MENU

:RUN_VENDEDOR

cls

echo.

echo ===== Iniciando ms-vendedor =====

start "MS-VENDEDOR" mvn -f ms-vendedor spring-boot:run

echo ms-vendedor iniciado.

pause

goto MENU

:RUN_VENTA

cls

echo.

echo ===== Iniciando ms-venta =====

start "MS-VENTA" mvn -f ms-venta spring-boot:run

echo ms-venta iniciado.

pause

goto MENU

:RUN_ZAPATILLA

cls

echo.

echo ===== Iniciando ms-zapatilla =====

start "MS-ZAPATILLA" mvn -f ms-zapatilla spring-boot:run

echo ms-zapatilla iniciado.

pause

goto MENU

rem [GATEWAY-INI] Seccion para iniciar el API Gateway de forma individual
:RUN_GATEWAY
cls
echo.
echo ===== Iniciando API Gateway =====
start "API-GATEWAY" mvn -f api-gateway spring-boot:run
echo API Gateway iniciado en puerto 9000.
pause
goto MENU
rem [GATEWAY-FIN]

:SALIR
cls
echo.
echo   Hasta luego.
echo.
endlocal
exit /b
