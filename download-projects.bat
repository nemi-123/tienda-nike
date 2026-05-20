@echo off
echo Descargando microservicios Spring Boot...
echo.
echo Descargando eureka.zip...
curl -o eureka.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=eureka&groupId=cl.nike&artifactId=cl-nike-eureka&name=tienda-eureka&description=servicio-eureka&packageName=cl.nike.eureka&packaging=jar&javaVersion=21&dependencies=cloud-eureka-server,devtools"
echo.
echo Descargando ms-marca.zip...
curl -o ms-marca.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=ms-marca&groupId=cl.nike&artifactId=cl-nike-marca&name=tienda-marca&description=servicio-marca&packageName=cl.nike.marca&packaging=jar&javaVersion=21&dependencies=web,data-jpa,lombok,postgresql,cloud-feign"
echo.
echo Descargando ms-modelo.zip...
curl -o ms-modelo.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=ms-modelo&groupId=cl.nike&artifactId=cl-nike-modelo&name=tienda-modelo&description=servicio-modelo&packageName=cl.nike.modelo&packaging=jar&javaVersion=21&dependencies=web,data-jpa,lombok,postgresql,cloud-feign"
echo.
echo Descargando ms-categoria.zip...
curl -o ms-categoria.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=ms-categoria&groupId=cl.nike&artifactId=cl-nike-categoria&name=tienda-categoria&description=servicio-categoria&packageName=cl.nike.categoria&packaging=jar&javaVersion=21&dependencies=web,data-jpa,lombok,postgresql,cloud-feign"
echo.
echo Descargando ms-zapatilla.zip...
curl -o ms-zapatilla.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=ms-zapatilla&groupId=cl.nike&artifactId=cl-nike-zapatilla&name=tienda-zapatilla&description=servicio-zapatilla&packageName=cl.nike.zapatilla&packaging=jar&javaVersion=21&dependencies=web,data-jpa,lombok,postgresql,cloud-feign"
echo.
echo Descargando ms-cliente.zip...
curl -o ms-cliente.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=ms-cliente&groupId=cl.nike&artifactId=cl-nike-cliente&name=tienda-cliente&description=servicio-cliente&packageName=cl.nike.cliente&packaging=jar&javaVersion=21&dependencies=web,data-jpa,lombok,postgresql,cloud-feign"
echo.
echo Descargando ms-vendedor.zip...
curl -o ms-vendedor.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=ms-vendedor&groupId=cl.nike&artifactId=cl-nike-vendedor&name=tienda-vendedor&description=servicio-vendedor&packageName=cl.nike.vendedor&packaging=jar&javaVersion=21&dependencies=web,data-jpa,lombok,postgresql,cloud-feign"
echo.
echo Descargando ms-venta.zip...
curl -o ms-venta.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=ms-venta&groupId=cl.nike&artifactId=cl-nike-venta&name=tienda-venta&description=servicio-venta&packageName=cl.nike.venta&packaging=jar&javaVersion=21&dependencies=web,data-jpa,lombok,postgresql,cloud-feign"
echo.
echo Descargando ms-detallevta.zip...
curl -o ms-detallevta.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=ms-detallevta&groupId=cl.nike&artifactId=cl-nike-detallevta&name=tienda-detallevta&description=servicio-detallevta&packageName=cl.nike.detallevta&packaging=jar&javaVersion=21&dependencies=web,data-jpa,lombok,postgresql,cloud-feign"
echo.
echo Descargando ms-proveedor.zip...
curl -o ms-proveedor.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=ms-proveedor&groupId=cl.nike&artifactId=cl-nike-proveedor&name=tienda-proveedor&description=servicio-proveedor&packageName=cl.nike.proveedor&packaging=jar&javaVersion=21&dependencies=web,data-jpa,lombok,postgresql,cloud-feign"
echo.
echo Descargando ms-compra.zip...
curl -o ms-compra.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=ms-compra&groupId=cl.nike&artifactId=cl-nike-compra&name=tienda-compra&description=servicio-compra&packageName=cl.nike.compra&packaging=jar&javaVersion=21&dependencies=web,data-jpa,lombok,postgresql,cloud-feign"
echo.
echo Descarga completada.
pause
