/* ============================================================
   ARCHIVO: 03-cliente.sql
   Microservicio: clientes
   Responsabilidad: administrar cuentas de clientes, perfiles, ciudades y compras.
   Kafka publica eventos ClienteCreado/ClienteActualizado para proyecciones.
   ============================================================ */
\c cliente;

-- 1. ELIMINACIÓN EN JERARQUÍA INVERSA
DROP TABLE IF EXISTS compracli;
DROP TABLE IF EXISTS credenciales_clientes;
DROP TABLE IF EXISTS perfil_clientes;
DROP TABLE IF EXISTS cliente;
DROP TABLE IF EXISTS ciudad;

-- 2. TABLAS MAESTRAS
CREATE TABLE ciudad (
    id_ciudad         SERIAL       PRIMARY KEY,
    nombre            VARCHAR(150) NOT NULL
);

CREATE TABLE cliente (
    id_cliente        SERIAL       PRIMARY KEY,
    nombre            VARCHAR(150) NOT NULL,
    email             VARCHAR(150) UNIQUE NOT NULL,
    -- [JJWT] Se debe aumentar el tamaño de la password para hashes BCrypt
    password          VARCHAR(255) NOT NULL,
    id_ciudad         INT          NOT NULL REFERENCES ciudad(id_ciudad) ON DELETE CASCADE,
    activo            BOOLEAN      DEFAULT TRUE
);

CREATE TABLE perfil_clientes (
    id                SERIAL       PRIMARY KEY,
    id_cliente        INT          UNIQUE NOT NULL REFERENCES cliente(id_cliente) ON DELETE CASCADE,
    telefono          VARCHAR(30),
    fecha_registro    DATE         NOT NULL DEFAULT CURRENT_DATE
);

CREATE TABLE credenciales_clientes (
    id                SERIAL       PRIMARY KEY,
    id_cliente        INT          UNIQUE NOT NULL REFERENCES cliente(id_cliente) ON DELETE CASCADE,
    ultimo_acceso     TIMESTAMP,
    bloqueado         BOOLEAN      NOT NULL DEFAULT FALSE,
    intentos_fallidos INT          NOT NULL DEFAULT 0 CHECK (intentos_fallidos >= 0)
);

CREATE TABLE compracli (
    id_compra         SERIAL       PRIMARY KEY,
    id_cliente        INT          NOT NULL REFERENCES cliente(id_cliente) ON DELETE CASCADE,
    fecha             DATE         NOT NULL DEFAULT CURRENT_DATE,
    total             NUMERIC      NOT NULL CHECK (total >= 0)
);

CREATE INDEX idx_cliente_ciudad ON cliente(id_ciudad);
CREATE INDEX idx_cliente_activo ON cliente(activo);
CREATE INDEX idx_compracli_cliente ON compracli(id_cliente);

-- 3. DATOS DE PRUEBA
INSERT INTO ciudad (nombre) VALUES 
('santiago'),
('valparaiso'),
('concepcion');

-- [JJWT-INI] 
-- La contraseña por defecto fue configurada como 'Biblio@2026' para todos los usuarios, y está almacenada como 
-- hash (huella digital) utilizando el algoritmo BCrypt (generado con BCryptPasswordEncoder de Spring Security).
INSERT INTO cliente (nombre, email, password, id_ciudad) VALUES
('juan',  'juan@cliente.cl',  '$2b$10$1hnbaMR7iTsdn3D0gG5Q8eUw5aSh9O2at2e4u1iAlzdhD6m4dzVZO', 1),
('ana',   'ana@cliente.cl',   '$2b$10$1hnbaMR7iTsdn3D0gG5Q8eUw5aSh9O2at2e4u1iAlzdhD6m4dzVZO', 2),
('pedro', 'pedro@cliente.cl', '$2b$10$1hnbaMR7iTsdn3D0gG5Q8eUw5aSh9O2at2e4u1iAlzdhD6m4dzVZO', 3);
-- [JJWT-FIN]

INSERT INTO perfil_clientes (id_cliente, telefono) VALUES
(1, '123456789'),
(2, '987654321'),
(3, '456123789');

INSERT INTO credenciales_clientes (id_cliente, ultimo_acceso, bloqueado, intentos_fallidos) VALUES
(1, NOW(), FALSE, 0),
(2, NOW(), FALSE, 0),
(3, NULL,  FALSE, 0);

INSERT INTO compracli (id_cliente, fecha, total) VALUES
(1, '2024-01-01', 50000),
(2, '2024-01-05', 70000),
(3, '2024-01-10', 60000);