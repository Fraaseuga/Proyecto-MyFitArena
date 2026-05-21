-- VISTAS
-- Vista para obtener los usuarios con el club al que pertencen
CREATE VIEW V_USUARIOS_CLUB AS
SELECT
    U.DNI,
    U.nombre,
    U.apellidos,
    U.cod_club,
    C.nombre AS nombre_club
FROM USUARIO U, CLUB C
WHERE U.cod_club = C.CodClub;

-- Vista para obtener los entrenamientos realizados por un usuario
CREATE VIEW V_USUARIO_ENTRENAMIENTO AS
SELECT
    U.DNI,
    U.nombre,
    R.cod_entrenamiento,
    E.dificultad,
    E.descripcion
FROM USUARIO U, REALIZAR R, ENTRENAMIENTO E
WHERE U.DNI = R.dni_usuario
AND R.cod_entrenamiento = E.CodEntrenamiento;

-- FUNCIONES NORMALES
-- Esta función comprueba si un usuario es propietario de un club
CREATE OR REPLACE FUNCTION ES_PROPIETARIO(
    p_dni VARCHAR,
    p_codclub VARCHAR
)
RETURNS BOOLEAN AS $$
DECLARE
    res BOOLEAN := FALSE;
BEGIN

    SELECT TRUE INTO res
    FROM CLUB
    WHERE CodClub = p_codclub
    AND dni_propietario = p_dni;

    IF res IS NULL THEN
        RETURN FALSE;
    END IF;

    RETURN res;
END;
$$ LANGUAGE plpgsql;

-- Función para obtener el número total de entrenamientos realizados de un usuario
CREATE OR REPLACE FUNCTION CONTAR_ENTRENAMIENTOS(p_dni VARCHAR)
RETURNS INTEGER AS $$
DECLARE
    total INTEGER;
BEGIN

    SELECT COUNT(*) INTO total
    FROM REALIZAR
    WHERE dni_usuario = p_dni;

    RETURN total;
END;
$$ LANGUAGE plpgsql;

-- Función para listar todos los entrenamientos de un usuario
CREATE OR REPLACE FUNCTION LISTAR_ENTRENAMIENTOS(p_dni VARCHAR)
RETURNS TEXT AS $$
DECLARE
    r RECORD;
    resultado TEXT := '';
    cur CURSOR FOR
        SELECT cod_entrenamiento
        FROM REALIZAR
        WHERE dni_usuario = p_dni;
BEGIN

    OPEN cur;

    LOOP
        FETCH cur INTO r;
        EXIT WHEN NOT FOUND;

        resultado := resultado || r.cod_entrenamiento || ' ';
    END LOOP;

    CLOSE cur;

    RETURN resultado;
END;
$$ LANGUAGE plpgsql;

-- Función que devuelve el nivel de progreso actual de un usario
CREATE OR REPLACE FUNCTION NIVEL_PROGRESO(p_dni VARCHAR)
RETURNS TEXT AS $$
DECLARE
    nivel TEXT;
BEGIN

    SELECT nivel INTO nivel
    FROM PROGRESO
    WHERE dni_usuario = p_dni
    LIMIT 1;

    RETURN nivel;
END;
$$ LANGUAGE plpgsql;

-- TRIGGERS
-- Trigger para actualizar la cantidad de miembros de los clubs cuando se modifican,insertan o eliminan un usario
CREATE OR REPLACE FUNCTION actualizar_num_miembros()
RETURNS TRIGGER AS $$
BEGIN
    IF (TG_OP = 'INSERT') THEN
        IF NEW.cod_club IS NOT NULL THEN
            UPDATE CLUB
            SET num_miembros = (
                SELECT COUNT(*)
                FROM USUARIO
                WHERE cod_club = NEW.cod_club
            )
            WHERE CodClub = NEW.cod_club;
        END IF;
        RETURN NEW;
    
    ELSIF (TG_OP = 'UPDATE') THEN
        IF (OLD.cod_club IS DISTINCT FROM NEW.cod_club) THEN
            
            IF OLD.cod_club IS NOT NULL THEN
                UPDATE CLUB
                SET num_miembros = (
                    SELECT COUNT(*)
                    FROM USUARIO
                    WHERE cod_club = OLD.cod_club
                )
                WHERE CodClub = OLD.cod_club;
            END IF;
            
            IF NEW.cod_club IS NOT NULL THEN
                UPDATE CLUB
                SET num_miembros = (
                    SELECT COUNT(*)
                    FROM USUARIO
                    WHERE cod_club = NEW.cod_club
                )
                WHERE CodClub = NEW.cod_club;
            END IF;
        END IF;
        RETURN NEW;

    ELSIF (TG_OP = 'DELETE') THEN
        -- Si el usuario pertenecía a un club
        IF OLD.cod_club IS NOT NULL THEN
            UPDATE CLUB
            SET num_miembros = (
                SELECT COUNT(*)
                FROM USUARIO
                WHERE cod_club = OLD.cod_club
            )
            WHERE CodClub = OLD.cod_club;
        END IF;
        RETURN OLD;
    END IF;
    
    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_actualizar_miembros
AFTER INSERT OR UPDATE OR DELETE ON USUARIO
FOR EACH ROW
EXECUTE FUNCTION actualizar_num_miembros();

-- Trigger para controlar que la cantidad de miembros no supere a la capacidad máxima del club
CREATE OR REPLACE FUNCTION F_CONTROL_CAPACIDAD()
RETURNS TRIGGER AS $$
DECLARE
    miembros_actuales NUMERIC;
    capacidad NUMERIC;
BEGIN

    SELECT num_miembros, capacidad_miembros
    INTO miembros_actuales, capacidad
    FROM CLUB
    WHERE CodClub = NEW.cod_club;

    IF miembros_actuales >= capacidad THEN
        RAISE EXCEPTION 'Club lleno';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER TR_CAPACIDAD
BEFORE INSERT ON USUARIO
FOR EACH ROW
EXECUTE FUNCTION F_CONTROL_CAPACIDAD();

-- Trigger para evitar que un usario obtenga el mismo logro mas de una vez
CREATE OR REPLACE FUNCTION F_NO_LOGRO_DUPLICADO()
RETURNS TRIGGER AS $$
DECLARE
    existe NUMERIC;
BEGIN

    SELECT COUNT(*) INTO existe
    FROM OBTENER
    WHERE dni_usuario = NEW.dni_usuario
    AND cod_logro = NEW.cod_logro;

    IF existe > 0 THEN
        RAISE EXCEPTION 'El usuario ya tiene este logro';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER TR_NO_DUPLICAR_LOGRO
BEFORE INSERT ON OBTENER
FOR EACH ROW
EXECUTE FUNCTION F_NO_LOGRO_DUPLICADO();

-- Trigger para evitar que un usuario tenga mas de un plan alimenticio asignado
CREATE OR REPLACE FUNCTION F_UN_SOLO_PLAN()
RETURNS TRIGGER AS $$
DECLARE
    existe INTEGER;
BEGIN

    SELECT COUNT(*) INTO existe
    FROM USUARIO
    WHERE codPlan = NEW.codPlan;

    IF existe > 0 THEN
        RAISE EXCEPTION 'Este plan alimenticio ya está asignado a otro usuario';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER TR_UN_SOLO_PLAN
BEFORE INSERT OR UPDATE ON USUARIO
FOR EACH ROW
EXECUTE FUNCTION F_UN_SOLO_PLAN();