package cl.nike.common.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * Propiedades de configuración para JWT de la tienda.
 *
 * Se mapean automáticamente desde el archivo application.yml de cada microservicio:
 *
 * jwt:
 * secret: "dG9rZW5TZWNyZXRLZXlCaWJsaW90ZWNhMjAyNlRyaXNrZWxlZHVTZWN1cml0eQ=="
 * expiration-ms: 3600000
 *
 * La anotación @ConfigurationProperties vincula de forma dinámica el prefijo "jwt" 
 * a las variables de esta clase al iniciar la aplicación.
 */
@Component
@ConfigurationProperties(prefix = "jwt")
@Getter
@Setter
public class JwtProperties {

    /**
     * Clave secreta en Base64 utilizada para firmar y verificar tokens JWT.
     * Esta frase debe ser idéntica a lo largo de todo el ecosistema de microservicios.
     */
    private String secret;

    /**
     * Tiempo de expiración del token en milisegundos.
     * Si no se especifica en el YAML, tomará el valor por defecto: 3600000 ms (1 hora).
     */
    private long expirationMs = 3600000;
}