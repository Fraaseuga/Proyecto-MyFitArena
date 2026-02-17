-- Consultas de una tabla
-- 1. Mostrar todos los usuarios
SELECT * FROM USUARIO;

-- 2. Mostrar todos los entrenamientos de dificultad 'Media'
SELECT * FROM ENTRENAMIENTO WHERE dificultad = 'Media';

-- 3. Mostrar todos los clubes creados después de 2020
SELECT * FROM CLUB WHERE to_char(fechaCreacion,'YYYY') > '2020';

-- 4. Mostrar todos los ejercicios con más de 10 repeticiones
SELECT * FROM EJERCICIO WHERE repeticiones > 10;

-- 5. Mostrar todos los anuncios publicados en 2024
SELECT * FROM ANUNCIO WHERE to_char(fecha_publicacion,'YYYY') = '2024';

-- -------------------------------------------------------------------------------------------------------------
-- 2 Actualizaciones y 2 Borrados en cualquier tabla
-- Actualización 1: Cambiar el teléfono de un usuario
UPDATE USUARIO
SET telefono = '611222333'
WHERE DNI = '11111111A';

-- Actualización 2: Aumentar la duración de un entrenamiento
UPDATE ENTRENAMIENTO
SET duracion = duracion + 5
WHERE CodEntrenamiento = 'T004';

-- Borrado 1: Eliminar un anuncio concreto
DELETE FROM ANUNCIO
WHERE codAnuncio = 'A009';

-- Borrado 2: Eliminar un evento concreto
DELETE FROM RECOMENDACIONES
WHERE codRecomendacion = 'R004';

-- -------------------------------------------------------------------------------------------------------------
-- 3 Consultas con más de 1 tabla
-- 1. Mostrar usuarios con su club
SELECT u.nombre, u.apellidos, c.nombre AS club
FROM USUARIO u
JOIN CLUB c ON u.cod_club = c.CodClub;

-- 2. Mostrar eventos con su anuncio asociado
SELECT e.tipo, e.descripcion, a.titulo
FROM EVENTO e
JOIN TENER t ON e.codEvento = t.cod_evento
JOIN ANUNCIO a ON t.cod_anuncio = a.codAnuncio;

-- 3. Mostrar usuarios y los entrenamientos que realizan
SELECT u.nombre, u.apellidos, e.descripcion
FROM REALIZAR r
JOIN USUARIO u ON r.dni_usuario = u.DNI
JOIN ENTRENAMIENTO e ON r.cod_entrenamiento = e.CodEntrenamiento;

-- -------------------------------------------------------------------------------------------------------------
-- 3 Consultas usando funciones
-- 1. Calcular la media de calorías de los planes alimenticios
SELECT AVG(calorias_diarias) AS media_calorias
FROM PLAN_ALIMENTICIO;

-- 2. Obtener el número total de usuarios
SELECT COUNT(*) AS total_usuarios
FROM USUARIO;

-- 3. Obtener la fecha más reciente de creación de clubes
SELECT MAX(fechaCreacion) AS club_mas_reciente
FROM CLUB;

-- -------------------------------------------------------------------------------------------------------------
-- 2 Consultas usando group by
-- 1. Número de usuarios por club
SELECT cod_club, COUNT(*) AS total_usuarios
FROM USUARIO
GROUP BY cod_club;

-- 2. Número de eventos por tipo
SELECT tipo, COUNT(*) AS total_eventos
FROM EVENTO
GROUP BY tipo;

-- -------------------------------------------------------------------------------------------------------------
-- 2 Consultas utilizando subconsultas
-- 1. Usuarios cuyo plan tiene más calorías que la media
SELECT nombre, apellidos
FROM USUARIO
WHERE codPlan IN (
    SELECT CodPlan
    FROM PLAN_ALIMENTICIO
    WHERE calorias_diarias > (SELECT AVG(calorias_diarias) FROM PLAN_ALIMENTICIO)
);

-- 2. Entrenamientos realizados por usuarios del club C003
SELECT cod_entrenamiento
FROM REALIZAR
WHERE dni_usuario IN (
    SELECT DNI FROM USUARIO WHERE cod_club = 'C003'
);

-- -------------------------------------------------------------------------------------------------------------
-- 2 Consultas usando group by con having
-- 1. Clubs con más de 80 miembros
SELECT nombre, num_miembros
FROM CLUB
GROUP BY nombre, num_miembros
HAVING num_miembros > 80;

-- 2. Tipos de evento con más de un evento
SELECT tipo, COUNT(*) AS total
FROM EVENTO
GROUP BY tipo
HAVING COUNT(*) > 1;

-- -------------------------------------------------------------------------------------------------------------
-- 3 actualizaciones usando subconsultas en where y set.
-- 1. Aumentar un 10% las calorías del plan con más proteínas
UPDATE PLAN_ALIMENTICIO
SET calorias_diarias = calorias_diarias * 1.10
WHERE proteinas = (
    SELECT MAX(proteinas)
    FROM PLAN_ALIMENTICIO
);

-- 2. Cambiar el club de los usuarios cuyo plan tiene menos calorías que la media
UPDATE USUARIO
SET cod_club = 'C010'
WHERE codPlan IN (
    SELECT CodPlan
    FROM PLAN_ALIMENTICIO
    WHERE calorias_diarias < (SELECT AVG(calorias_diarias) FROM PLAN_ALIMENTICIO)
);

-- 3. Aumentar la duración del entrenamiento más largo de dificultad 'Difícil'
UPDATE ENTRENAMIENTO
SET duracion = duracion + 10
WHERE duracion = (
    SELECT MAX(duracion)
    FROM ENTRENAMIENTO
    WHERE dificultad = 'Difícil'
);
