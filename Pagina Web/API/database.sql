-- Crear base de datos
CREATE DATABASE IF NOT EXISTS my_fit_arena_db;

-- Crear tabla de tickets de soporte
CREATE TABLE IF NOT EXISTS tickets (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    asunto VARCHAR(200) NOT NULL,
    mensaje TEXT NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Insertar algunos datos de ejemplo
INSERT INTO tickets (nombre, email, asunto, mensaje) VALUES
('Juan Pérez', 'juan@example.com', 'Consulta sobre horarios', '¿Cuál es el horario de apertura del gimnasio?'),
('María García', 'maria@example.com', 'Problema con mi rutina', 'No puedo acceder a mi plan de entrenamiento'),
('Carlos López', 'carlos@example.com', 'Sugerencia', 'Sería genial tener más clases de yoga'),
('Ana Martínez', 'ana@example.com', 'Soporte técnico', 'La calculadora IMC no funciona correctamente');