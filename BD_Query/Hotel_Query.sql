CREATE DATABASE sistema_reservas_hotel;

USE sistema_reservas_hotel;

CREATE TABLE cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100),
    documento VARCHAR(20) UNIQUE NOT NULL,
    correo VARCHAR(100),
    telefono VARCHAR(20)
);

CREATE TABLE usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    usuario varchar(100),
    correo VARCHAR(100) UNIQUE,
    contrasena VARCHAR(255),
    rol ENUM('Recepcionista', 'Administrador')
);

CREATE TABLE habitacion (
    id_habitacion INT AUTO_INCREMENT PRIMARY KEY,
    numero INT UNIQUE NOT NULL,
    tipo ENUM('Simple', 'Lujo'),
    precio_base DECIMAL(10,2) NOT NULL,
    estado ENUM('Disponible', 'Ocupada', 'Mantenimiento')
);

CREATE TABLE decoracion_habitacion (
    id_decoracion INT AUTO_INCREMENT PRIMARY KEY,
    id_habitacion INT,
    servicio_extra VARCHAR(50),
    costo_adicional DECIMAL(10,2),
    FOREIGN KEY (id_habitacion) REFERENCES habitacion(id_habitacion) ON DELETE CASCADE
);

CREATE TABLE reserva (
    id_reserva INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT,
    id_habitacion INT,
    fecha_ingreso DATE,
    fecha_salida DATE,
    estado ENUM('Activa', 'Finalizada', 'Cancelada'),
    total_calculado DECIMAL(10,2),
    FOREIGN KEY (id_cliente) REFERENCES cliente(id),
    FOREIGN KEY (id_habitacion) REFERENCES habitacion(id_habitacion)
);

CREATE TABLE reserva_historial (
    id_historial INT AUTO_INCREMENT PRIMARY KEY,
    id_reserva INT,
    fecha_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    estado_anterior ENUM('Activa', 'Finalizada', 'Cancelada'),
    total_anterior DECIMAL(10,2),
    FOREIGN KEY (id_reserva) REFERENCES reserva(id_reserva)
);

CREATE TABLE pago (
    id_pago INT AUTO_INCREMENT PRIMARY KEY,
    id_reserva INT,
    metodo_pago VARCHAR(30),
    monto_pagado DECIMAL(10,2),
    fecha_pago DATE,
    FOREIGN KEY (id_reserva) REFERENCES reserva(id_reserva)
);

CREATE TABLE notificacion (
    id_notificacion INT AUTO_INCREMENT PRIMARY KEY,
    mensaje TEXT,
    tipo ENUM('Correo', 'Aplicacion'),
    fecha_envio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    id_usuario INT,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE log_actividad (
    id_log INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT,
    accion_realizada TEXT,
    tabla_afectada VARCHAR(50),
    fecha_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

-- Vista de habitaciones disponibles
CREATE VIEW vista_habitaciones_disponibles AS
SELECT id_habitacion, numero, tipo, precio_base
FROM habitacion
WHERE estado = 'Disponible';

-- Vista de reservas activas
CREATE VIEW vista_reservas_activas AS
SELECT r.id_reserva, c.nombre AS cliente, h.numero AS habitacion, r.fecha_ingreso, r.fecha_salida
FROM reserva r
JOIN cliente c ON r.id_cliente = c.id
JOIN habitacion h ON r.id_habitacion = h.id_habitacion
WHERE r.estado = 'Activa';
