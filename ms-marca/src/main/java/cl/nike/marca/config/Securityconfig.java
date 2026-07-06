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
 * Matriz de autorización:
 * ┌──────────────────────────────────┬────────┬────────────────────────────────────────────────┐
 * │ Endpoint                         │ Método │ Acceso                                         │
 * ├──────────────────────────────────┼────────┼────────────────────────────────────────────────┤
 * │ /actuator/**                     │ ALL    │ Público (monitoreo)                            │
 * │ /api/v1/marcas/**                │ GET    │ Público / Autenticado (Cualquier rol)          │
 * │ /api/v1/marcas/**                │ POST   │ Administrador                                  │
 * │ /api/v1/marcas/**                │ PUT    │ Administrador                                  │
 * │ /api/v1/marcas/**                │ DELETE │ Administrador                                  │
 * └──────────────────────────────────┴────────┴────────────────────────────────────────────────┘
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

                // Listar o ver marcas: Accesible por cualquier usuario autenticado (Clientes, Administradores, etc.)
                .requestMatchers(HttpMethod.GET, "/api/v1/marcas/**")
                    .hasAnyRole("Administrador", "Nikerio", "Cliente")

                // Mantenedores de Marcas (Crear, Modificar, Eliminar): Exclusivo Administradores
                .requestMatchers(HttpMethod.POST, "/api/v1/marcas/**").hasAnyRole("Administrador")
                .requestMatchers(HttpMethod.PUT, "/api/v1/marcas/**").hasAnyRole("Administrador")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/marcas/**").hasAnyRole("Administrador")

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