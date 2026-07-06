package cl.nike.detallevta.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// IMPORT REQUERIDO AGREGADO Y CORREGIDO
import cl.nike.common.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;

/**
 * Configuración de Spring Security para ms-detallevta.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class Securityconfig {

    // SE CORRIGIÓ EL TIPO DE LA CLASE (Estaba en minúsculas: jwtAuthenticationFilter)
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authorizeHttpRequests(auth -> auth

                // [SWAGGER-INI] PERMITIR RUTAS PÚBLICAS DE SWAGGER / SPRINGDOC
                .requestMatchers(
                    "/v3/api-docs/**",
                    "/v3/api-docs.yaml",
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/swagger-resources/**",
                    "/webjars/**",
                    "/favicon.ico"
                ).permitAll()
                // [SWAGGER-FIN]

                // Actuator siempre público
                .requestMatchers("/actuator/**").permitAll()

                // Consulta de detalles de venta: Accesible por todos los roles autenticados
                .requestMatchers(HttpMethod.GET, "/api/v1/detallevta/**")
                    .hasAnyRole("Administrador", "Nikerio", "Cliente")

                // Registro de detalles de venta: Típicamente por Administradores o Clientes al comprar
                .requestMatchers(HttpMethod.POST, "/api/v1/detallevta/**")
                    .hasAnyRole("Administrador", "Cliente")

                // Modificaciones y eliminaciones restringidas a Administradores
                .requestMatchers(HttpMethod.PUT, "/api/v1/detallevta/**")
                    .hasAnyRole("Administrador")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/detallevta/**")
                    .hasAnyRole("Administrador")

                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}