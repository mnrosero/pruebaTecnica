# Bank Microservices - Complete Package

Contenido:
- ms-clientes (Spring Boot)
- ms-cuentas (Spring Boot)
- docker-compose.yml (Postgres + RabbitMQ + ambos servicios)
- BaseDatos.sql
- BankAPI.postman_collection.json
- scripts para build

## Cómo ejecutar (con Docker)
1. Construye los JARs (opcional si quieres usar imágenes locales):
   ```bash
   ./build-and-package.sh
   ```
2. Levanta el stack con Docker Compose:
   ```bash
   docker-compose up --build
   ```
3. Endpoints disponibles:
   - Clientes: POST http://localhost:8081/api/v1/clientes, GET /api/v1/clientes/{clienteId}
   - Cuentas: POST http://localhost:8082/api/v1/cuentas, GET /api/v1/cuentas/{numeroCuenta}
   - Movimientos: POST http://localhost:8082/api/v1/movimientos

## Pruebas incluidas
- Test unitario: ms-clientes/src/test... ClienteControllerTest
- Test unitario: ms-cuentas/src/test... MovimientoServiceTest

## Postman
Importa `BankAPI.postman_collection.json` en Postman y ajusta las URLs si cambias puertos.

## Base de datos
El archivo `BaseDatos.sql` contiene las instrucciones de creación de tablas si prefieres crear la BD manualmente.

