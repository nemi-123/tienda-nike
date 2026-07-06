package cl.nike.marca.config;

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

import cl.nike.common.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;

/**
 * Configuración de Spring Security para ms-marca.
 *
 * Este microservicio actúa como RESOURCE SERVER:
 * - Valida tokens JWT en cada petición usando el filtro compartido.
 * - Aplica reglas de autorización según el rol del usuario para el mantenimiento de marcas.
 *
 * Matriz de autorización:
 * ┌──────────────────────────────────┬────────┬────────────────────────────────────────────────┐
 * │ Endpoint                         │ Método │ Acceso                                         │
 * ├──────────────────────────────────┼────────┼────────────────────────────────────────────────┤
 * │ /actuator/** │ ALL    │ Público (monitoreo)                            │
 * │ /api/v1/marcas/** │ GET    │ Administrador, Nikerio, Cliente          │
 * │ /api/v1/marcas/** │ POST   │ Administrador                                  │
 * │ Cualquier otro                   │ ALL    │ Autenticado (con token válido)                 │
 * └──────────────────────────────────┴────────┴────────────────────────────────────────────────┘
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

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

                // Consulta de marcas: Accesible por todos los roles autenticados (catálogo)
                .requestMatchers(HttpMethod.GET, "/api/v1/marcas/**")
                    .hasAnyRole("Administrador", "Nikerio", "Cliente")

                // Modificaciones, creación y eliminación restringidas únicamente al rol Administrador
                .requestMatchers(HttpMethod.POST, "/api/v1/marcas/**")
                    .hasAnyRole("Administrador")
                .requestMatchers(HttpMethod.PUT, "/api/v1/marcas/**")
                    .hasAnyRole("Administrador")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/marcas/**")
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