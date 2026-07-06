package cl.nike.common.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FeignClientInterceptor implements RequestInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Override
    public void apply(RequestTemplate template) {
        // Capturar la petición HTTP original que ingresó a este microservicio
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            String token = request.getHeader(AUTHORIZATION_HEADER);

            // Si el header "Authorization" existe en la petición actual, lo clonamos en Feign
            if (token != null && !token.isEmpty()) {
                template.header(AUTHORIZATION_HEADER, token);
                log.debug("Token JWT propagado exitosamente en llamada Feign a: {}", template.url());
            } else {
                log.warn("No se encontró el header Authorization en la petición original para enviar a: {}", template.url());
            }
        }
    }
}