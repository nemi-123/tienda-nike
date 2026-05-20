-- ES FUNDAMENTAL EJECUTAR ESTE SCRIPT QUE PERMITE ELIMINAR LAS BASES DE DATOS
-- SI ES QUE EXISTEN, PARA LUEGO CREARLAS LIMPIAS SIN TABLAS Y DESDE CERO

SELECT 'CREATE DATABASE categoria'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'categoria') \gexec


SELECT 'CREATE DATABASE cliente'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'cliente') \gexec


SELECT 'CREATE DATABASE compra'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'compra') \gexec


SELECT 'CREATE DATABASE detallevta'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'detallevta') \gexec


SELECT 'CREATE DATABASE marca'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'marca') \gexec


SELECT 'CREATE DATABASE modelo'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'modelo') \gexec


SELECT 'CREATE DATABASE proveedor'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'proveedor') \gexec


SELECT 'CREATE DATABASE vendedor'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'vendedor') \gexec


SELECT 'CREATE DATABASE venta'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'venta') \gexec


SELECT 'CREATE DATABASE zapatilla'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'zapatilla') \gexec

