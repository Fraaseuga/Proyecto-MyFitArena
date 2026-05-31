--
-- PostgreSQL database dump
--

\restrict WFQMmxFtIFV5h9TNQg1kcPnLeemckiSnau3ubclR9chopYiGbjjYUn3Zfk5nnkt

-- Dumped from database version 18.1
-- Dumped by pg_dump version 18.1

-- Started on 2026-05-30 16:34:24

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- TOC entry 5201 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


--
-- TOC entry 259 (class 1255 OID 19647)
-- Name: actualizar_num_miembros(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.actualizar_num_miembros() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
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
$$;


ALTER FUNCTION public.actualizar_num_miembros() OWNER TO postgres;

--
-- TOC entry 244 (class 1255 OID 20128)
-- Name: contar_entrenamientos(character varying); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.contar_entrenamientos(p_dni character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
    total INTEGER;
BEGIN

    SELECT COUNT(*) INTO total
    FROM REALIZAR
    WHERE dni_usuario = p_dni;

    RETURN total;
END;
$$;


ALTER FUNCTION public.contar_entrenamientos(p_dni character varying) OWNER TO postgres;

--
-- TOC entry 243 (class 1255 OID 20127)
-- Name: es_propietario(character varying, character varying); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.es_propietario(p_dni character varying, p_codclub character varying) RETURNS boolean
    LANGUAGE plpgsql
    AS $$
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
$$;


ALTER FUNCTION public.es_propietario(p_dni character varying, p_codclub character varying) OWNER TO postgres;

--
-- TOC entry 260 (class 1255 OID 20132)
-- Name: f_control_capacidad(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.f_control_capacidad() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
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
$$;


ALTER FUNCTION public.f_control_capacidad() OWNER TO postgres;

--
-- TOC entry 261 (class 1255 OID 20134)
-- Name: f_no_logro_duplicado(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.f_no_logro_duplicado() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
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
$$;


ALTER FUNCTION public.f_no_logro_duplicado() OWNER TO postgres;

--
-- TOC entry 247 (class 1255 OID 20136)
-- Name: f_un_solo_plan(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.f_un_solo_plan() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
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
$$;


ALTER FUNCTION public.f_un_solo_plan() OWNER TO postgres;

--
-- TOC entry 245 (class 1255 OID 20129)
-- Name: listar_entrenamientos(character varying); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.listar_entrenamientos(p_dni character varying) RETURNS text
    LANGUAGE plpgsql
    AS $$
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
$$;


ALTER FUNCTION public.listar_entrenamientos(p_dni character varying) OWNER TO postgres;

--
-- TOC entry 246 (class 1255 OID 20130)
-- Name: nivel_progreso(character varying); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.nivel_progreso(p_dni character varying) RETURNS text
    LANGUAGE plpgsql
    AS $$
DECLARE
    nivel TEXT;
BEGIN

    SELECT nivel INTO nivel
    FROM PROGRESO
    WHERE dni_usuario = p_dni
    LIMIT 1;

    RETURN nivel;
END;
$$;


ALTER FUNCTION public.nivel_progreso(p_dni character varying) OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 222 (class 1259 OID 28644)
-- Name: anuncio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.anuncio (
    codanuncio character varying(5) NOT NULL,
    titulo character varying(40),
    contenido character varying(150),
    fecha_publicacion date
);


ALTER TABLE public.anuncio OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 28662)
-- Name: calistenia; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.calistenia (
    codentrena character varying(5) NOT NULL,
    tipo_ejercicio character varying(30),
    lastre_kg numeric(5,2),
    nivel_progresion character varying(20),
    requiere_barras boolean,
    requiere_anillas boolean,
    enfoque_habilidad character varying(50),
    trabajo_isometrico boolean,
    dificultad character varying(10),
    descripcion character varying(500)
);


ALTER TABLE public.calistenia OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 28701)
-- Name: cardio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cardio (
    codentrena character varying(5) NOT NULL,
    tipo_cardio character varying(30),
    distancia_km numeric(6,2),
    ritmo_promedio character varying(10),
    frecuencia_cardiaca_promedio integer,
    frecuencia_cardiaca_maxima integer,
    zona_entrenamiento character varying(30),
    calorias_quemadas integer,
    tipo_sesion character varying(20),
    inclinacion_promedio numeric(4,1),
    intervalo_trabajo_descanso character varying(10),
    dificultad character varying(10),
    descripcion character varying(500)
);


ALTER TABLE public.cardio OWNER TO postgres;

--
-- TOC entry 230 (class 1259 OID 28727)
-- Name: club; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.club (
    codclub character varying(5) NOT NULL,
    nombre character varying(30),
    descripcion character varying(60),
    fechacreacion date,
    num_miembros numeric(4,0),
    capacidad_miembros numeric(4,0),
    dni_propietario character varying(9)
);


ALTER TABLE public.club OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 28626)
-- Name: ejercicio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ejercicio (
    codejercicio character varying(5) NOT NULL,
    nombre character varying(30),
    series numeric(2,0),
    repeticiones numeric(2,0)
);


ALTER TABLE public.ejercicio OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 28714)
-- Name: elasticidad; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.elasticidad (
    codentrena character varying(5) NOT NULL,
    tipo_estiramiento character varying(30),
    nivel_elasticidad integer,
    zona_corporal_principal character varying(30),
    metodo character varying(30),
    tiempo_por_posicion integer,
    incluye_respiracion boolean,
    requiere_accesorios boolean,
    tipo_accesorios character varying(50),
    objetivo character varying(50),
    temperatura_muscular character varying(20),
    dificultad character varying(10),
    descripcion character varying(500)
);


ALTER TABLE public.elasticidad OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 28632)
-- Name: entrenamiento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.entrenamiento (
    codentrenamiento character varying(5) NOT NULL,
    dificultad character varying(20),
    duracion numeric(3,0),
    cantidad numeric(2,0),
    descripcion character varying(300)
);


ALTER TABLE public.entrenamiento OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 28650)
-- Name: evento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.evento (
    codevento character varying(5) NOT NULL,
    tipo character varying(30),
    descripcion character varying(100),
    ubicacion character varying(50),
    fecha_y_hora timestamp without time zone
);


ALTER TABLE public.evento OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 28675)
-- Name: hipertrofia; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.hipertrofia (
    codentrena character varying(5) NOT NULL,
    tecnica character varying(100),
    peso_medio_kg numeric(5,2),
    grupo_muscular_principal character varying(30),
    grupo_muscular_secundario character varying(30),
    rango_repeticiones character varying(10),
    tiempo_bajo_tension integer,
    descanso_entre_series integer,
    tipo_division character varying(30),
    volumen_total_kg numeric(8,2),
    dificultad character varying(10),
    descripcion character varying(500)
);


ALTER TABLE public.hipertrofia OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 28656)
-- Name: logro; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.logro (
    codlogro character varying(5) NOT NULL,
    nombre character varying(30),
    descripcion character varying(100)
);


ALTER TABLE public.logro OWNER TO postgres;

--
-- TOC entry 237 (class 1259 OID 28836)
-- Name: obtener; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.obtener (
    dni_usuario character varying(9) NOT NULL,
    cod_logro character varying(5) NOT NULL
);


ALTER TABLE public.obtener OWNER TO postgres;

--
-- TOC entry 233 (class 1259 OID 28774)
-- Name: participar; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.participar (
    cod_evento character varying(5) NOT NULL,
    dni_usuario character varying(9) NOT NULL
);


ALTER TABLE public.participar OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 28638)
-- Name: plan_alimenticio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.plan_alimenticio (
    codplan character varying(5) NOT NULL,
    calorias_diarias numeric(5,0),
    proteinas numeric(4,1),
    grasas numeric(4,1),
    carbohidratos numeric(4,1)
);


ALTER TABLE public.plan_alimenticio OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 28688)
-- Name: powerlifting; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.powerlifting (
    codentrena character varying(5) NOT NULL,
    peso_maximo_kg numeric(6,2),
    levantamiento_principal character varying(30),
    porcentaje_1rm integer,
    repeticiones_objetivo integer,
    velocidad_barra numeric(3,2),
    usa_equipamiento boolean,
    tipo_equipamiento character varying(50),
    pausa_en_pecho boolean,
    profundidad_sentadilla character varying(20),
    dificultad character varying(10),
    descripcion character varying(500)
);


ALTER TABLE public.powerlifting OWNER TO postgres;

--
-- TOC entry 238 (class 1259 OID 28853)
-- Name: progreso; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.progreso (
    codprogreso character varying(5) NOT NULL,
    fecha_y_hora timestamp without time zone,
    nivel character varying(20),
    dni_usuario character varying(9) NOT NULL
);


ALTER TABLE public.progreso OWNER TO postgres;

--
-- TOC entry 235 (class 1259 OID 28808)
-- Name: realizar; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.realizar (
    dni_usuario character varying(9) NOT NULL,
    cod_entrenamiento character varying(5) NOT NULL
);


ALTER TABLE public.realizar OWNER TO postgres;

--
-- TOC entry 236 (class 1259 OID 28825)
-- Name: recomendaciones; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.recomendaciones (
    codrecomendacion character varying(5) NOT NULL,
    descripcion character varying(150),
    prioridad numeric(1,0),
    dni_usuario character varying(9),
    cod_entrenamiento character varying(5)
);


ALTER TABLE public.recomendaciones OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 28751)
-- Name: tener; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tener (
    cod_evento character varying(5) NOT NULL,
    cod_anuncio character varying(5) NOT NULL,
    cod_club character varying(5) NOT NULL
);


ALTER TABLE public.tener OWNER TO postgres;

--
-- TOC entry 234 (class 1259 OID 28791)
-- Name: tener_amigos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tener_amigos (
    amigo1 character varying(9) NOT NULL,
    amigo2 character varying(9) NOT NULL
);


ALTER TABLE public.tener_amigos OWNER TO postgres;

--
-- TOC entry 240 (class 1259 OID 28871)
-- Name: tickets; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tickets (
    id integer NOT NULL,
    nombre character varying(100) NOT NULL,
    email character varying(100) NOT NULL,
    asunto character varying(200) NOT NULL,
    mensaje text NOT NULL,
    fecha_creacion timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT tickets_asunto_check CHECK (((asunto)::text = ANY ((ARRAY['Consulta general'::character varying, 'Soporte técnico'::character varying, 'Sugerencia'::character varying, 'Otro'::character varying])::text[])))
);


ALTER TABLE public.tickets OWNER TO postgres;

--
-- TOC entry 239 (class 1259 OID 28870)
-- Name: tickets_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tickets_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tickets_id_seq OWNER TO postgres;

--
-- TOC entry 5202 (class 0 OID 0)
-- Dependencies: 239
-- Name: tickets_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tickets_id_seq OWNED BY public.tickets.id;


--
-- TOC entry 231 (class 1259 OID 28733)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
    dni character varying(9) NOT NULL,
    nombre character varying(30),
    apellidos character varying(50),
    telefono character varying(15),
    correo_electronico character varying(50),
    contrasena character varying(100),
    codplan character varying(5),
    cod_club character varying(5)
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- TOC entry 242 (class 1259 OID 28892)
-- Name: v_usuario_entrenamiento; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.v_usuario_entrenamiento AS
 SELECT u.dni,
    u.nombre,
    r.cod_entrenamiento,
    e.dificultad,
    e.descripcion
   FROM public.usuario u,
    public.realizar r,
    public.entrenamiento e
  WHERE (((u.dni)::text = (r.dni_usuario)::text) AND ((r.cod_entrenamiento)::text = (e.codentrenamiento)::text));


ALTER VIEW public.v_usuario_entrenamiento OWNER TO postgres;

--
-- TOC entry 241 (class 1259 OID 28888)
-- Name: v_usuarios_club; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.v_usuarios_club AS
 SELECT u.dni,
    u.nombre,
    u.apellidos,
    u.cod_club,
    c.nombre AS nombre_club
   FROM public.usuario u,
    public.club c
  WHERE ((u.cod_club)::text = (c.codclub)::text);


ALTER VIEW public.v_usuarios_club OWNER TO postgres;

--
-- TOC entry 4952 (class 2604 OID 28874)
-- Name: tickets id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tickets ALTER COLUMN id SET DEFAULT nextval('public.tickets_id_seq'::regclass);


--
-- TOC entry 5177 (class 0 OID 28644)
-- Dependencies: 222
-- Data for Name: anuncio; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.anuncio (codanuncio, titulo, contenido, fecha_publicacion) FROM stdin;
A001	Nuevo evento	Participa en nuestro evento mensual	2024-01-10
A002	Promoción	Descuento en planes	2024-02-15
A003	Taller	Taller de nutrición	2024-03-20
A004	Competencia	Competencia de fuerza	2024-04-05
A005	Reunión	Reunión del club	2024-05-12
A006	Aviso	Cambio de horario	2024-06-18
A007	Entrenamiento	Clase especial	2024-07-22
A008	Maratón	Inscripciones abiertas	2024-08-30
A009	Seminario	Seminario de salud	2024-09-14
A010	Fiesta	Fiesta anual del club	2024-10-01
\.


--
-- TOC entry 5180 (class 0 OID 28662)
-- Dependencies: 225
-- Data for Name: calistenia; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.calistenia (codentrena, tipo_ejercicio, lastre_kg, nivel_progresion, requiere_barras, requiere_anillas, enfoque_habilidad, trabajo_isometrico, dificultad, descripcion) FROM stdin;
T001	Movilidad general	0.00	Básico	f	f	Movilidad	t	Fácil	Entrenamiento enfocado en mejorar la movilidad general.\nIncluye movimientos suaves y controlados.\nIdeal para principiantes.
T008	Calistenia básica	0.00	Inicial	t	f	Control corporal	f	Fácil	Rutina básica de calistenia.\nSe trabaja fuerza relativa y coordinación.\nPerfecto para empezar.
T011	Dominadas	5.00	Intermedio	t	f	Fuerza tirón	f	Media	Ejercicio clásico de tirón vertical.\nDesarrolla espalda y brazos.\nProgresión fundamental en calistenia.
T012	Handstand	0.00	Avanzado	f	f	Equilibrio	t	Difícil	Trabajo avanzado de equilibrio sobre manos.\nRequiere fuerza de hombros y control corporal.\nIncluye progresiones desde pared.
T013	Muscle-up	10.00	Avanzado	t	t	Explosividad	f	Difícil	Movimiento explosivo combinando dominada y fondo.\nRequiere técnica, potencia y coordinación.\nMuy usado en calistenia avanzada.
T014	L-sit	0.00	Intermedio	f	t	Core isométrico	t	Media	Isométrico avanzado para core.\nFortalece abdominales y flexores de cadera.\nExcelente para control corporal.
\.


--
-- TOC entry 5183 (class 0 OID 28701)
-- Dependencies: 228
-- Data for Name: cardio; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cardio (codentrena, tipo_cardio, distancia_km, ritmo_promedio, frecuencia_cardiaca_promedio, frecuencia_cardiaca_maxima, zona_entrenamiento, calorias_quemadas, tipo_sesion, inclinacion_promedio, intervalo_trabajo_descanso, dificultad, descripcion) FROM stdin;
T004	Cinta	5.00	6:00	135	160	Zona 2	280	Continuo	1.0	\N	Media	Cardio continuo en cinta.\nRitmo estable y controlado.\nIdeal para mejorar resistencia general.
T009	HIIT	3.00	4:30	155	185	Zona 4	420	Intervalos	2.5	40/20	Difícil	Entrenamiento HIIT de alta intensidad.\nIncluye intervalos cortos de esfuerzo máximo.\nExcelente para mejorar VO2max.
T023	Caminata	2.00	10:00	110	130	Zona 1	120	Suave	0.0	\N	Fácil	Caminata ligera.\nPerfecta para principiantes o recuperación.\nBajo impacto y fácil de mantener.
T024	Carrera larga	10.00	5:30	145	170	Zona 3	600	Continuo	1.0	\N	Difícil	Carrera de larga distancia.\nMejora resistencia aeróbica.\nRequiere buena base cardiovascular.
T025	Bicicleta	8.00	4:20	140	165	Zona 2	350	Continuo	0.5	\N	Media	Sesión de bicicleta.\nTrabajo constante y fluido.\nIdeal para resistencia y quema de calorías.
T026	Elíptica	3.50	6:30	130	150	Zona 1	200	Suave	0.0	\N	Fácil	Entrenamiento suave en elíptica.\nBajo impacto y fácil de mantener.\nPerfecto para principiantes.
\.


--
-- TOC entry 5185 (class 0 OID 28727)
-- Dependencies: 230
-- Data for Name: club; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.club (codclub, nombre, descripcion, fechacreacion, num_miembros, capacidad_miembros, dni_propietario) FROM stdin;
C001	Fitness Pro	Club de entrenamiento avanzado	2020-01-10	4	50	11111111A
C002	CardioMax	Club de cardio y running	2019-05-22	4	45	22222222B
C003	PowerClub	Powerlifting y fuerza	2021-03-15	4	48	33333333C
C004	FlexFit	Elasticidad y movilidad	2018-11-30	4	40	44444444D
C005	NutriLife	Club de nutrición	2022-02-18	4	35	55555555E
C006	CalisTeam	Calistenia y street workout	2020-07-09	4	45	66666666F
C007	MuscleLab	Hipertrofia avanzada	2019-09-12	4	55	77777777G
C008	ZenFit	Bienestar y estiramientos	2021-12-01	4	38	88888888H
C009	RunFast	Running profesional	2017-04-25	4	50	99999999I
C010	StrongNation	Fuerza general	2023-01-05	4	32	00000000J
C011	BodyBalance	Equilibrio cuerpo-mente	2020-06-15	4	42	12345678K
C012	IronWarriors	Levantamiento de pesas	2019-08-20	4	46	23456789L
C013	SpeedDemon	Velocidad y agilidad	2021-05-10	3	38	34567890M
C014	YogaFlow	Yoga y meditación	2018-03-25	3	44	45678901N
C015	CrossFit Elite	CrossFit competitivo	2022-09-12	3	52	56789012O
C016	SwimPro	Natación profesional	2020-02-28	3	36	67890123P
C017	FitFamily	Fitness familiar	2019-11-05	3	48	78901234Q
C018	GymRats	Entrenamiento hardcore	2021-07-18	3	42	89012345R
C019	HealthFirst	Salud preventiva	2018-12-08	3	40	90123456S
C020	AthleteZone	Alto rendimiento	2022-04-22	3	50	01234567T
C021	FlexZone	Flexibilidad avanzada	2020-10-14	3	35	11223344U
C022	EnduranceCrew	Resistencia extrema	2019-06-30	3	46	22334455V
C023	MindBody	Conexión mente-cuerpo	2021-02-17	3	40	33445566W
C024	StrengthHub	Centro de fuerza	2018-09-05	3	44	44556677X
C025	ActiveLife	Vida activa	2022-11-28	3	38	55667788Y
C026	FitnessFusion	Entrenamiento mixto	2020-03-11	3	42	66778899Z
C027	WellnessClub	Bienestar integral	2019-12-22	3	40	77889900A
C028	PeakPerformance	Rendimiento máximo	2021-08-09	3	50	88990011B
C029	VitalityGroup	Vitalidad y energía	2018-05-15	3	36	99001122C
C030	UltraFit	Fitness ultra	2022-07-03	3	44	00112233D
\.


--
-- TOC entry 5174 (class 0 OID 28626)
-- Dependencies: 219
-- Data for Name: ejercicio; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ejercicio (codejercicio, nombre, series, repeticiones) FROM stdin;
E001	Flexiones	4	12
E002	Sentadillas	4	15
E003	Dominadas	3	8
E004	Plancha	3	1
E005	Burpees	4	10
E006	Zancadas	4	12
E007	Press banca	5	5
E008	Peso muerto	5	5
E009	Curl bíceps	4	10
E010	Remo	4	12
\.


--
-- TOC entry 5184 (class 0 OID 28714)
-- Dependencies: 229
-- Data for Name: elasticidad; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.elasticidad (codentrena, tipo_estiramiento, nivel_elasticidad, zona_corporal_principal, metodo, tiempo_por_posicion, incluye_respiracion, requiere_accesorios, tipo_accesorios, objetivo, temperatura_muscular, dificultad, descripcion) FROM stdin;
T005	Estático	1	Cuerpo completo	Estático	30	t	f	\N	Relajación	Baja	Fácil	Estiramientos estáticos mantenidos.\nMejoran relajación muscular.\nPerfecto para sesiones suaves.
T010	Dinámico	3	Cadera	Dinámico	20	t	t	Banda elástica	Apertura	Media	Media	Movilidad dinámica enfocada en articulaciones.\nIncluye movimientos fluidos.\nIdeal para calentar.
T027	PNF	2	Isquios	PNF	15	t	f	\N	Flexibilidad	Alta	Media	Método PNF para mejorar flexibilidad.\nIncluye contracción y relajación.\nMuy efectivo para isquios.
T028	Yoga avanzado	4	Columna	Yoga	40	t	t	Bloques	Control	Alta	Difícil	Secuencia avanzada de yoga.\nRequiere control corporal y respiración.\nIdeal para usuarios experimentados.
T029	Estático suave	1	Cuello	Estático	20	t	f	\N	Relajación	Baja	Fácil	Estiramientos suaves para cuello.\nReducen tensión acumulada.\nPerfecto para sesiones de relajación.
T030	Movilidad articular	3	Hombros	Dinámico	25	t	f	\N	Rango articular	Media	Difícil	Movilidad enfocada en hombros.\nMejora rango articular y control.\nMuy útil para deportes overhead.
\.


--
-- TOC entry 5175 (class 0 OID 28632)
-- Dependencies: 220
-- Data for Name: entrenamiento; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.entrenamiento (codentrenamiento, dificultad, duracion, cantidad, descripcion) FROM stdin;
T001	Fácil	30	5	Entrenamiento básico de movilidad.\nMovimientos suaves y controlados.\nIdeal para empezar.
T002	Media	45	6	Rutina de fuerza general.\nIncluye ejercicios multiarticulares.\nPerfecta para progreso constante.
T003	Difícil	60	8	Entrenamiento avanzado.\nAlta intensidad y volumen.\nRecomendado para usuarios experimentados.
T004	Media	40	5	Cardio moderado.\nRitmo estable y sostenido.\nMejora la resistencia aeróbica.
T005	Fácil	25	4	Estiramientos guiados.\nMovilidad suave y progresiva.\nPerfecto para relajación.
T006	Difícil	70	10	Powerlifting intensivo.\nTrabajo pesado en los tres básicos.\nEnfoque en fuerza máxima.
T007	Media	50	7	Hipertrofia de torso.\nEjercicios de empuje y tirón.\nIdeal para ganar masa muscular.
T008	Fácil	20	3	Calistenia básica.\nControl corporal y movimientos simples.\nPerfecto para principiantes.
T009	Difícil	55	8	Cardio HIIT.\nIntervalos de alta intensidad.\nExcelente para quemar calorías.
T010	Media	45	6	Elasticidad y movilidad.\nMovimientos dinámicos y estáticos.\nMejora el rango articular.
T011	Media	35	5	Dominadas progresivas.\nTrabajo de tirón vertical.\nFortalece espalda y brazos.
T012	Difícil	50	6	Handstand y equilibrio.\nTrabajo avanzado de control corporal.\nRequiere fuerza y técnica.
T013	Difícil	55	7	Muscle-up explosivo.\nCombinación de tirón y empuje.\nMuy demandante técnicamente.
T014	Media	30	4	L-sit y core isométrico.\nTrabajo intenso del abdomen.\nMejora estabilidad y control.
T015	Media	60	8	Pirámides ascendentes.\nIncremento progresivo de carga.\nIdeal para fuerza e hipertrofia.
T016	Difícil	70	10	Entrenamiento al fallo.\nAlta intensidad y volumen.\nSolo para avanzados.
T017	Media	55	7	Hipertrofia de piernas.\nTrabajo completo de cuádriceps e isquios.\nSesión muy completa.
T018	Difícil	65	5	Método 5x5 modificado.\nSeries pesadas con descansos largos.\nEnfoque en fuerza.
T019	Difícil	75	5	Sentadilla pesada.\nTrabajo técnico y profundo.\nRequiere movilidad avanzada.
T020	Media	45	6	Press banca técnico.\nTrabajo de pausa y control.\nMejora fuerza y estabilidad.
T021	Difícil	80	4	Peso muerto pesado.\nEnfoque en fuerza máxima.\nMuy demandante físicamente.
T022	Media	50	6	Sentadilla técnica.\nPeso moderado y buena forma.\nIdeal para progresar seguro.
T023	Fácil	25	3	Caminata ligera.\nBajo impacto y ritmo suave.\nPerfecto para recuperación.
T024	Difícil	90	7	Carrera larga.\nTrabajo aeróbico sostenido.\nRequiere buena base cardiovascular.
T025	Media	40	5	Bicicleta continua.\nRitmo estable y fluido.\nExcelente para resistencia.
T026	Fácil	20	3	Elíptica suave.\nMovimiento fluido y sin impacto.\nIdeal para principiantes.
T027	Media	30	4	Estiramientos PNF.\nContracción y relajación.\nMejora flexibilidad rápidamente.
T028	Difícil	50	6	Yoga avanzado.\nSecuencias exigentes y controladas.\nRequiere experiencia previa.
T029	Fácil	20	3	Estiramientos suaves.\nMovimientos lentos y relajantes.\nReduce tensión muscular.
T030	Difícil	45	5	Movilidad articular avanzada.\nTrabajo profundo de hombros y cadera.\nMejora rango y control.
\.


--
-- TOC entry 5178 (class 0 OID 28650)
-- Dependencies: 223
-- Data for Name: evento; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.evento (codevento, tipo, descripcion, ubicacion, fecha_y_hora) FROM stdin;
E001	Competencia	Competencia mensual	Gimnasio	2024-01-15 10:00:00
E002	Taller	Taller de cocina	Sala 2	2024-02-20 17:00:00
E003	Charla	Charla motivacional	Auditorio	2024-03-10 12:00:00
E004	Maratón	Carrera 10K	Ciudad	2024-04-25 09:00:00
E005	Reunión	Reunión general	Sala 1	2024-05-05 18:00:00
E006	Competencia	Powerlifting	Gimnasio	2024-06-12 11:00:00
E007	Yoga	Clase especial	Sala Zen	2024-07-08 08:00:00
E008	Nutrición	Seminario	Sala 3	2024-08-19 16:00:00
E009	Entrenamiento	Clase grupal	Pista	2024-09-22 19:00:00
E010	Fiesta	Fiesta anual	Terraza	2024-10-10 21:00:00
\.


--
-- TOC entry 5181 (class 0 OID 28675)
-- Dependencies: 226
-- Data for Name: hipertrofia; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.hipertrofia (codentrena, tecnica, peso_medio_kg, grupo_muscular_principal, grupo_muscular_secundario, rango_repeticiones, tiempo_bajo_tension, descanso_entre_series, tipo_division, volumen_total_kg, dificultad, descripcion) FROM stdin;
T002	Técnica de repeticiones lentas	40.00	Pectoral	Tríceps	10-12	50	90	Push/Pull/Legs	480.00	Media	Método centrado en aumentar el tiempo bajo tensión.\nMejora la conexión mente-músculo.\nIdeal para fases de volumen controlado.
T003	Super series	35.00	Espalda	Bíceps	12-15	40	60	Torso/Pierna	520.00	Difícil	Super series combinadas sin descanso.\nAumentan intensidad y congestión muscular.\nRecomendado para usuarios avanzados.
T007	Drop sets	30.00	Hombro	Trapecio	12-20	45	75	Fullbody	450.00	Media	Método avanzado reduciendo peso sin descanso.\nEstimula fibras profundas.\nMuy efectivo para romper estancamientos.
T015	Pirámides ascendentes	50.00	Piernas	Glúteos	8-12	55	120	Pierna	600.00	Media	Progresión aumentando peso en cada serie.\nExcelente para fuerza e hipertrofia.\nMuy usado en rutinas de pierna.
T016	Entrenamiento al fallo	45.00	Pectoral	Hombro	6-10	60	150	Push	700.00	Difícil	Entrenamiento llevado al fallo muscular.\nAlta intensidad y demanda física.\nSolo recomendado para avanzados.
T017	Hipertrofia de piernas	55.00	Cuádriceps	Isquios	10-15	50	120	Pierna	650.00	Media	Rutina enfocada en volumen de piernas.\nIncluye sentadilla, prensa y extensiones.\nIdeal para crecimiento muscular.
T018	Método 5x5 modificado	60.00	Espalda	Bíceps	5	30	180	Torso	750.00	Difícil	Método clásico de fuerza adaptado a hipertrofia.\nSeries pesadas con descansos largos.\nExcelente para progresión.
\.


--
-- TOC entry 5179 (class 0 OID 28656)
-- Dependencies: 224
-- Data for Name: logro; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.logro (codlogro, nombre, descripcion) FROM stdin;
L001	Constancia	Entrenar 7 días seguidos
L002	Fuerza	Levantar 100kg
L003	Resistencia	Correr 5km
L004	Flexibilidad	Tocar los pies
L005	Velocidad	Sprint 100m
L006	Potencia	Salto vertical alto
L007	Técnica	Ejecutar ejercicio perfecto
L008	Cardio	30 min sin parar
L009	Progreso	Mejorar marca personal
L010	Disciplina	Cumplir plan mensual
\.


--
-- TOC entry 5192 (class 0 OID 28836)
-- Dependencies: 237
-- Data for Name: obtener; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.obtener (dni_usuario, cod_logro) FROM stdin;
11111111A	L001
22222222B	L002
33333333C	L003
44444444D	L004
55555555E	L005
66666666F	L006
77777777G	L007
88888888H	L008
99999999I	L009
00000000J	L010
\.


--
-- TOC entry 5188 (class 0 OID 28774)
-- Dependencies: 233
-- Data for Name: participar; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.participar (cod_evento, dni_usuario) FROM stdin;
E001	11111111A
E002	22222222B
E003	33333333C
E004	44444444D
E005	55555555E
E006	66666666F
E007	77777777G
E008	88888888H
E009	99999999I
E010	00000000J
\.


--
-- TOC entry 5176 (class 0 OID 28638)
-- Dependencies: 221
-- Data for Name: plan_alimenticio; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.plan_alimenticio (codplan, calorias_diarias, proteinas, grasas, carbohidratos) FROM stdin;
P001	2200	120.5	60.2	250.3
P002	1800	90.0	50.0	200.0
P003	2500	150.0	70.0	300.0
P004	2000	110.0	55.0	220.0
P005	2700	160.0	80.0	320.0
P006	2300	130.0	65.0	260.0
P007	1900	95.0	45.0	210.0
P008	3000	180.0	90.0	350.0
P009	2100	105.0	50.0	230.0
P010	2600	140.0	75.0	290.0
P011	2400	125.0	62.0	270.0
P012	1850	92.5	48.0	205.0
P013	2550	155.0	72.0	310.0
P014	2050	112.0	56.0	225.0
P015	2750	165.0	82.0	330.0
P016	2350	132.0	66.0	265.0
P017	1950	97.0	47.0	215.0
P018	3050	185.0	92.0	360.0
P019	2150	107.0	52.0	235.0
P020	2650	142.0	77.0	295.0
P021	2250	122.0	61.0	255.0
P022	1900	93.0	49.0	208.0
P023	2600	157.0	73.0	315.0
P024	2100	113.0	57.0	228.0
P025	2800	167.0	83.0	335.0
P026	2400	133.0	67.0	268.0
P027	2000	98.0	48.0	218.0
P028	3100	187.0	93.0	365.0
P029	2200	108.0	53.0	238.0
P030	2700	143.0	78.0	298.0
P031	2300	123.0	62.5	258.0
P032	1920	94.0	49.5	209.0
P033	2620	158.0	73.5	318.0
P034	2120	114.0	57.5	229.0
P035	2820	168.0	83.5	338.0
P036	2420	134.0	67.5	269.0
P037	2020	99.0	48.5	219.0
P038	3120	188.0	93.5	368.0
P039	2220	109.0	53.5	239.0
P040	2720	144.0	78.5	299.0
P041	2320	124.0	63.0	259.0
P042	1940	95.0	50.0	210.5
P043	2640	159.0	74.0	319.0
P044	2140	115.0	58.0	230.0
P045	2840	169.0	84.0	339.0
P046	2440	135.0	68.0	270.0
P047	2040	100.0	49.0	220.0
P048	3140	189.0	94.0	369.0
P049	2240	110.0	54.0	240.0
P050	2740	145.0	79.0	300.0
P051	2340	125.5	63.5	260.0
P052	1960	96.0	50.5	211.0
P053	2660	160.0	74.5	320.0
P054	2160	116.0	58.5	231.0
P055	2860	170.0	84.5	340.0
P056	2460	136.0	68.5	271.0
P057	2060	101.0	49.5	221.0
P058	3160	190.0	94.5	370.0
P059	2260	111.0	54.5	241.0
P060	2760	146.0	79.5	301.0
P061	2360	126.0	64.0	261.0
P062	1980	97.0	51.0	212.0
P063	2680	161.0	75.0	321.0
P064	2180	117.0	59.0	232.0
P065	2880	171.0	85.0	341.0
P066	2480	137.0	69.0	272.0
P067	2080	102.0	50.0	222.0
P068	3180	191.0	95.0	371.0
P069	2280	112.0	55.0	242.0
P070	2780	147.0	80.0	302.0
P071	2380	127.0	64.5	262.0
P072	2010	98.0	51.5	213.0
P073	2710	162.0	75.5	322.0
P074	2210	118.0	59.5	233.0
P075	2910	172.0	85.5	342.0
P076	2510	138.0	69.5	273.0
P077	2110	103.0	50.5	223.0
P078	3210	192.0	95.5	372.0
P079	2310	113.0	55.5	243.0
P080	2810	148.0	80.5	303.0
P081	2410	128.0	65.0	263.0
P082	2030	99.0	52.0	214.0
P083	2730	163.0	76.0	323.0
P084	2230	119.0	60.0	234.0
P085	2930	173.0	86.0	343.0
P086	2530	139.0	70.0	274.0
P087	2130	104.0	51.0	224.0
P088	3230	193.0	96.0	373.0
P089	2330	114.0	56.0	244.0
P090	2830	149.0	81.0	304.0
P091	2430	129.0	65.5	264.0
P092	2050	100.0	52.5	215.0
P093	2750	164.0	76.5	324.0
P094	2250	120.0	60.5	235.0
P095	2950	174.0	86.5	344.0
P096	2550	140.0	70.5	275.0
P097	2150	105.0	51.5	225.0
P098	3250	194.0	96.5	374.0
P099	2350	115.0	56.5	245.0
P100	2850	150.0	81.5	305.0
P101	2450	130.0	66.0	265.0
P102	2070	101.0	53.0	216.0
P103	2770	165.0	77.0	325.0
P104	2270	121.0	61.0	236.0
P105	2970	175.0	87.0	345.0
P106	2570	141.0	71.0	276.0
P107	2170	106.0	52.0	226.0
P108	3270	195.0	97.0	375.0
P109	2370	116.0	57.0	246.0
P110	2870	151.0	82.0	306.0
P111	2470	131.0	66.5	266.0
P112	2090	102.0	53.5	217.0
P113	2790	166.0	77.5	326.0
P114	2290	122.0	61.5	237.0
P115	2990	176.0	87.5	346.0
P116	2590	142.0	71.5	277.0
P117	2190	107.0	52.5	227.0
P118	3290	196.0	97.5	376.0
P119	2390	117.0	57.5	247.0
P120	2890	152.0	82.5	307.0
\.


--
-- TOC entry 5182 (class 0 OID 28688)
-- Dependencies: 227
-- Data for Name: powerlifting; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.powerlifting (codentrena, peso_maximo_kg, levantamiento_principal, porcentaje_1rm, repeticiones_objetivo, velocidad_barra, usa_equipamiento, tipo_equipamiento, pausa_en_pecho, profundidad_sentadilla, dificultad, descripcion) FROM stdin;
T006	180.50	Peso muerto	85	3	0.45	t	Cinturón	f	Reglamentaria	Difícil	Sesión centrada en peso muerto pesado.\nIncluye técnica, velocidad y control postural.\nIdeal para fases de fuerza máxima.
T019	200.00	Sentadilla	80	5	0.40	t	Rodilleras	f	Profunda	Difícil	Sentadilla pesada con profundidad reglamentaria.\nRequiere movilidad y técnica avanzada.\nMuy demandante para piernas.
T020	150.00	Press banca	70	6	0.50	f	No se usa	t	Normal	Media	Press banca con pausa en pecho.\nMejora control y fuerza en el movimiento.\nIdeal para progresión técnica.
T021	220.00	Peso muerto	90	2	0.35	t	Cinturón y straps	f	Reglamentaria	Difícil	Peso muerto muy pesado.\nEnfocado en fuerza máxima.\nRequiere calentamiento específico.
T022	130.00	Sentadilla	65	8	0.55	f	No se usa	f	Paralela	Media	Sentadilla técnica con peso moderado.\nIdeal para mejorar forma y resistencia.\nPerfecta para fases de volumen.
\.


--
-- TOC entry 5193 (class 0 OID 28853)
-- Dependencies: 238
-- Data for Name: progreso; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.progreso (codprogreso, fecha_y_hora, nivel, dni_usuario) FROM stdin;
PR01	2024-01-10 10:00:00	Bajo	11111111A
PR02	2024-02-12 11:00:00	Medio	22222222B
PR03	2024-03-15 12:00:00	Alto	33333333C
PR04	2024-04-18 13:00:00	Medio	44444444D
PR05	2024-05-20 14:00:00	Bajo	55555555E
PR06	2024-06-22 15:00:00	Alto	66666666F
PR07	2024-07-25 16:00:00	Medio	77777777G
PR08	2024-08-28 17:00:00	Bajo	88888888H
PR09	2024-09-30 18:00:00	Alto	99999999I
PR10	2024-10-05 19:00:00	Medio	00000000J
\.


--
-- TOC entry 5190 (class 0 OID 28808)
-- Dependencies: 235
-- Data for Name: realizar; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.realizar (dni_usuario, cod_entrenamiento) FROM stdin;
11111111A	T001
22222222B	T002
33333333C	T003
44444444D	T004
55555555E	T005
66666666F	T006
77777777G	T007
88888888H	T008
99999999I	T009
00000000J	T010
\.


--
-- TOC entry 5191 (class 0 OID 28825)
-- Dependencies: 236
-- Data for Name: recomendaciones; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.recomendaciones (codrecomendacion, descripcion, prioridad, dni_usuario, cod_entrenamiento) FROM stdin;
R001	Mejorar técnica	1	11111111A	T001
R002	Aumentar intensidad	2	22222222B	T002
R003	Controlar respiración	1	33333333C	T003
R005	Mayor flexibilidad	2	55555555E	T005
R006	Progresión de cargas	1	66666666F	T006
R007	Más volumen	2	77777777G	T007
R008	Mejor técnica	1	88888888H	T008
R009	Más resistencia	3	99999999I	T009
R010	Control postural	2	00000000J	T010
\.


--
-- TOC entry 5187 (class 0 OID 28751)
-- Dependencies: 232
-- Data for Name: tener; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tener (cod_evento, cod_anuncio, cod_club) FROM stdin;
E001	A001	C001
E002	A002	C002
E003	A003	C003
E004	A004	C004
E005	A005	C005
E006	A006	C006
E007	A007	C007
E008	A008	C008
E009	A009	C009
E010	A010	C010
\.


--
-- TOC entry 5189 (class 0 OID 28791)
-- Dependencies: 234
-- Data for Name: tener_amigos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tener_amigos (amigo1, amigo2) FROM stdin;
11111111A	22222222B
22222222B	33333333C
33333333C	44444444D
44444444D	55555555E
55555555E	66666666F
66666666F	77777777G
77777777G	88888888H
88888888H	99999999I
99999999I	00000000J
00000000J	11111111A
\.


--
-- TOC entry 5195 (class 0 OID 28871)
-- Dependencies: 240
-- Data for Name: tickets; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tickets (id, nombre, email, asunto, mensaje, fecha_creacion, fecha_actualizacion) FROM stdin;
1	Juan Pérez	juan.perez@example.com	Consulta general	Quisiera saber los horarios de atención.	2026-05-30 15:52:30.989776	2026-05-30 15:52:30.989776
2	María García	maria.garcia@example.com	Soporte técnico	La aplicación no me deja iniciar sesión.	2026-05-30 15:52:30.989776	2026-05-30 15:52:30.989776
3	Carlos López	carlos.lopez@example.com	Sugerencia	Sería útil añadir un modo oscuro.	2026-05-30 15:52:30.989776	2026-05-30 15:52:30.989776
4	Ana Martínez	ana.martinez@example.com	Otro	Tengo una consulta que no encaja en las categorías.	2026-05-30 15:52:30.989776	2026-05-30 15:52:30.989776
5	Laura Torres	laura.torres@example.com	Consulta general	¿Cómo puedo cambiar mi contraseña?	2026-05-30 15:52:30.989776	2026-05-30 15:52:30.989776
6	Pedro Sánchez	pedro.sanchez@example.com	Soporte técnico	El sistema se queda congelado al cargar mi perfil.	2026-05-30 15:52:30.989776	2026-05-30 15:52:30.989776
7	Lucía Romero	lucia.romero@example.com	Sugerencia	Podrían añadir más opciones de personalización.	2026-05-30 15:52:30.989776	2026-05-30 15:52:30.989776
8	David Ruiz	david.ruiz@example.com	Consulta general	¿Dónde puedo ver mis facturas anteriores?	2026-05-30 15:52:30.989776	2026-05-30 15:52:30.989776
9	Sofía Navarro	sofia.navarro@example.com	Otro	Quiero eliminar mi cuenta definitivamente.	2026-05-30 15:52:30.989776	2026-05-30 15:52:30.989776
10	Miguel Torres	miguel.torres@example.com	Soporte técnico	No recibo el correo de verificación.	2026-05-30 15:52:30.989776	2026-05-30 15:52:30.989776
\.


--
-- TOC entry 5186 (class 0 OID 28733)
-- Dependencies: 231
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuario (dni, nombre, apellidos, telefono, correo_electronico, contrasena, codplan, cod_club) FROM stdin;
11111111A	Juan	Pérez López	600111222	juan.perez@mail.com	Pass1234!	P001	C001
22222222B	Ana	García Ruiz	600222333	ana.garcia@mail.com	Ana2024@	P002	C002
33333333C	Luis	Martínez Gil	600333444	luis.martinez@mail.com	Luis#456	P003	C003
44444444D	Marta	Sánchez Díaz	600444555	marta.sanchez@mail.com	Marta789$	P004	C004
55555555E	Carlos	López Vega	600555666	carlos.lopez@mail.com	Carlos!2024	P005	C005
66666666F	Laura	Torres Soler	600666777	laura.torres@mail.com	Laura@Pass	P006	C006
77777777G	David	Hernández Mora	600777888	david.hernandez@mail.com	David#123	P007	C007
88888888H	Sara	Navarro León	600888999	sara.navarro@mail.com	Sara2024!	P008	C008
99999999I	Pablo	Castro Ríos	600999000	pablo.castro@mail.com	Pablo$456	P009	C009
00000000J	Elena	Romero Sáez	600000111	elena.romero@mail.com	Elena@789	P010	C010
12345678K	Miguel	Fernández Cruz	601111222	miguel.fernandez@mail.com	Miguel!2024	P011	C011
23456789L	Carmen	Jiménez Vidal	602222333	carmen.jimenez@mail.com	Carmen#Pass	P012	C012
34567890M	Javier	Moreno Santos	603333444	javier.moreno@mail.com	Javier@456	P013	C013
45678901N	Lucía	Álvarez Ruiz	604444555	lucia.alvarez@mail.com	Lucia$2024	P014	C014
56789012O	Sergio	Gómez Martín	605555666	sergio.gomez@mail.com	Sergio!789	P015	C015
67890123P	Patricia	Díaz Soto	606666777	patricia.diaz@mail.com	Patricia@123	P016	C016
78901234Q	Alberto	Ruiz Campos	607777888	alberto.ruiz@mail.com	Alberto#456	P017	C017
89012345R	Raquel	Vargas Ponce	608888999	raquel.vargas@mail.com	Raquel!Pass	P018	C018
90123456S	Jorge	Molina Rivas	609999000	jorge.molina@mail.com	Jorge@2024	P019	C019
01234567T	Cristina	Ortiz Blanco	610000111	cristina.ortiz@mail.com	Cristina$789	P020	C020
11223344U	Roberto	Gil Serrano	611111222	roberto.gil@mail.com	Roberto!2024	P021	C021
22334455V	Beatriz	Peña Castro	612222333	beatriz.pena@mail.com	Beatriz#Pass	P022	C022
33445566W	Fernando	Vega Morales	613333444	fernando.vega@mail.com	Fernando@456	P023	C023
44556677X	Isabel	Ramos Fuentes	614444555	isabel.ramos@mail.com	Isabel$2024	P024	C024
55667788Y	Óscar	Iglesias Luna	615555666	oscar.iglesias@mail.com	Oscar!789	P025	C025
66778899Z	Silvia	Cabrera Nieto	616666777	silvia.cabrera@mail.com	Silvia@123	P026	C026
77889900A	Andrés	Méndez Rojas	617777888	andres.mendez@mail.com	Andres#456	P027	C027
88990011B	Alicia	Cruz Medina	618888999	alicia.cruz@mail.com	Alicia!Pass	P028	C028
99001122C	Iván	Flores Reyes	619999000	ivan.flores@mail.com	Ivan@2024	P029	C029
00112233D	Nuria	Santiago Prieto	620000111	nuria.santiago@mail.com	Nuria$789	P030	C030
10203040E	Diego	Cortés Aguilar	621111222	diego.cortes@mail.com	Diego!2024	P031	\N
20304050F	Mónica	Pascual Lozano	622222333	monica.pascual@mail.com	Monica#Pass	P032	\N
30405060G	Rubén	Gallego Herrera	623333444	ruben.gallego@mail.com	Ruben@456	P033	C001
40506070H	Teresa	Domínguez Cano	624444555	teresa.dominguez@mail.com	Teresa$2024	P034	C002
50607080I	Víctor	Giménez Sanz	625555666	victor.gimenez@mail.com	Victor!789	P035	C003
60708090J	Inmaculada	Rubio Carrasco	626666777	inmaculada.rubio@mail.com	Inmaculada@123	P036	C004
70809001K	Antonio	Marín Delgado	627777888	antonio.marin@mail.com	Antonio#456	P037	C005
80900112L	Esther	Soto Velasco	628888999	esther.soto@mail.com	Esther!Pass	P038	C006
90011223M	Francisco	Bravo Nuñez	629999000	francisco.bravo@mail.com	Francisco@2024	P039	C007
01122334N	Rocío	León Romero	630000111	rocio.leon@mail.com	Rocio$789	P040	C008
12233445O	Alejandro	Ramírez Silva	631111222	alejandro.ramirez@mail.com	Alejandro!2024	P041	C009
23344556P	Pilar	Castillo Mora	632222333	pilar.castillo@mail.com	Pilar#Pass	P042	C010
34455667Q	Guillermo	Ortega Vicente	633333444	guillermo.ortega@mail.com	Guillermo@456	P043	\N
45566778R	Rosa	Hidalgo Suárez	634444555	rosa.hidalgo@mail.com	Rosa$2024	P044	\N
56677889S	Manuel	Lorenzo Benítez	635555666	manuel.lorenzo@mail.com	Manuel!789	P045	C011
67788990T	Dolores	Guerrero Ibáñez	636666777	dolores.guerrero@mail.com	Dolores@123	P046	C012
78899001U	Enrique	Crespo Caballero	637777888	enrique.crespo@mail.com	Enrique#456	P047	C013
89900112V	Amparo	Méndez Durán	638888999	amparo.mendez@mail.com	Amparo!Pass	P048	C014
90011234W	Raúl	Santana Cortés	639999000	raul.santana@mail.com	Raul@2024	P049	C015
01122345X	Consuelo	Peña Gallardo	640000111	consuelo.pena@mail.com	Consuelo$789	P050	C016
11112222Y	Adrián	Vidal Pascual	641111222	adrian.vidal@mail.com	Adrian!2024	P051	\N
22223333Z	Yolanda	Campos Moya	642222333	yolanda.campos@mail.com	Yolanda#Pass	P052	\N
33334444A	Gonzalo	Soler Esteban	643333444	gonzalo.soler@mail.com	Gonzalo@456	P053	C017
44445555B	Mercedes	Navarro Blanco	644444555	mercedes.navarro@mail.com	Mercedes$2024	P054	C018
55556666C	Ignacio	Lozano Ramos	645555666	ignacio.lozano@mail.com	Ignacio!789	P055	C019
66667777D	Josefa	Herrera Campos	646666777	josefa.herrera@mail.com	Josefa@123	P056	C020
77778888E	Marcos	Carmona Iglesias	647777888	marcos.carmona@mail.com	Marcos#456	P057	C021
88889999F	Encarnación	Prieto Santos	648888999	encarnacion.prieto@mail.com	Encarna!Pass	P058	C022
99990000G	Jesús	Reyes Montero	649999000	jesus.reyes@mail.com	Jesus@2024	P059	C023
00001111H	Remedios	Medina Vega	650000111	remedios.medina@mail.com	Remedios$789	P060	C024
10101010I	Ángel	Garrido Molina	651111222	angel.garrido@mail.com	Angel!2024	P061	\N
20202020J	Victoria	Morales Flores	652222333	victoria.morales@mail.com	Victoria#Pass	P062	\N
30303030K	Daniel	Sánchez Ortiz	653333444	daniel.sanchez@mail.com	Daniel@456	P063	C025
40404040L	Aurora	León Cruz	654444555	aurora.leon@mail.com	Aurora$2024	P064	C026
50505050M	Ricardo	Romero Gil	655555666	ricardo.romero@mail.com	Ricardo!789	P065	C027
60606060N	Francisca	Torres Ruiz	656666777	francisca.torres@mail.com	Francisca@123	P066	C028
70707070O	Tomás	García Díaz	657777888	tomas.garcia@mail.com	Tomas#456	P067	C029
80808080P	Milagros	Pérez Moreno	658888999	milagros.perez@mail.com	Milagros!Pass	P068	C030
90909090Q	Felipe	Martínez López	659999000	felipe.martinez@mail.com	Felipe@2024	P069	C001
01010101R	Angustias	Fernández Gómez	660000111	angustias.fernandez@mail.com	Angustias$789	P070	C002
11001100S	Bernardo	Jiménez Sánchez	661111222	bernardo.jimenez@mail.com	Bernardo!2024	P071	\N
22002200T	Purificación	Álvarez Martín	662222333	purificacion.alvarez@mail.com	Purifica#Pass	P072	\N
33003300U	Emilio	Vargas Ruiz	663333444	emilio.vargas@mail.com	Emilio@456	P073	C003
44004400V	Trinidad	Molina Jiménez	664444555	trinidad.molina@mail.com	Trinidad$2024	P074	C004
55005500W	Jaime	Ortiz Álvarez	665555666	jaime.ortiz@mail.com	Jaime!789	P075	C005
66006600X	Asunción	Gil Vargas	666666777	asuncion.gil@mail.com	Asuncion@123	P076	C006
77007700Y	Agustín	Peña Molina	667777888	agustin.pena@mail.com	Agustin#456	P077	C007
88008800Z	Soledad	Vega Ortiz	668888999	soledad.vega@mail.com	Soledad!Pass	P078	C008
99009900A	César	Ramos Gil	669999000	cesar.ramos@mail.com	Cesar@2024	P079	C009
00100100B	Nieves	Iglesias Peña	670000111	nieves.iglesias@mail.com	Nieves$789	P080	C010
10203050C	Pascual	Cabrera Vega	671111222	pascual.cabrera@mail.com	Pascual!2024	P081	\N
20304060D	Visitación	Méndez Ramos	672222333	visitacion.mendez@mail.com	Visita#Pass	P082	\N
30405070E	Eugenio	Cruz Iglesias	673333444	eugenio.cruz@mail.com	Eugenio@456	P083	C011
40506080F	Casilda	Flores Cabrera	674444555	casilda.flores@mail.com	Casilda$2024	P084	C012
50607090G	Valentín	Santiago Méndez	675555666	valentin.santiago@mail.com	Valentin!789	P085	C013
60708001H	Elvira	Cortés Cruz	676666777	elvira.cortes@mail.com	Elvira@123	P086	C014
70809002I	Esteban	Pascual Flores	677777888	esteban.pascual@mail.com	Esteban#456	P087	C015
80900113J	Celestina	Gallego Santiago	678888999	celestina.gallego@mail.com	Celestina!Pass	P088	C016
90011224K	Ramiro	Domínguez Cortés	679999000	ramiro.dominguez@mail.com	Ramiro@2024	P089	C017
01122335L	Herminia	Giménez Pascual	680000111	herminia.gimenez@mail.com	Herminia$789	P090	C018
12233446M	Casimiro	Rubio Gallego	681111222	casimiro.rubio@mail.com	Casimiro!2024	P091	\N
23344557N	Genoveva	Marín Domínguez	682222333	genoveva.marin@mail.com	Genoveva#Pass	P092	\N
34455668O	Benjamín	Soto Giménez	683333444	benjamin.soto@mail.com	Benjamin@456	P093	C019
45566779P	Virtudes	Bravo Rubio	684444555	virtudes.bravo@mail.com	Virtudes$2024	P094	C020
56677880Q	Clemente	León Marín	685555666	clemente.leon@mail.com	Clemente!789	P095	C021
67788991R	Presentación	Ramírez Soto	686666777	presentacion.ramirez@mail.com	Presenta@123	P096	C022
78899002S	Fermín	Castillo Bravo	687777888	fermin.castillo@mail.com	Fermin#456	P097	C023
89900123T	Filomena	Ortega León	688888999	filomena.ortega@mail.com	Filomena!Pass	P098	C024
90011235U	Matías	Hidalgo Ramírez	689999000	matias.hidalgo@mail.com	Matias@2024	P099	C025
01122346V	Ascensión	Lorenzo Castillo	690000111	ascension.lorenzo@mail.com	Ascension$789	P100	C026
12121212W	Laureano	Guerrero Ortega	691111222	laureano.guerrero@mail.com	Laureano!2024	P101	\N
23232323X	Perpetua	Crespo Hidalgo	692222333	perpetua.crespo@mail.com	Perpetua#Pass	P102	\N
34343434Y	Baldomero	Santana Lorenzo	693333444	baldomero.santana@mail.com	Baldomero@456	P103	C027
45454545Z	Adoración	Vidal Guerrero	694444555	adoracion.vidal@mail.com	Adoracion$2024	P104	C028
56565656A	Severino	Campos Crespo	695555666	severino.campos@mail.com	Severino!789	P105	C029
67676767B	Leocadia	Soler Santana	696666777	leocadia.soler@mail.com	Leocadia@123	P106	C030
78787878C	Avelino	Navarro Vidal	697777888	avelino.navarro@mail.com	Avelino#456	P107	C001
89898989D	Basilisa	Lozano Campos	698888999	basilisa.lozano@mail.com	Basilisa!Pass	P108	C002
90901212E	Plácido	Herrera Soler	699999000	placido.herrera@mail.com	Placido@2024	P109	C003
01012323F	Escolástica	Carmona Navarro	700000111	escolastica.carmona@mail.com	Escolas$789	P110	C004
11223366G	Saturnino	Prieto Lozano	701111222	saturnino.prieto@mail.com	Saturnino!2024	P111	\N
22334477H	Tránsito	Reyes Herrera	702222333	transito.reyes@mail.com	Transito#Pass	P112	\N
33445588I	Hilario	Medina Carmona	703333444	hilario.medina@mail.com	Hilario@456	P113	C005
44556699J	Digna	Garrido Prieto	704444555	digna.garrido@mail.com	Digna$2024	P114	C006
55667700K	Abundio	Morales Reyes	705555666	abundio.morales@mail.com	Abundio!789	P115	C007
66778811L	Primitiva	Sánchez Medina	706666777	primitiva.sanchez@mail.com	Primitiva@123	P116	C008
77889922M	Hipólito	León Garrido	707777888	hipolito.leon@mail.com	Hipolito#456	P117	C009
88990033N	Cándida	Romero Morales	708888999	candida.romero@mail.com	Candida!Pass	P118	C010
99001144O	Fulgencio	Torres Sánchez	709999000	fulgencio.torres@mail.com	Fulgencio@2024	P119	C011
00112244P	Salvadora	García León	710000111	salvadora.garcia@mail.com	Salvadora$789	P120	C012
\.


--
-- TOC entry 5203 (class 0 OID 0)
-- Dependencies: 239
-- Name: tickets_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tickets_id_seq', 10, true);


--
-- TOC entry 4963 (class 2606 OID 28649)
-- Name: anuncio anuncio_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.anuncio
    ADD CONSTRAINT anuncio_pkey PRIMARY KEY (codanuncio);


--
-- TOC entry 4969 (class 2606 OID 28669)
-- Name: calistenia calistenia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.calistenia
    ADD CONSTRAINT calistenia_pkey PRIMARY KEY (codentrena);


--
-- TOC entry 4975 (class 2606 OID 28708)
-- Name: cardio cardio_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cardio
    ADD CONSTRAINT cardio_pkey PRIMARY KEY (codentrena);


--
-- TOC entry 4979 (class 2606 OID 28732)
-- Name: club club_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.club
    ADD CONSTRAINT club_pkey PRIMARY KEY (codclub);


--
-- TOC entry 4957 (class 2606 OID 28631)
-- Name: ejercicio ejercicio_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ejercicio
    ADD CONSTRAINT ejercicio_pkey PRIMARY KEY (codejercicio);


--
-- TOC entry 4977 (class 2606 OID 28721)
-- Name: elasticidad elasticidad_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.elasticidad
    ADD CONSTRAINT elasticidad_pkey PRIMARY KEY (codentrena);


--
-- TOC entry 4959 (class 2606 OID 28637)
-- Name: entrenamiento entrenamiento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.entrenamiento
    ADD CONSTRAINT entrenamiento_pkey PRIMARY KEY (codentrenamiento);


--
-- TOC entry 4965 (class 2606 OID 28655)
-- Name: evento evento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.evento
    ADD CONSTRAINT evento_pkey PRIMARY KEY (codevento);


--
-- TOC entry 4971 (class 2606 OID 28682)
-- Name: hipertrofia hipertrofia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.hipertrofia
    ADD CONSTRAINT hipertrofia_pkey PRIMARY KEY (codentrena);


--
-- TOC entry 4967 (class 2606 OID 28661)
-- Name: logro logro_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.logro
    ADD CONSTRAINT logro_pkey PRIMARY KEY (codlogro);


--
-- TOC entry 4995 (class 2606 OID 28842)
-- Name: obtener obtener_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.obtener
    ADD CONSTRAINT obtener_pkey PRIMARY KEY (dni_usuario, cod_logro);


--
-- TOC entry 4987 (class 2606 OID 28780)
-- Name: participar participar_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.participar
    ADD CONSTRAINT participar_pkey PRIMARY KEY (cod_evento, dni_usuario);


--
-- TOC entry 4961 (class 2606 OID 28643)
-- Name: plan_alimenticio plan_alimenticio_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.plan_alimenticio
    ADD CONSTRAINT plan_alimenticio_pkey PRIMARY KEY (codplan);


--
-- TOC entry 4973 (class 2606 OID 28695)
-- Name: powerlifting powerlifting_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.powerlifting
    ADD CONSTRAINT powerlifting_pkey PRIMARY KEY (codentrena);


--
-- TOC entry 4997 (class 2606 OID 28859)
-- Name: progreso progreso_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.progreso
    ADD CONSTRAINT progreso_pkey PRIMARY KEY (codprogreso);


--
-- TOC entry 4991 (class 2606 OID 28814)
-- Name: realizar realizar_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.realizar
    ADD CONSTRAINT realizar_pkey PRIMARY KEY (dni_usuario, cod_entrenamiento);


--
-- TOC entry 4993 (class 2606 OID 28830)
-- Name: recomendaciones recomendaciones_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recomendaciones
    ADD CONSTRAINT recomendaciones_pkey PRIMARY KEY (codrecomendacion);


--
-- TOC entry 4989 (class 2606 OID 28797)
-- Name: tener_amigos tener_amigos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tener_amigos
    ADD CONSTRAINT tener_amigos_pkey PRIMARY KEY (amigo1, amigo2);


--
-- TOC entry 4985 (class 2606 OID 28758)
-- Name: tener tener_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tener
    ADD CONSTRAINT tener_pkey PRIMARY KEY (cod_evento, cod_anuncio);


--
-- TOC entry 4999 (class 2606 OID 28886)
-- Name: tickets tickets_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT tickets_pkey PRIMARY KEY (id);


--
-- TOC entry 4981 (class 2606 OID 28740)
-- Name: usuario usuario_codplan_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_codplan_key UNIQUE (codplan);


--
-- TOC entry 4983 (class 2606 OID 28738)
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (dni);


--
-- TOC entry 5021 (class 2620 OID 28897)
-- Name: usuario tr_capacidad; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tr_capacidad BEFORE INSERT ON public.usuario FOR EACH ROW EXECUTE FUNCTION public.f_control_capacidad();


--
-- TOC entry 5024 (class 2620 OID 28898)
-- Name: obtener tr_no_duplicar_logro; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tr_no_duplicar_logro BEFORE INSERT ON public.obtener FOR EACH ROW EXECUTE FUNCTION public.f_no_logro_duplicado();


--
-- TOC entry 5022 (class 2620 OID 28899)
-- Name: usuario tr_un_solo_plan; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tr_un_solo_plan BEFORE INSERT OR UPDATE ON public.usuario FOR EACH ROW EXECUTE FUNCTION public.f_un_solo_plan();


--
-- TOC entry 5023 (class 2620 OID 28896)
-- Name: usuario trigger_actualizar_miembros; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER trigger_actualizar_miembros AFTER INSERT OR DELETE OR UPDATE ON public.usuario FOR EACH ROW EXECUTE FUNCTION public.actualizar_num_miembros();


--
-- TOC entry 5000 (class 2606 OID 28670)
-- Name: calistenia fk_calistenia; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.calistenia
    ADD CONSTRAINT fk_calistenia FOREIGN KEY (codentrena) REFERENCES public.entrenamiento(codentrenamiento);


--
-- TOC entry 5003 (class 2606 OID 28709)
-- Name: cardio fk_cardio; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cardio
    ADD CONSTRAINT fk_cardio FOREIGN KEY (codentrena) REFERENCES public.entrenamiento(codentrenamiento);


--
-- TOC entry 5005 (class 2606 OID 28865)
-- Name: club fk_club_propietario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.club
    ADD CONSTRAINT fk_club_propietario FOREIGN KEY (dni_propietario) REFERENCES public.usuario(dni);


--
-- TOC entry 5004 (class 2606 OID 28722)
-- Name: elasticidad fk_elasticidad; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.elasticidad
    ADD CONSTRAINT fk_elasticidad FOREIGN KEY (codentrena) REFERENCES public.entrenamiento(codentrenamiento);


--
-- TOC entry 5001 (class 2606 OID 28683)
-- Name: hipertrofia fk_hipertrofia; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.hipertrofia
    ADD CONSTRAINT fk_hipertrofia FOREIGN KEY (codentrena) REFERENCES public.entrenamiento(codentrenamiento);


--
-- TOC entry 5002 (class 2606 OID 28696)
-- Name: powerlifting fk_powerlifting; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.powerlifting
    ADD CONSTRAINT fk_powerlifting FOREIGN KEY (codentrena) REFERENCES public.entrenamiento(codentrenamiento);


--
-- TOC entry 5018 (class 2606 OID 28848)
-- Name: obtener obtener_cod_logro_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.obtener
    ADD CONSTRAINT obtener_cod_logro_fkey FOREIGN KEY (cod_logro) REFERENCES public.logro(codlogro);


--
-- TOC entry 5019 (class 2606 OID 28843)
-- Name: obtener obtener_dni_usuario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.obtener
    ADD CONSTRAINT obtener_dni_usuario_fkey FOREIGN KEY (dni_usuario) REFERENCES public.usuario(dni);


--
-- TOC entry 5011 (class 2606 OID 28781)
-- Name: participar participar_cod_evento_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.participar
    ADD CONSTRAINT participar_cod_evento_fkey FOREIGN KEY (cod_evento) REFERENCES public.evento(codevento);


--
-- TOC entry 5012 (class 2606 OID 28786)
-- Name: participar participar_dni_usuario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.participar
    ADD CONSTRAINT participar_dni_usuario_fkey FOREIGN KEY (dni_usuario) REFERENCES public.usuario(dni);


--
-- TOC entry 5020 (class 2606 OID 28860)
-- Name: progreso progreso_dni_usuario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.progreso
    ADD CONSTRAINT progreso_dni_usuario_fkey FOREIGN KEY (dni_usuario) REFERENCES public.usuario(dni);


--
-- TOC entry 5015 (class 2606 OID 28820)
-- Name: realizar realizar_cod_entrenamiento_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.realizar
    ADD CONSTRAINT realizar_cod_entrenamiento_fkey FOREIGN KEY (cod_entrenamiento) REFERENCES public.entrenamiento(codentrenamiento);


--
-- TOC entry 5016 (class 2606 OID 28815)
-- Name: realizar realizar_dni_usuario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.realizar
    ADD CONSTRAINT realizar_dni_usuario_fkey FOREIGN KEY (dni_usuario) REFERENCES public.usuario(dni);


--
-- TOC entry 5017 (class 2606 OID 28831)
-- Name: recomendaciones recomendaciones_dni_usuario_cod_entrenamiento_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recomendaciones
    ADD CONSTRAINT recomendaciones_dni_usuario_cod_entrenamiento_fkey FOREIGN KEY (dni_usuario, cod_entrenamiento) REFERENCES public.realizar(dni_usuario, cod_entrenamiento);


--
-- TOC entry 5013 (class 2606 OID 28798)
-- Name: tener_amigos tener_amigos_amigo1_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tener_amigos
    ADD CONSTRAINT tener_amigos_amigo1_fkey FOREIGN KEY (amigo1) REFERENCES public.usuario(dni);


--
-- TOC entry 5014 (class 2606 OID 28803)
-- Name: tener_amigos tener_amigos_amigo2_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tener_amigos
    ADD CONSTRAINT tener_amigos_amigo2_fkey FOREIGN KEY (amigo2) REFERENCES public.usuario(dni);


--
-- TOC entry 5008 (class 2606 OID 28764)
-- Name: tener tener_cod_anuncio_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tener
    ADD CONSTRAINT tener_cod_anuncio_fkey FOREIGN KEY (cod_anuncio) REFERENCES public.anuncio(codanuncio);


--
-- TOC entry 5009 (class 2606 OID 28769)
-- Name: tener tener_cod_club_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tener
    ADD CONSTRAINT tener_cod_club_fkey FOREIGN KEY (cod_club) REFERENCES public.club(codclub);


--
-- TOC entry 5010 (class 2606 OID 28759)
-- Name: tener tener_cod_evento_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tener
    ADD CONSTRAINT tener_cod_evento_fkey FOREIGN KEY (cod_evento) REFERENCES public.evento(codevento);


--
-- TOC entry 5006 (class 2606 OID 28746)
-- Name: usuario usuario_cod_club_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_cod_club_fkey FOREIGN KEY (cod_club) REFERENCES public.club(codclub);


--
-- TOC entry 5007 (class 2606 OID 28741)
-- Name: usuario usuario_codplan_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_codplan_fkey FOREIGN KEY (codplan) REFERENCES public.plan_alimenticio(codplan);


-- Completed on 2026-05-30 16:34:24

--
-- PostgreSQL database dump complete
--

\unrestrict WFQMmxFtIFV5h9TNQg1kcPnLeemckiSnau3ubclR9chopYiGbjjYUn3Zfk5nnkt

