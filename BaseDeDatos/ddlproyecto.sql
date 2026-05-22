/*
DROP TABLE IF EXISTS PROGRESO CASCADE;
DROP TABLE IF EXISTS OBTENER CASCADE;
DROP TABLE IF EXISTS LOGRO CASCADE;
DROP TABLE IF EXISTS RECOMENDACIONES CASCADE;
DROP TABLE IF EXISTS REALIZAR CASCADE;
DROP TABLE IF EXISTS TENER_AMIGOS CASCADE;
DROP TABLE IF EXISTS PARTICIPAR CASCADE;
DROP TABLE IF EXISTS TENER CASCADE;
DROP TABLE IF EXISTS USUARIO CASCADE;
DROP TABLE IF EXISTS EVENTO CASCADE;
DROP TABLE IF EXISTS ANUNCIO CASCADE;
DROP TABLE IF EXISTS CLUB CASCADE;
DROP TABLE IF EXISTS PLAN_ALIMENTICIO CASCADE;
DROP TABLE IF EXISTS ELASTICIDAD CASCADE;
DROP TABLE IF EXISTS CARDIO CASCADE;
DROP TABLE IF EXISTS POWERLIFTING CASCADE;
DROP TABLE IF EXISTS HIPERTROFIA CASCADE;
DROP TABLE IF EXISTS CALISTENIA CASCADE;
DROP TABLE IF EXISTS ENTRENAMIENTO CASCADE;
DROP TABLE IF EXISTS EJERCICIO CASCADE;
*/

CREATE TABLE EJERCICIO(
	CodEjercicio VARCHAR(5) PRIMARY KEY,
	nombre VARCHAR(30),
	series NUMERIC(2),
	repeticiones NUMERIC(2)
);

CREATE TABLE ENTRENAMIENTO(
    CodEntrenamiento VARCHAR(5) PRIMARY KEY,
    dificultad VARCHAR(20),
    duracion NUMERIC(3),
    cantidad NUMERIC(2),
    descripcion VARCHAR(300)
);

CREATE TABLE PLAN_ALIMENTICIO(
	CodPlan VARCHAR(5) PRIMARY KEY,
	calorias_diarias NUMERIC(5),
	proteinas NUMERIC(4,1),
	grasas NUMERIC(4,1),
	carbohidratos NUMERIC(4,1)
);

CREATE TABLE ANUNCIO (
	codAnuncio VARCHAR(5) PRIMARY KEY,
	titulo VARCHAR(40),
	contenido VARCHAR(150),
	fecha_publicacion DATE
);

CREATE TABLE EVENTO (
	codEvento VARCHAR(5) PRIMARY KEY,
	tipo VARCHAR(30),
	descripcion VARCHAR(100),
	ubicacion VARCHAR(50),
	fecha_y_hora TIMESTAMP
);

CREATE TABLE LOGRO (
	codLogro VARCHAR(5) PRIMARY KEY,
	nombre VARCHAR(30),
	descripcion VARCHAR(100)
);

CREATE TABLE CALISTENIA(
    CodEntrena VARCHAR(5) PRIMARY KEY,
    tipo_ejercicio VARCHAR(30),
    lastre_kg DECIMAL(5,2),
    nivel_progresion VARCHAR(20),
    requiere_barras BOOLEAN,
    requiere_anillas BOOLEAN,
    enfoque_habilidad VARCHAR(50),
    trabajo_isometrico BOOLEAN,
    dificultad VARCHAR(10),
    descripcion VARCHAR(500),
    CONSTRAINT FK_calistenia FOREIGN KEY (CodEntrena)
        REFERENCES ENTRENAMIENTO (CodEntrenamiento)
);

CREATE TABLE HIPERTROFIA(
    CodEntrena VARCHAR(5) PRIMARY KEY,
    tecnica VARCHAR(100),
    peso_medio_kg DECIMAL(5,2),
    grupo_muscular_principal VARCHAR(30),
    grupo_muscular_secundario VARCHAR(30),
    rango_repeticiones VARCHAR(10),
    tiempo_bajo_tension INT,
    descanso_entre_series INT,
    tipo_division VARCHAR(30),
    volumen_total_kg DECIMAL(8,2),
    dificultad VARCHAR(10),
    descripcion VARCHAR(500),
    CONSTRAINT FK_hipertrofia FOREIGN KEY (CodEntrena)
        REFERENCES ENTRENAMIENTO (CodEntrenamiento)
);

CREATE TABLE POWERLIFTING(
    CodEntrena VARCHAR(5) PRIMARY KEY,
    peso_maximo_kg DECIMAL(6,2),
    levantamiento_principal VARCHAR(30),
    porcentaje_1RM INT,
    repeticiones_objetivo INT,
    velocidad_barra DECIMAL(3,2),
    usa_equipamiento BOOLEAN,
    tipo_equipamiento VARCHAR(50),
    pausa_en_pecho BOOLEAN,
    profundidad_sentadilla VARCHAR(20),
    dificultad VARCHAR(10),
    descripcion VARCHAR(500),
    CONSTRAINT FK_powerlifting FOREIGN KEY (CodEntrena)
        REFERENCES ENTRENAMIENTO (CodEntrenamiento)
);

CREATE TABLE CARDIO(
    CodEntrena VARCHAR(5) PRIMARY KEY,
    tipo_cardio VARCHAR(30),
    distancia_km DECIMAL(6,2),
    ritmo_promedio VARCHAR(10),
    frecuencia_cardiaca_promedio INT,
    frecuencia_cardiaca_maxima INT,
    zona_entrenamiento VARCHAR(30),
    calorias_quemadas INT,
    tipo_sesion VARCHAR(20),
    inclinacion_promedio DECIMAL(4,1),
    intervalo_trabajo_descanso VARCHAR(10),
    dificultad VARCHAR(10),
    descripcion VARCHAR(500),
    CONSTRAINT FK_cardio FOREIGN KEY (CodEntrena)
        REFERENCES ENTRENAMIENTO (CodEntrenamiento)
);

CREATE TABLE ELASTICIDAD(
    CodEntrena VARCHAR(5) PRIMARY KEY,
    tipo_estiramiento VARCHAR(30),
    nivel_elasticidad INT,
    zona_corporal_principal VARCHAR(30),
    metodo VARCHAR(30),
    tiempo_por_posicion INT,
    incluye_respiracion BOOLEAN,
    requiere_accesorios BOOLEAN,
    tipo_accesorios VARCHAR(50),
    objetivo VARCHAR(50),
    temperatura_muscular VARCHAR(20),
    dificultad VARCHAR(10),
    descripcion VARCHAR(500),
    CONSTRAINT FK_elasticidad FOREIGN KEY (CodEntrena)
        REFERENCES ENTRENAMIENTO (CodEntrenamiento)
);

CREATE TABLE CLUB(
	CodClub VARCHAR(5) PRIMARY KEY,
	nombre VARCHAR(30),
	descripcion VARCHAR(60),
	fechaCreacion DATE,
	num_miembros NUMERIC(4),
	capacidad_miembros NUMERIC(4),
	dni_propietario VARCHAR(9)
);

CREATE TABLE USUARIO (
	DNI VARCHAR(9) PRIMARY KEY,
	nombre VARCHAR(30),
	apellidos VARCHAR(50),
	telefono VARCHAR(15),
	correo_electronico VARCHAR(50),
	contrasena VARCHAR(100),
	codPlan VARCHAR(5) UNIQUE,
	cod_club VARCHAR(5),

	FOREIGN KEY (codPlan) REFERENCES PLAN_ALIMENTICIO(CodPlan),
	FOREIGN KEY (cod_club) REFERENCES CLUB(codClub)
);

CREATE TABLE TENER (
	cod_evento VARCHAR(5),
	cod_anuncio VARCHAR(5),
	cod_club VARCHAR(5) NOT NULL,

	PRIMARY KEY (cod_evento, cod_anuncio),
	FOREIGN KEY (cod_evento) REFERENCES EVENTO(codEvento),
	FOREIGN KEY (cod_anuncio) REFERENCES ANUNCIO(codAnuncio),
	FOREIGN KEY (cod_club) REFERENCES CLUB(codClub)
);

CREATE TABLE PARTICIPAR (
	cod_evento VARCHAR(5),
	dni_usuario VARCHAR(9),

	PRIMARY KEY (cod_evento, dni_usuario),
	FOREIGN KEY (cod_evento) REFERENCES EVENTO(codEvento),
	FOREIGN KEY (dni_usuario) REFERENCES USUARIO(DNI)
);

CREATE TABLE TENER_AMIGOS (
	amigo1 VARCHAR(9),
	amigo2 VARCHAR(9),

	PRIMARY KEY (amigo1, amigo2),
	FOREIGN KEY (amigo1) REFERENCES USUARIO(DNI),
	FOREIGN KEY (amigo2) REFERENCES USUARIO(DNI)
);

CREATE TABLE REALIZAR (
	dni_usuario VARCHAR(9),
	cod_entrenamiento VARCHAR(5),

	PRIMARY KEY (dni_usuario, cod_entrenamiento),
	FOREIGN KEY (dni_usuario) REFERENCES USUARIO(DNI),
	FOREIGN KEY (cod_entrenamiento) REFERENCES ENTRENAMIENTO(CodEntrenamiento)
);

CREATE TABLE RECOMENDACIONES (
	codRecomendacion VARCHAR(5) PRIMARY KEY,
	descripcion VARCHAR(150),
	prioridad NUMERIC(1),
	dni_usuario VARCHAR(9),
	cod_entrenamiento VARCHAR(5),

	FOREIGN KEY (dni_usuario, cod_entrenamiento)
		REFERENCES REALIZAR(dni_usuario, cod_entrenamiento)
);

CREATE TABLE OBTENER (
	dni_usuario VARCHAR(9),
	cod_logro VARCHAR(5),

	PRIMARY KEY (dni_usuario, cod_logro),
	FOREIGN KEY (dni_usuario) REFERENCES USUARIO(DNI),
	FOREIGN KEY (cod_logro) REFERENCES LOGRO(codLogro)
);

CREATE TABLE PROGRESO (
	codProgreso VARCHAR(5) PRIMARY KEY,
	fecha_y_hora TIMESTAMP,
	nivel VARCHAR(20),
	dni_usuario VARCHAR(9) NOT NULL,

	FOREIGN KEY (dni_usuario) REFERENCES USUARIO(DNI)
);

ALTER TABLE CLUB
ADD CONSTRAINT FK_club_propietario 
FOREIGN KEY (dni_propietario) REFERENCES USUARIO(DNI);