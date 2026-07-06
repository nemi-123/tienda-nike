package cl.nike.cliente.service;

import cl.nike.cliente.dto.LoginRequest;
import cl.nike.cliente.dto.LoginResponse;
import cl.nike.cliente.model.Cliente;
import cl.nike.cliente.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import cl.nike.common.security.JwtTokenProvider;
import cl.nike.common.security.JwtProperties;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClienteAuthService {

    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;
    
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtProperties jwtProperties;

    @Transactional
    public LoginResponse login(LoginRequest request) {
        log.info("Intento de login para el cliente: {}", request.getEmail());

        // 1. Buscar cliente por email
        Cliente cliente = clienteRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> {
                    log.warn("Login fallido: Email no registrado -> {}", request.getEmail());
                    return new RuntimeException("Credenciales inválidas");
                });

        // 2. Validar contraseña
        if (!passwordEncoder.matches(request.getPassword(), cliente.getPassword())) {
            log.warn("Login fallido: Contraseña incorrecta para -> {}", request.getEmail());
            throw new RuntimeException("Credenciales inválidas");
        }

        log.info("Login exitoso para el cliente ID: {}", cliente.getIdCliente());

        // 3. Generar token real usando los parámetros que espera el proveedor
        String rol = "Cliente";
        String token = jwtTokenProvider.generarToken(
                cliente.getEmail(),
                rol,
                cliente.getNombre()
        );

        // 4. Construir respuesta con el token real y la expiración de la configuración
        return LoginResponse.builder()
                .token(token)
                .email(cliente.getEmail())
                .nombre(cliente.getNombre())
                .rol(rol)
                .expiresIn(jwtProperties.getExpirationMs())
                .build();
    }
}