package cl.nike.compra.config;

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

// IMPORT FIJO: Limpiado de caracteres extraños y errores tipográficos (*;. y nike)
import cl.nike.common.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;

/**
 * Configuración de Spring Security para ms-compra.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class Securityconfig {

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

                // Historial/Detalle de compras: visible por cualquier rol autenticado
                .requestMatchers(HttpMethod.GET, "/api/v1/compras/**")
                    .hasAnyRole("Administrador", "Bibliotecario", "Cliente")

                // Generar una compra: típicamente Clientes y Administradores
                .requestMatchers(HttpMethod.POST, "/api/v1/compras/**")
                    .hasAnyRole("Administrador", "Cliente")

                // Modificaciones/Cancelaciones de compras (si aplican)
                .requestMatchers(HttpMethod.PUT, "/api/v1/compras/**")
                    .hasAnyRole("Administrador")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/compras/**")
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