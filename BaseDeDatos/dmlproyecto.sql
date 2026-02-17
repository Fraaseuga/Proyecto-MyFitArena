INSERT INTO EJERCICIO VALUES
('E001','Flexiones',4,12),
('E002','Sentadillas',4,15),
('E003','Dominadas',3,8),
('E004','Plancha',3,1),
('E005','Burpees',4,10),
('E006','Zancadas',4,12),
('E007','Press banca',5,5),
('E008','Peso muerto',5,5),
('E009','Curl bíceps',4,10),
('E010','Remo',4,12);

INSERT INTO ENTRENAMIENTO VALUES
('T001','Fácil',30,5,'Entrenamiento básico de movilidad'),
('T002','Media',45,6,'Rutina de fuerza general'),
('T003','Difícil',60,8,'Entrenamiento avanzado'),
('T004','Media',40,5,'Cardio moderado'),
('T005','Fácil',25,4,'Estiramientos guiados'),
('T006','Difícil',70,10,'Powerlifting intensivo'),
('T007','Media',50,7,'Hipertrofia de torso'),
('T008','Fácil',20,3,'Calistenia básica'),
('T009','Difícil',55,8,'Cardio HIIT'),
('T010','Media',45,6,'Elasticidad y movilidad');

INSERT INTO CALISTENIA VALUES
('T001','Movilidad general'),
('T008','Calistenia básica');

INSERT INTO HIPERTROFIA VALUES
('T002','Técnica de repeticiones lentas'),
('T003','Super series'),
('T007','Drop sets');

INSERT INTO POWERLIFTING VALUES
('T006',180.50);

INSERT INTO CARDIO VALUES
('T004',30),
('T009',45);

INSERT INTO ELASTICIDAD VALUES
('T005','Baja'),
('T010','Alta');

INSERT INTO PLAN_ALIMENTICIO VALUES
('P001',2200,120.5,60.2,250.3),
('P002',1800,90.0,50.0,200.0),
('P003',2500,150.0,70.0,300.0),
('P004',2000,110.0,55.0,220.0),
('P005',2700,160.0,80.0,320.0),
('P006',2300,130.0,65.0,260.0),
('P007',1900,95.0,45.0,210.0),
('P008',3000,180.0,90.0,350.0),
('P009',2100,105.0,50.0,230.0),
('P010',2600,140.0,75.0,290.0);

INSERT INTO CLUB VALUES
('C001','Fitness Pro','Club de entrenamiento avanzado','2020-01-10',120),
('C002','CardioMax','Club de cardio y running','2019-05-22',90),
('C003','PowerClub','Powerlifting y fuerza','2021-03-15',70),
('C004','FlexFit','Elasticidad y movilidad','2018-11-30',60),
('C005','NutriLife','Club de nutrición','2022-02-18',40),
('C006','CalisTeam','Calistenia y street workout','2020-07-09',85),
('C007','MuscleLab','Hipertrofia avanzada','2019-09-12',110),
('C008','ZenFit','Bienestar y estiramientos','2021-12-01',50),
('C009','RunFast','Running profesional','2017-04-25',95),
('C010','StrongNation','Fuerza general','2023-01-05',30);

INSERT INTO ANUNCIO VALUES
('A001','Nuevo evento','Participa en nuestro evento mensual','2024-01-10'),
('A002','Promoción','Descuento en planes','2024-02-15'),
('A003','Taller','Taller de nutrición','2024-03-20'),
('A004','Competencia','Competencia de fuerza','2024-04-05'),
('A005','Reunión','Reunión del club','2024-05-12'),
('A006','Aviso','Cambio de horario','2024-06-18'),
('A007','Entrenamiento','Clase especial','2024-07-22'),
('A008','Maratón','Inscripciones abiertas','2024-08-30'),
('A009','Seminario','Seminario de salud','2024-09-14'),
('A010','Fiesta','Fiesta anual del club','2024-10-01');

INSERT INTO EVENTO VALUES
('E001','Competencia','Competencia mensual','Gimnasio','2024-01-15 10:00'),
('E002','Taller','Taller de cocina','Sala 2','2024-02-20 17:00'),
('E003','Charla','Charla motivacional','Auditorio','2024-03-10 12:00'),
('E004','Maratón','Carrera 10K','Ciudad','2024-04-25 09:00'),
('E005','Reunión','Reunión general','Sala 1','2024-05-05 18:00'),
('E006','Competencia','Powerlifting','Gimnasio','2024-06-12 11:00'),
('E007','Yoga','Clase especial','Sala Zen','2024-07-08 08:00'),
('E008','Nutrición','Seminario','Sala 3','2024-08-19 16:00'),
('E009','Entrenamiento','Clase grupal','Pista','2024-09-22 19:00'),
('E010','Fiesta','Fiesta anual','Terraza','2024-10-10 21:00');

INSERT INTO TENER VALUES
('E001','A001','C001'),
('E002','A002','C002'),
('E003','A003','C003'),
('E004','A004','C004'),
('E005','A005','C005'),
('E006','A006','C006'),
('E007','A007','C007'),
('E008','A008','C008'),
('E009','A009','C009'),
('E010','A010','C010');

INSERT INTO USUARIO VALUES
('11111111A','Juan','Pérez López','600111222','juan@mail.com','P001','C001'),
('22222222B','Ana','García Ruiz','600222333','ana@mail.com','P002','C002'),
('33333333C','Luis','Martínez Gil','600333444','luis@mail.com','P003','C003'),
('44444444D','Marta','Sánchez Díaz','600444555','marta@mail.com','P004','C004'),
('55555555E','Carlos','López Vega','600555666','carlos@mail.com','P005','C005'),
('66666666F','Laura','Torres Soler','600666777','laura@mail.com','P006','C006'),
('77777777G','David','Hernández Mora','600777888','david@mail.com','P007','C007'),
('88888888H','Sara','Navarro León','600888999','sara@mail.com','P008','C008'),
('99999999I','Pablo','Castro Ríos','600999000','pablo@mail.com','P009','C009'),
('00000000J','Elena','Romero Sáez','600000111','elena@mail.com','P010','C010');

INSERT INTO PARTICIPAR VALUES
('E001','11111111A'),
('E002','22222222B'),
('E003','33333333C'),
('E004','44444444D'),
('E005','55555555E'),
('E006','66666666F'),
('E007','77777777G'),
('E008','88888888H'),
('E009','99999999I'),
('E010','00000000J');

INSERT INTO TENER_AMIGOS VALUES
('11111111A','22222222B'),
('22222222B','33333333C'),
('33333333C','44444444D'),
('44444444D','55555555E'),
('55555555E','66666666F'),
('66666666F','77777777G'),
('77777777G','88888888H'),
('88888888H','99999999I'),
('99999999I','00000000J'),
('00000000J','11111111A');

INSERT INTO REALIZAR VALUES
('11111111A','T001'),
('22222222B','T002'),
('33333333C','T003'),
('44444444D','T004'),
('55555555E','T005'),
('66666666F','T006'),
('77777777G','T007'),
('88888888H','T008'),
('99999999I','T009'),
('00000000J','T010');

INSERT INTO RECOMENDACIONES VALUES
('R001','Mejorar técnica',1,'11111111A','T001'),
('R002','Aumentar intensidad',2,'22222222B','T002'),
('R003','Controlar respiración',1,'33333333C','T003'),
('R004','Mantener ritmo',3,'44444444D','T004'),
('R005','Mayor flexibilidad',2,'55555555E','T005'),
('R006','Progresión de cargas',1,'66666666F','T006'),
('R007','Más volumen',2,'77777777G','T007'),
('R008','Mejor técnica',1,'88888888H','T008'),
('R009','Más resistencia',3,'99999999I','T009'),
('R010','Control postural',2,'00000000J','T010');

INSERT INTO LOGRO VALUES
('L001','Constancia','Entrenar 7 días seguidos'),
('L002','Fuerza','Levantar 100kg'),
('L003','Resistencia','Correr 5km'),
('L004','Flexibilidad','Tocar los pies'),
('L005','Velocidad','Sprint 100m'),
('L006','Potencia','Salto vertical alto'),
('L007','Técnica','Ejecutar ejercicio perfecto'),
('L008','Cardio','30 min sin parar'),
('L009','Progreso','Mejorar marca personal'),
('L010','Disciplina','Cumplir plan mensual');

INSERT INTO OBTENER VALUES
('11111111A','L001'),
('22222222B','L002'),
('33333333C','L003'),
('44444444D','L004'),
('55555555E','L005'),
('66666666F','L006'),
('77777777G','L007'),
('88888888H','L008'),
('99999999I','L009'),
('00000000J','L010');

INSERT INTO PROGRESO VALUES
('PR01','2024-01-10 10:00','Bajo','11111111A'),
('PR02','2024-02-12 11:00','Medio','22222222B'),
('PR03','2024-03-15 12:00','Alto','33333333C'),
('PR04','2024-04-18 13:00','Medio','44444444D'),
('PR05','2024-05-20 14:00','Bajo','55555555E'),
('PR06','2024-06-22 15:00','Alto','66666666F'),
('PR07','2024-07-25 16:00','Medio','77777777G'),
('PR08','2024-08-28 17:00','Bajo','88888888H'),
('PR09','2024-09-30 18:00','Alto','99999999I'),
('PR10','2024-10-05 19:00','Medio','00000000J');