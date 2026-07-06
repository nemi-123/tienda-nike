package cl.nike.cliente.controller;

import cl.nike.cliente.dto.ClienteRequest;
import cl.nike.cliente.dto.ClienteResponse;
import cl.nike.cliente.dto.LoginRequest;
import cl.nike.cliente.dto.LoginResponse;
import cl.nike.cliente.service.ClienteAuthService; // Importación añadida
import cl.nike.cliente.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador de autenticación (público, sin requerir token JWT).
 *
 * Endpoints:
 * - POST /api/v1/auth/login    → Iniciar sesión, obtener token JWT simulado
 * - POST /api/v1/auth/register → Registrar nuevo cliente en la plataforma Nike
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    // Se inyectan ambos servicios mediante el constructor generado por @RequiredArgsConstructor
    private final ClienteAuthService clienteAuthService; 
    private final ClienteService clienteService;

    /**
     * Endpoint de inicio de sesión.
     *
     * Recibe email y contraseña, valida las credenciales en ClienteAuthService 
     * y retorna un token JWT simulado.
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        // Corregido: Se utiliza clienteAuthService que posee la lógica del método login
        LoginResponse response = clienteAuthService.login(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint de registro público.
     *
     * Permite a cualquier persona crear una cuenta de cliente utilizando ClienteService.
     */
    @PostMapping("/register")
    public ResponseEntity<ClienteResponse> register(@Valid @RequestBody ClienteRequest request) {
        ClienteResponse response = clienteService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}