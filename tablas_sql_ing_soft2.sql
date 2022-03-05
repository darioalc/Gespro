-- Generado por Oracle SQL Developer Data Modeler 21.4.1.349.1605
--   en:        2022-03-04 06:57:56 PYST
--   sitio:      Oracle Database 21c
--   tipo:      Oracle Database 21c



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE backloog (
    id_backloog      INTEGER NOT NULL,
    id_proyecto      INTEGER NOT NULL,
    id_sprint        INTEGER,
    enunciado        VARCHAR2(200),
    estado           VARCHAR2(200),
    esfuerzo         VARCHAR2(200),
    prioridad        VARCHAR2(200),
    sprint_id_sprint INTEGER NOT NULL,
    id_history       INTEGER
);

ALTER TABLE backloog ADD CONSTRAINT backloog_pk PRIMARY KEY ( id_backloog );

CREATE TABLE history (
    id_history           INTEGER NOT NULL,
    id_backloog          INTEGER NOT NULL,
    backloog_id_backloog INTEGER NOT NULL,
    id_usuario           INTEGER NOT NULL,
    nombre_history       VARCHAR2(200)
);

ALTER TABLE history ADD CONSTRAINT history_pk PRIMARY KEY ( id_history );

CREATE TABLE modulo (
    id_modulo               INTEGER NOT NULL,
    nombre_modulo           VARCHAR2(30),
    descripcion             VARCHAR2(200),
    permiso_id_autorizacion INTEGER NOT NULL
);

ALTER TABLE modulo ADD CONSTRAINT modulo_pk PRIMARY KEY ( id_modulo );

CREATE TABLE permiso (
    id_autorizacion   INTEGER NOT NULL,
    id_modulo         INTEGER,
    tipo_autorizacion VARCHAR2(10)
);

COMMENT ON COLUMN permiso.tipo_autorizacion IS
    'permiso de creación             - 01
permiso de modificacion        -02
permiso de visualización        -03';

ALTER TABLE permiso ADD CONSTRAINT autorizacion_pk PRIMARY KEY ( id_autorizacion );

CREATE TABLE proyecto (
    id_proyecto     INTEGER NOT NULL,
    id_rol_usuario  INTEGER,
    nombre_proyecto VARCHAR2(30),
    descripcion     VARCHAR2(100),
    fecha_inicio    DATE,
    fecha_fin       DATE,
    id_backloog     INTEGER NOT NULL
);

ALTER TABLE proyecto ADD CONSTRAINT proyecto_pk PRIMARY KEY ( id_proyecto );

CREATE TABLE proyecto_backloog (
    proyecto_id_proyecto INTEGER NOT NULL,
    id_backloog          INTEGER,
    backloog_id_backloog INTEGER NOT NULL
);

CREATE TABLE rol (
    id_rol                  INTEGER NOT NULL,
    id_autorizacion         INTEGER NOT NULL,
    permiso_id_autorizacion INTEGER NOT NULL
);

ALTER TABLE rol ADD CONSTRAINT rol_pk PRIMARY KEY ( id_rol,
                                                    id_autorizacion );

CREATE TABLE rol_usuario (
    id_rol_usuario      INTEGER NOT NULL,
    usu_id_usuar        INTEGER NOT NULL,
    rol_id_rol          INTEGER NOT NULL,
    id_proyecto         INTEGER NOT NULL,
    rol_id_autorizacion INTEGER NOT NULL
);

ALTER TABLE rol_usuario ADD CONSTRAINT rol_usuario_pk PRIMARY KEY ( id_rol_usuario );

CREATE TABLE sprint (
    id_sprint          INTEGER NOT NULL,
    nombre_sprint      VARCHAR2(150),
    descripcion        VARCHAR2(200),
    id_history         INTEGER NOT NULL,
    history_id_history INTEGER NOT NULL,
    fecha_inicio       DATE,
    fecha_fin          DATE,
    estado             VARCHAR2(30)
);

ALTER TABLE sprint ADD CONSTRAINT sprint_pk PRIMARY KEY ( id_sprint );

CREATE TABLE usuario (
    id_usuario INTEGER NOT NULL,
    nombre     VARCHAR2(20),
    apellido   VARCHAR2(200),
    email      VARCHAR2(50),
    telefono   VARCHAR2(30)
);

ALTER TABLE usuario ADD CONSTRAINT usuario_pk PRIMARY KEY ( id_usuario );

CREATE TABLE usuario_proyecto (
    proyecto_id_proyecto INTEGER NOT NULL,
    rol_usu_id_rol       INTEGER NOT NULL
);

ALTER TABLE backloog
    ADD CONSTRAINT backloog_sprint_fk FOREIGN KEY ( sprint_id_sprint )
        REFERENCES sprint ( id_sprint );

ALTER TABLE history
    ADD CONSTRAINT history_backloog_fk FOREIGN KEY ( backloog_id_backloog )
        REFERENCES backloog ( id_backloog );

ALTER TABLE modulo
    ADD CONSTRAINT modulo_permiso_fk FOREIGN KEY ( permiso_id_autorizacion )
        REFERENCES permiso ( id_autorizacion );

ALTER TABLE proyecto_backloog
    ADD CONSTRAINT proyecto_backloog_backloog_fk FOREIGN KEY ( backloog_id_backloog )
        REFERENCES backloog ( id_backloog );

ALTER TABLE proyecto_backloog
    ADD CONSTRAINT proyecto_backloog_proyecto_fk FOREIGN KEY ( proyecto_id_proyecto )
        REFERENCES proyecto ( id_proyecto );

ALTER TABLE rol
    ADD CONSTRAINT rol_permiso_fk FOREIGN KEY ( permiso_id_autorizacion )
        REFERENCES permiso ( id_autorizacion );

ALTER TABLE rol_usuario
    ADD CONSTRAINT rol_usuario_rol_fk FOREIGN KEY ( rol_id_rol,
                                                    rol_id_autorizacion )
        REFERENCES rol ( id_rol,
                         id_autorizacion );

ALTER TABLE rol_usuario
    ADD CONSTRAINT rol_usuario_usuario_fk FOREIGN KEY ( usu_id_usuar )
        REFERENCES usuario ( id_usuario );

ALTER TABLE sprint
    ADD CONSTRAINT sprint_history_fk FOREIGN KEY ( history_id_history )
        REFERENCES history ( id_history );

ALTER TABLE usuario_proyecto
    ADD CONSTRAINT us_proy_proy_fk FOREIGN KEY ( proyecto_id_proyecto )
        REFERENCES proyecto ( id_proyecto );

ALTER TABLE usuario_proyecto
    ADD CONSTRAINT us_proy_rol_us_fk FOREIGN KEY ( rol_usu_id_rol )
        REFERENCES rol_usuario ( id_rol_usuario );



-- Informe de Resumen de Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                            11
-- CREATE INDEX                             0
-- ALTER TABLE                             20
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
