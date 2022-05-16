--MODIFICACION DEL DISEÑO DE BASE DE DATOS 
--VERSION 3.0
--TABLAS: 
--1.PROYECTO: TABLA,CONSTRAINTS, SECUENCIA,TRIGGER SECUENCIA, SCRIPT DE INSERT. 
--2.BACKLOG: TABLA, CONSTRAINTS, SECUENCIA, TRIGGER SECUENCIA, TRIGGER DE INSERCION 
--3.SPRINT:TABLA, CONSTRAINTS, SECUENCIA, TRIGGER SECUENCIA, TRIGGER DE INSERCION TABLA
--4-USER_HISTORY: TABLA, CONSTRAINTS, SECUENCIA, TRIGGER SECUENCIA, TRIGGER DE INSERCION DE TABLA. 
--CREACION TABLA PROYECTO 

--El sistema debe poder gestionar más de un proyecto ágil a la vez
CREATE TABLE PROYECTO (
    ID_PROYECTO        INTEGER NOT NULL,
    nombre_proyecto    VARCHAR2(30),
    descripcion        VARCHAR2(300),
    fecha_inicio       DATE,
    fecha_fin          DATE,
    estado             VARCHAR2(20)
);
--CONSTRAINTS DE TABLA PROYECTO PARA VALIDAR QUE FECHA FIN SEA MAYOR QUE FECHA INICIO
ALTER TABLE PROYECTO ADD CONSTRAINT CH_FECHA CHECK (FECHA_FIN >= FECHA_INICIO);
ALTER TABLE PROYECTO ADD CONSTRAINT PROYECTO_PK PRIMARY KEY (ID_PROYECTO);
--SECUENCIA PARA EL CAMPO ID_PROYECTO.
CREATE SEQUENCE seq_proyecto
MINVALUE 1
START WITH 1
INCREMENT BY 1
CACHE 10;
-- TRIGGER PARA ASIGNAR ID_PROYECTO DE SECUENCIA.
--UTILIZA LA SECUENCIA PARA INGRESAR EL NRO EN EL CAMPO ID_PROYECTO
CREATE TRIGGER TR_PROYECTO
BEFORE INSERT ON PROYECTO
FOR EACH ROW 
BEGIN 
SELECT seq_proyecto.NEXTVAL INTO :NEW.ID_PROYECTO FROM DUAL;
END;
-- INSERT PROYECTO ID_PROYECT Y FECHA_FIN NO ES NECESARIO
--INSERT PROYECTO (NOMBRE_PROYECTO,DESCRIPCION,FECHA_INICIO,ESTADO) VALUES ('NOMBRE_PROYECTO','DESCRIPCION',SYSDATE,'PENDIENTE');

--CREACION DE BACKLOG
CREATE TABLE BACKLOG (
    id_backlog          INTEGER NOT NULL,
    id_proyecto          INTEGER,
    enunciado            VARCHAR2(200),
    estado               VARCHAR2(200),
    esfuerzo             VARCHAR2(200),
    prioridad            VARCHAR2(200)
);

COMMENT ON COLUMN backlog.id_proyecto IS
    'Posterior a la creacion de se debe activar el trigger de insercion en la tabla history';

ALTER TABLE backlog ADD CONSTRAINT backlog_pk PRIMARY KEY ( id_backlog );

ALTER TABLE backlog
    ADD CONSTRAINT bk_pro_fk FOREIGN KEY (id_proyecto )
        REFERENCES PROYECTO (id_proyecto);
--Cada backlog debe pertenecer a un solo proyecto
ALTER TABLE backlog
    ADD UNIQUE (id_backlog);
--CREACION DE SECUENCIA PARA BACKLOG

CREATE SEQUENCE seq_backlog
MINVALUE 1
START WITH 1
INCREMENT BY 1
CACHE 10;

--CREACION DE TRIGGER PARA AL MOMENTO DE INSERTAR REGITROS EN LA TABLA BACKLOG 
--UTILIZA LA SECUENCIA PARA INGRESAR EL NRO EN EL CAMPO ID_BACKLOG 
CREATE TRIGGER TR_BACKLOG
BEFORE INSERT ON BACKLOG
FOR EACH ROW 
BEGIN 
SELECT seq_backlog.NEXTVAL INTO :NEW.ID_BACKLOG FROM DUAL;
END;
--TOMA LA SECUENCIA CREADA PARA EL ID_PROYECTO
--CREATE TRIGGER TR_BK_ID_PROYECTO
--BEFORE INSERT ON BACKLOG
--FOR EACH ROW 
--BEGIN 
--SELECT seq_proyecto.NEXTVAL INTO :NEW.ID_PROYECTO FROM DUAL;
--END;
--DROP TRIGGER TR_BK_ID_PROYECTO;

--INSERT BACKLOG (
--DROP TRIGGER TR_BK_ID_PROYECTO;
--CREACION DE TABLA SRINT

--6 AL INSERTAR EN PROYECTO DISPARO EN SPRINT Y LUEGO EN BACKLOG
--SECUENCIA PROYECTO
--No puede existir un proyecto sin un backlog ni un backlog sin proyecto
    CREATE TRIGGER TR_PROY_BACKLOG
    BEFORE INSERT ON PROYECTO
    FOR EACH ROW
    BEGIN 
    --INSERT EN LA TABLA BACKLOG
    -- Cada backlog debe pertenecer a un solo proyecto
     INSERT INTO BACKLOG (ID_PROYECTO,ENUNCIADO,ESTADO,ESFUERZO,PRIORIDAD) 
     VALUES(:NEW.ID_PROYECTO,'Descripcion','Pendiente','10','Normal');
    END; 
--DROP TRIGGER TR_PROY_BACKLOG;
--TABLA CREACION SPRINT 
CREATE TABLE SPRINT(
	ID_SPRINT INTEGER NOT NULL,
    ID_BACKLOG INTEGER NOT NULL,
	NOMBRE_SPRINT VARCHAR2(300),
	FECHA_INICIO DATE,
	FECHA_FIN DATE NULL
);
--CHECK DE TABLA SPRINT PARA VALIDAR QUE FECHA FIN SEA MAYOR QUE FECHA INICIO
ALTER TABLE SPRINT ADD CONSTRAINT CH_FECHA CHECK (FECHA_INICIO < FECHA_FIN);
ALTER TABLE SPRINT ADD CONSTRAINT SPRINT_PK PRIMARY KEY (ID_SPRINT);
ALTER TABLE sprint
    ADD CONSTRAINT sp_bk_fk FOREIGN KEY (id_backlog)
        REFERENCES backlog ( id_backlog );

--CREACION DE SECUENCIA PARA ID_SPRINT
CREATE SEQUENCE seq_sprint
MINVALUE 1
START WITH 1
INCREMENT BY 1
CACHE 10;

--CREACION DE TRIGGER PARA AL MOMENTO DE INSERTAR REGITROS EN LA TALBA SPRINT 
--UTILIZA LA SECUENCIA PARA INGRESAR EL NRO EN EL CAMPO ID_SPRINT 
CREATE TRIGGER TR_SPRINT
BEFORE INSERT ON SPRINT
FOR EACH ROW 
BEGIN 
SELECT seq_sprint.NEXTVAL INTO :NEW.ID_SPRINT FROM DUAL;
END;
--TRIGGER DE INSERCION SPRINT 
--Los Sprints tendrán una duración de 2 semanas por defecto
CREATE TRIGGER TR_BK_SPRINT
BEFORE INSERT ON BACKLOG
FOR EACH ROW 
BEGIN
INSERT INTO SPRINT(ID_BACKLOG,NOMBRE_SPRINT,FECHA_INICIO,FECHA_FIN) 
    VALUES (:NEW.ID_BACKLOG,'NOMBRE SPRINT',SYSDATE,(SYSDATE+15));
END;
--DESC SPRINT;
--DROP TABLE SPRINT;
--DROP TRIGGER TR_BK_SPRINT

--7. POR ENDE CREAR EN LA TABLA BACKLOG, DEBEMOS CREAR LA TABLA HISTORY
CREATE TABLE USER_HISTORY (
    id_history           INTEGER NOT NULL,
    id_backlog          INTEGER NOT NULL,
    usuario              VARCHAR2(20),
    nombre_history       VARCHAR2(200),
    id_sprint            INTEGER NOT NULL
);
--DROP TABLE USER_HISTORY
ALTER TABLE USER_HISTORY ADD CONSTRAINT history_pk PRIMARY KEY (ID_HISTORY);

ALTER TABLE USER_HISTORY
    ADD CONSTRAINT history_backloog_fk FOREIGN KEY ( id_backloog )
        REFERENCES BACKLOG (ID_BACKLOG);

ALTER TABLE USER_HISTORY
    ADD CONSTRAINT history_sprint_fk FOREIGN KEY (ID_SPRINT)
        REFERENCES SPRINT (ID_SPRINT);

CREATE SEQUENCE seq_history
MINVALUE 1
START WITH 1
INCREMENT BY 1
CACHE 10;

--4. CREACION DE TRIGGER PARA QUE AL MOMENTO DE INSERTAR REGITROS EN LA TABLA HISTORY.
--UTILIZA LA SECUENCIA PARA INGRESAR EL NRO EN EL CAMPO ID_HISTORY
CREATE TRIGGER TR_HISTORY
BEFORE INSERT ON USER_HISTORY
FOR EACH ROW 
BEGIN 
SELECT seq_history.NEXTVAL INTO :NEW.ID_HISTORY FROM DUAL;
END;
-- No se debe poder iniciar un Sprint que no contenga US
CREATE TRIGGER TR_SPRIT_HISTORY
BEFORE INSERT ON SPRINT 
FOR EACH ROW 
BEGIN
INSERT INTO USER_HISTORY (ID_BACKLOG,USUARIO,NOMBRE_HISTORY,ID_SPRINT) 
    VALUES (:NEW.ID_BACKLOG, USER,'NOMBRE USER HISTORY',:NEW.ID_SPRINT);
END;

--DROP TRIGGER TR_SPRIT_HISTORY


--CREACION MODULO
CREATE TABLE modulo (
    id_modulo               INTEGER NOT NULL,
    nombre_modulo           VARCHAR2(30),
    descripcion             VARCHAR2(200),
    permiso_id_autorizacion INTEGER NOT NULL
);

ALTER TABLE modulo ADD CONSTRAINT modulo_pk PRIMARY KEY ( id_modulo );

--CREACION ROL
CREATE TABLE rol (
    id_rol           INTEGER NOT NULL,
    nombre_rol       VARCHAR2(50) NOT NULL,
    id_modulo        INTEGER NOT NULL,
    modulo_id_modulo INTEGER NOT NULL
);

ALTER TABLE rol ADD CONSTRAINT rol_pk PRIMARY KEY ( id_rol );

ALTER TABLE rol
    ADD CONSTRAINT rol_modulo_fk FOREIGN KEY ( id_modulo )
        REFERENCES modulo (id_modulo );
--CREACION TABLA USUARIO
CREATE TABLE usuario (
    id_usuario     INTEGER NOT NULL,
    nombre_usuario VARCHAR2(15) NOT NULL,
    nombre         VARCHAR2(20),
    apellido       VARCHAR2(200),
    email          VARCHAR2(50),
    telefono       VARCHAR2(30),
    contraseña     VARCHAR2(15) NOT NULL
);

ALTER TABLE usuario ADD CONSTRAINT usuario_pk PRIMARY KEY ( id_usuario );

ALTER TABLE usuario ADD CONSTRAINT usuario_un UNIQUE ( nombre_usuario );
-- CREACION TABLA ROL_USUARIO
CREATE TABLE rol_usuario (
    id_rol_usuario INTEGER NOT NULL,
    id_usuario     INTEGER NOT NULL,
    id_rol         INTEGER NOT NULL,
    id_proyecto    INTEGER NOT NULL
);
--DROP TABLE ROL_USUARIO;
ALTER TABLE rol_usuario ADD CONSTRAINT rol_usuario_pk PRIMARY KEY ( id_rol_usuario );

ALTER TABLE rol_usuario
    ADD CONSTRAINT rol_usuario_rol_fk FOREIGN KEY ( id_rol )
        REFERENCES rol ( id_rol );

ALTER TABLE rol_usuario
    ADD CONSTRAINT rol_usuario_usuario_fk FOREIGN KEY ( id_usuario )
        REFERENCES usuario ( id_usuario );
CREATE TABLE rol_usuario (
    id_rol_usuario INTEGER NOT NULL,
    id_usuario     INTEGER NOT NULL,
    id_rol         INTEGER NOT NULL,
    id_proyecto    INTEGER NOT NULL
);

ALTER TABLE rol_usuario ADD CONSTRAINT rol_usuario_pk PRIMARY KEY ( id_rol_usuario);
ALTER TABLE rol_usuario
    ADD CONSTRAINT rol_usuario_rol_fk FOREIGN KEY ( id_rol )
        REFERENCES rol ( id_rol );

ALTER TABLE rol_usuario
    ADD CONSTRAINT rol_usuario_usuario_fk FOREIGN KEY ( id_usuario )
        REFERENCES usuario ( id_usuario );
--Cada usuario debe tener permiso a 1 rol por proyecto,
ALTER TABLE ROL_USUARIO
    ADD UNIQUE (id_usuario,id_rol,id_proyecto);
--CREACION MODULO
CREATE TABLE modulo (
    id_modulo               INTEGER NOT NULL,
    nombre_modulo           VARCHAR2(30),
    descripcion             VARCHAR2(200),
    permiso_id_autorizacion INTEGER NOT NULL
);

ALTER TABLE modulo ADD CONSTRAINT modulo_pk PRIMARY KEY ( id_modulo );

--CREACION ROL
CREATE TABLE rol (
    id_rol           INTEGER NOT NULL,
    nombre_rol       VARCHAR2(50) NOT NULL,
    id_modulo        INTEGER NOT NULL,
    modulo_id_modulo INTEGER NOT NULL
);

ALTER TABLE rol ADD CONSTRAINT rol_pk PRIMARY KEY ( id_rol );

ALTER TABLE rol
    ADD CONSTRAINT rol_modulo_fk FOREIGN KEY ( id_modulo )
        REFERENCES modulo (id_modulo );
--CREACION TABLA USUARIO
CREATE TABLE usuario (
    id_usuario     INTEGER NOT NULL,
    nombre_usuario VARCHAR2(15) NOT NULL,
    nombre         VARCHAR2(20),
    apellido       VARCHAR2(200),
    email          VARCHAR2(50),
    telefono       VARCHAR2(30),
    contraseña     VARCHAR2(15) NOT NULL
);

ALTER TABLE usuario ADD CONSTRAINT usuario_pk PRIMARY KEY ( id_usuario );

ALTER TABLE usuario ADD CONSTRAINT usuario__un UNIQUE ( nombre_usuario );
-- CREACION TABLA ROL_USUARIO
CREATE TABLE rol_usuario (
    id_rol_usuario INTEGER NOT NULL,
    id_usuario     INTEGER NOT NULL,
    id_rol         INTEGER NOT NULL,
    id_proyecto    INTEGER NOT NULL
);

ALTER TABLE rol_usuario ADD CONSTRAINT rol_usuario_pk PRIMARY KEY ( id_proyecto,
                                                                    id_usuario );

ALTER TABLE rol_usuario
    ADD CONSTRAINT rol_usuario_rol_fk FOREIGN KEY ( id_rol )
        REFERENCES rol ( id_rol );

ALTER TABLE rol_usuario
    ADD CONSTRAINT rol_usuario_usuario_fk FOREIGN KEY ( id_usuario )
        REFERENCES usuario ( id_usuario );
--Cada usuario debe tener permiso a 1 rol por proyecto,
ALTER TABLE 
    ADD UNIQUE (id_usuario,id_rol,id_proyecto);