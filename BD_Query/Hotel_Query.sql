

-- USUARIOS (autenticación)
CREATE TABLE usuario (
    id_usuario SERIAL PRIMARY KEY,
    nombre VARCHAR(100),
    correo VARCHAR(100) UNIQUE,
    contrasena VARCHAR(255),
    rol VARCHAR(20) CHECK (rol IN ('Recepcionista', 'Administrador'))
);

-- CLIENTES
CREATE TABLE cliente (
    id_cliente SERIAL PRIMARY KEY,
    nombre VARCHAR(100),
    dni VARCHAR(20) UNIQUE,
    telefono VARCHAR(20),
    email VARCHAR(100)
);

-- HABITACIONES
CREATE TABLE habitacion (
    id_habitacion SERIAL PRIMARY KEY,
    numero INT UNIQUE NOT NULL,
    tipo VARCHAR(20) CHECK (tipo IN ('Simple', 'Lujo')),
    precio_base DECIMAL(10,2) NOT NULL,
    estado VARCHAR(20) CHECK (estado IN ('Disponible', 'Ocupada', 'Mantenimiento'))
);

-- SERVICIOS EXTRAS (Decorador)
CREATE TABLE decoracion_habitacion (
    id_decoracion SERIAL PRIMARY KEY,
    id_habitacion INT REFERENCES habitacion(id_habitacion) ON DELETE CASCADE,
    servicio_extra VARCHAR(50),
    costo_adicional DECIMAL(10,2)
);

-- RESERVAS
CREATE TABLE reserva (
    id_reserva SERIAL PRIMARY KEY,
    id_cliente INT REFERENCES cliente(id_cliente),
    id_habitacion INT REFERENCES habitacion(id_habitacion),
    fecha_ingreso DATE,
    fecha_salida DATE,
    estado VARCHAR(20) CHECK (estado IN ('Activa', 'Finalizada', 'Cancelada')),
    total_calculado DECIMAL(10,2)
);

-- HISTORIAL DE RESERVA (Memento)
CREATE TABLE reserva_historial (
    id_historial SERIAL PRIMARY KEY,
    id_reserva INT REFERENCES reserva(id_reserva),
    fecha_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    estado_anterior VARCHAR(20),
    total_anterior DECIMAL(10,2)
);

-- PAGOS
CREATE TABLE pago (
    id_pago SERIAL PRIMARY KEY,
    id_reserva INT REFERENCES reserva(id_reserva),
    metodo_pago VARCHAR(30),
    monto_pagado DECIMAL(10,2),
    fecha_pago DATE
);

-- NOTIFICACIONES (Observer)
CREATE TABLE notificacion (
    id_notificacion SERIAL PRIMARY KEY,
    mensaje TEXT,
    tipo VARCHAR(30) CHECK (tipo IN ('Correo', 'Aplicacion')),
    fecha_envio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    id_usuario INT REFERENCES usuario(id_usuario)
);

-- LOG DE ACTIVIDAD (Observer / Auditoría)
CREATE TABLE log_actividad (
    id_log SERIAL PRIMARY KEY,
    id_usuario INT REFERENCES usuario(id_usuario),
    accion_realizada TEXT,
    tabla_afectada VARCHAR(50),
    fecha_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Vista de habitaciones disponibles
CREATE VIEW vista_habitaciones_disponibles AS
SELECT id_habitacion, numero, tipo, precio_base
FROM habitacion
WHERE estado = 'Disponible';

-- Vista de reservas activas con datos de cliente
CREATE VIEW vista_reservas_activas AS
SELECT r.id_reserva, c.nombre AS cliente, h.numero AS habitacion, r.fecha_ingreso, r.fecha_salida
FROM reserva r
JOIN cliente c ON r.id_cliente = c.id_cliente
JOIN habitacion h ON r.id_habitacion = h.id_habitacion
WHERE r.estado = 'Activa';

-- Total de ingresos por reservas finalizadas
SELECT SUM(total_calculado) AS total_ingresos
FROM reserva
WHERE estado = 'Finalizada';

-- Historial de cambios en una reserva específica
SELECT * FROM reserva_historial
WHERE id_reserva = 1
ORDER BY fecha_modificacion DESC;
