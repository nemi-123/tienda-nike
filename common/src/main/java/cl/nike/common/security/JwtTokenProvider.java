package cl.nike.common.security;

import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;

    // ─── Generación ──────────────────────────────────────────────────────────
    public String generarToken(String email, String rol, String nombreCompleto) {
        Date ahora = new Date();
        Date expiracion = new Date(ahora.getTime() + jwtProperties.getExpirationMs());

        return Jwts.builder()
                .subject(email)
                .claim("rol", rol)
                .claim("nombre", nombreCompleto)
                .issuedAt(ahora)
                .expiration(expiracion)
                .signWith(getSigningKey())
                .compact();
    }

    // ─── Validación y Extracción combinada ────────────────────────────────────
    
    /**
     * Parsea y valida el token. Si es válido, retorna sus Claims.
     * Si es inválido o expiró, retorna null.
     */
    public Claims extraerYValidarClaims(String token) {
        try {
            return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload(); // Retorna los Claims directamente si pasa la firma y expiración
        } catch (JwtException | IllegalArgumentException e) {
            log.warn("Token JWT inválido o expirado: {}", e.getMessage());
            return null;
        }
    }

    // ─── Métodos privados ────────────────────────────────────────────────────
    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.getSecret());
        return Keys.hmacShaKeyFor(keyBytes);
    }
}