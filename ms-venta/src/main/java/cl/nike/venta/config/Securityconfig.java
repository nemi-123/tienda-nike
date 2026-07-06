package cl.nike.venta.config;

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
 * Configuración de Spring Security para ms-venta.
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

                // Historial o consulta de ventas: Accesible por los roles correspondientes
                .requestMatchers(HttpMethod.GET, "/api/v1/ventas/**")
                    .hasAnyRole("Administrador", "Bibliotecario", "Cliente")

                // Generar una venta (Checkout): Permitido para Clientes y Administradores
                .requestMatchers(HttpMethod.POST, "/api/v1/ventas/**")
                    .hasAnyRole("Administrador", "Cliente")

                // Modificaciones profundas o anulaciones de ventas restringidas a Administradores
                .requestMatchers(HttpMethod.PUT, "/api/v1/ventas/**").hasAnyRole("Administrador")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/ventas/**").hasAnyRole("Administrador")

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