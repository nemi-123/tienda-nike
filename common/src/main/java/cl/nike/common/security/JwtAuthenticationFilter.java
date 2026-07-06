package cl.nike.common.security;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.lang.NonNull;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// Se añade la importación de Claims para poder manejar el retorno de tu JwtTokenProvider
import io.jsonwebtoken.Claims;

/**
 * Filtro de autenticación JWT que se ejecuta UNA VEZ por cada petición HTTP entrante.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        try {
            // 1. Extraer el token del header Authorization
            String token = extraerToken(request);

            // 2. Validar y autenticar solo si existe un token
            if (StringUtils.hasText(token)) {
                
                // Usamos el método unificado que ya definiste en tu JwtTokenProvider
                Claims claims = jwtTokenProvider.extraerYValidarClaims(token);

                // Si el token es válido y no ha expirado, claims no será null
                if (claims != null) {

                    // 3. Extraer los datos desde el Payload de los Claims
                    String email = claims.getSubject(); // El subject almacena el email en tu provider
                    String rol = claims.get("rol", String.class);

                    // 4. Crear authorities con prefijo ROLE_ (convención de Spring Security)
                    List<SimpleGrantedAuthority> authorities = List.of(
                        new SimpleGrantedAuthority("ROLE_" + rol)
                    );

                    // 5. Crear objeto de autenticación guardando el token en credentials
                    UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(email, token, authorities);

                    // 6. Inyectar en el SecurityContext
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    log.debug("Usuario autenticado en contexto: {} con rol: {}", email, rol);
                }
            }

        } catch (Exception e) {
            log.error("Error al procesar el token JWT en el filtro: {}", e.getMessage());
            SecurityContextHolder.clearContext();
        }

        // 7. Continuar con la cadena de filtros
        filterChain.doFilter(request, response);
    }

    /**
     * Extrae el token JWT del header Authorization.
     */
    private String extraerToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(BEARER_PREFIX.length());
        }
        return null;
    }
}