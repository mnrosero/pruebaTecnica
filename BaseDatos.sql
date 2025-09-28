-- ===================================================
-- Script de creación de base de datos del ejercicio
-- Prueba Técnica NTTDATA - Microservicios
-- ===================================================

-- ==================
-- TABLA PERSONA
-- ==================
CREATE TABLE persona (
                         identificacion VARCHAR(50) PRIMARY KEY,
                         nombre         VARCHAR(100) NOT NULL,
                         genero         CHAR(1),
                         edad           INT,
                         direccion      VARCHAR(200),
                         telefono       VARCHAR(50)
);

-- ==================
-- TABLA CLIENTE
-- ==================
CREATE TABLE cliente (
                         cliente_id     VARCHAR(50) PRIMARY KEY,
                         contrasenia    VARCHAR(100) NOT NULL,
                         estado         BOOLEAN DEFAULT TRUE,
                         identificacion VARCHAR(50) NOT NULL,
                         CONSTRAINT fk_cliente_persona FOREIGN KEY (identificacion)
                             REFERENCES persona(identificacion)
);

-- ==================
-- TABLA CUENTAS
-- ==================
CREATE TABLE cuentas (
                         id             SERIAL PRIMARY KEY,
                         numero_cuenta  VARCHAR(50) UNIQUE NOT NULL,
                         tipo_cuenta    VARCHAR(50) NOT NULL,
                         saldo_inicial  NUMERIC(15,2) NOT NULL DEFAULT 0,
                         estado         BOOLEAN DEFAULT TRUE,
                         cliente_id     VARCHAR(50) NOT NULL,
                         CONSTRAINT fk_cuenta_cliente FOREIGN KEY (cliente_id)
                             REFERENCES cliente(cliente_id)
);

-- ==================
-- TABLA MOVIMIENTOS
-- ==================
CREATE TABLE movimientos (
                             id              SERIAL PRIMARY KEY,
                             cuenta_id       INT NOT NULL,
                             fecha           TIMESTAMP NOT NULL,
                             tipo_movimiento VARCHAR(20) NOT NULL,
                             valor           NUMERIC(15,2) NOT NULL,
                             saldo           NUMERIC(15,2) NOT NULL,
                             CONSTRAINT fk_movimiento_cuenta FOREIGN KEY (cuenta_id)
                                 REFERENCES cuentas(id)
);

-- ==================
-- DATOS DE EJEMPLO
-- ==================
INSERT INTO persona (identificacion, nombre, genero, edad, direccion, telefono)
VALUES
    ('ID001', 'Jose Lema', 'M', 34, 'Otavalo sn y principal', '098254785'),
    ('ID002', 'Marianela Montalvo', 'F', 28, 'Amazonas y NNUU', '097548965'),
    ('ID003', 'Juan Osorio', 'M', 40, '13 junio y Equinoccial', '098874587');

INSERT INTO cliente (cliente_id, contrasenia, estado, identificacion)
VALUES
    ('jose.lema', '1234', TRUE, 'ID001'),
    ('marianela.montalvo', '5678', TRUE, 'ID002'),
    ('juan.osorio', '9999', TRUE, 'ID003');

INSERT INTO cuentas (numero_cuenta, tipo_cuenta, saldo_inicial, estado, cliente_id)
VALUES
    ('478758', 'Ahorro',    2000, TRUE, 'jose.lema'),
    ('225487', 'Corriente',  100, TRUE, 'jose.lema'),
    ('495878', 'Ahorro',       0, TRUE, 'marianela.montalvo'),
    ('496825', 'Ahorro',     540, TRUE, 'juan.osorio');

INSERT INTO movimientos (cuenta_id, fecha, tipo_movimiento, valor, saldo)
VALUES
    (1, '2022-02-08 10:00:00', 'DEPOSITO', 600, 2600),
    (1, '2022-02-10 09:30:00', 'RETIRO',   575, 2025),
    (4, '2022-02-08 15:00:00', 'DEPOSITO', 150, 690);
