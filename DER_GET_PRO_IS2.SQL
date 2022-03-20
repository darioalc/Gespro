	CREATE TABLE a_backloog (
    id_backloog            INTEGER NOT NULL,
    id_proyecto            INTEGER NOT NULL,
    enunciado              VARCHAR2(200),
    esfuerzo               VARCHAR2(200),
    prioridad              VARCHAR2(200),
    id_history             INTEGER,
    a_proyecto_id_proyecto INTEGER NOT NULL
)
LOGGING;

ALTER TABLE a_backloog ADD CONSTRAINT backloog_pk PRIMARY KEY ( id_backloog );

CREATE TABLE a_estado (
    id_estado   INTEGER NOT NULL,
    descripcion VARCHAR2(20)
)
LOGGING;

COMMENT ON COLUMN a_estado.descripcion IS
    '1. Creado
2. Iniciado
3. Cerrado
';

ALTER TABLE a_estado ADD CONSTRAINT estados_pk PRIMARY KEY ( id_estado );

CREATE TABLE a_kanban (
    id_kanban              INTEGER NOT NULL,
    id_estado_kan          INTEGER,
    id_us_his              INTEGER,
    fecha_estado           DATE,
    comentario             VARCHAR2(200),
    a_usr_histo_id_history INTEGER NOT NULL,
    a_tipo_janban_di_tipo  INTEGER NOT NULL,
    a_tipo_kanban_id_tipo  INTEGER NOT NULL
)
LOGGING;

COMMENT ON COLUMN a_kanban.id_kanban IS
    'Una vez iniciado un Sprint el usuario debera visualizar una interfaz con un
tablero Kanban, con tres paneles: 
1: To-Do,
2:Doing,
3: Done.
';

COMMENT ON COLUMN a_kanban.comentario IS
    ' con tres paneles: To-Do, Doing, Done.';

ALTER TABLE a_kanban ADD CONSTRAINT id_kanban_pk PRIMARY KEY ( id_kanban );

CREATE TABLE a_modulo (
    id_modulo     INTEGER NOT NULL,
    nombre_modulo VARCHAR2(30),
    descripcion   VARCHAR2(200)
)
LOGGING;

COMMENT ON COLUMN a_modulo.id_modulo IS
    'A_modulo gestiona el nombre y descripcion de los modulos del sistema
Ejemplo:
Proyecto
Backloog
Sprint 
History 
kanban';

ALTER TABLE a_modulo ADD CONSTRAINT modulo_pk PRIMARY KEY ( id_modulo );

CREATE TABLE a_permiso (
    id_permiso         INTEGER NOT NULL,
    id_modulo          INTEGER NOT NULL,
    tipo_permiso       VARCHAR2(10),
    observacion        VARCHAR2(20),
    a_modulo_id_modulo INTEGER NOT NULL
)
LOGGING;

COMMENT ON COLUMN a_permiso.id_permiso IS
    'A_Autorizacion administra en en base a cada modulo los permisos de
01: creacion,
02: modificacion,
03: visualizacion.

Ejemplo: 
Modulo proyecto permiso de lectura
id autorizacion: 1 
id_modulo: 1 (Proyecto)
tipo_autorizacion: 1 (Lectura).

Modulo proyecto permiso de modificacion o escritura
id_autorizacion: 2
id_modulo: 1(Proyecto)
tipo:autorizacion:2 (Modificacion).

id autorizacion 	id_modulo tipo_autorizacion
1		 1		1
2		 1		2
';

COMMENT ON COLUMN a_permiso.tipo_permiso IS
    'permiso de creacion             - 01
permiso de modificacion        -02
permiso de visualizacion        -03';

COMMENT ON COLUMN a_permiso.observacion IS
    '1 Creacion
2 Modificacion
3 Lectura';

ALTER TABLE a_permiso ADD CONSTRAINT autorizacion_pk PRIMARY KEY ( id_permiso );

CREATE TABLE a_proyecto (
    id_proyecto        INTEGER NOT NULL,
    id_rol_usuario     INTEGER,
    nombre_proyecto    VARCHAR2(30),
    descripcion        VARCHAR2(100),
    fecha_inicio       DATE,
    fecha_fin          DATE,
    id_backloog        INTEGER NOT NULL,
    id_estado          INTEGER,
    estados_id_estados INTEGER NOT NULL
)
LOGGING;

COMMENT ON COLUMN a_proyecto.id_proyecto IS
    'A_Proyecto administra la informacion de los datos de proyecto
Por especificacion del proyecto 
No puede existir un proyecto sin un backlog ni un backlog sin proyecto.
Primero se crea el backloog y luego el proyecto

se puede validar con un trigger: un proyecto sin backloog
';

COMMENT ON COLUMN a_proyecto.id_estado IS
    '1 Abierto
2 En proceso
3.Cerrado
';

ALTER TABLE a_proyecto ADD CONSTRAINT proyecto_pk PRIMARY KEY ( id_proyecto );

CREATE TABLE a_rol (
    id_rol               INTEGER NOT NULL,
    nombre_rol           VARCHAR2(30),
    id_permiso           INTEGER NOT NULL,
    a_permiso_id_permiso INTEGER NOT NULL
)
LOGGING;

COMMENT ON COLUMN a_rol.id_rol IS
    'Tabla que maneja los roles en base al perfil del los usuarios del sistema
Ejemplos: 
1: Scrum Master
2: Product Owner.

Rol de Scrum Master
id_rol: 1 
id_autorizacion: 1

id_rol: 1
id_autorizacion: 2

id_rol 	id_autorizacion
1		 1	
1		 2
1		 3
	
';

COMMENT ON COLUMN a_rol.id_permiso IS
    'Dato referenciado a la tabla A_Autorizacion
En base a cada id_rol se van asignando los permisos/autorizacione
Ejemplo
Id_rol: 01 (Scrum Master)
Id_autorizacion:  1
Es decir Modulo: Proyecto
	Tipo_Autorizacion:Escritura

 Id_rol: 01
 Id_autorizacion: 2
 Es decir Modulo: Sprint 
 	Tipo de autorizacion: Lectura

 Finalmente el id_rol : 1 
 se compone de
id_rol 	id_autorizacion
1	1
1 	2

entre otros

';

ALTER TABLE a_rol ADD CONSTRAINT rol_pk PRIMARY KEY ( id_rol );

CREATE TABLE a_rol_usuario (
    id_rol_usu             INTEGER NOT NULL,
    id_usuario             INTEGER NOT NULL,
    id_rol                 INTEGER NOT NULL,
    id_proyecto            INTEGER NOT NULL,
    a_proyecto_id_proyecto INTEGER NOT NULL,
    a_rol_id_rol           INTEGER NOT NULL
)
LOGGING;

COMMENT ON COLUMN a_rol_usuario.id_rol_usu IS
    'A_Rol_Usuario administra
los roles creados en A_rol.
Los usuarios 
Los proyectos.

Es decir para el proyecto 1 el usuario 1 (Jose) que rol tiene 1 (Scrum Master)
	            proyecto 2 el usuaro  1 (Jose) tiene el rol 2 (Product Owner).

';

ALTER TABLE a_rol_usuario
    ADD CONSTRAINT a_rol_usuario_pk PRIMARY KEY ( id_rol_usu,
                                                  id_usuario,
                                                  id_proyecto );

CREATE TABLE a_sprint (
    id_sprint              INTEGER NOT NULL,
    nombre_sprint          VARCHAR2(150),
    descripcion            VARCHAR2(200),
    id_history             INTEGER NOT NULL,
    fecha_inicio           DATE,
    fecha_fin              DATE,
    id_estado              VARCHAR2(30),
    estados_id_estados     INTEGER NOT NULL,
    a_usr_histo_id_history INTEGER NOT NULL
)
LOGGING;

ALTER TABLE a_sprint ADD CONSTRAINT sprint_pk PRIMARY KEY ( id_sprint );

CREATE TABLE a_tipo_kanban (
    id_tipo       INTEGER NOT NULL,
    nombre_kanban VARCHAR2(20)
)
LOGGING;

COMMENT ON COLUMN a_tipo_kanban.id_tipo IS
    '0: No iniciado
1: To-Do,
2:Doing,
3: Done.
';

COMMENT ON COLUMN a_tipo_kanban.nombre_kanban IS
    '0: No iniciado
1: To-Do,
2:Doing,
3: Done.
';

ALTER TABLE a_tipo_kanban ADD CONSTRAINT a_tipo_janban_pk PRIMARY KEY ( id_tipo );

CREATE TABLE a_usr_histo (
    id_history             INTEGER NOT NULL,
    nombre_history         VARCHAR2(200),
    id_backloog            INTEGER NOT NULL,
    id_usuario             INTEGER NOT NULL,
    a_backloog_id_backloog INTEGER NOT NULL
)
LOGGING;

ALTER TABLE a_usr_histo ADD CONSTRAINT history_pk PRIMARY KEY ( id_history );

CREATE TABLE a_usuario (
    id_usuario INTEGER NOT NULL,
    nombre     VARCHAR2(20),
    apellido   VARCHAR2(200),
	username   VARCHAR2(30),
	contrasena VARCHAR2(30),
    email      VARCHAR2(50),
    telefono   VARCHAR2(30)
)
LOGGING;

COMMENT ON COLUMN a_usuario.id_usuario IS
    'A_usuario maneja los datos basicos de los usuarios del sistema
';

ALTER TABLE a_usuario ADD CONSTRAINT usuario_pk PRIMARY KEY ( id_usuario );

ALTER TABLE a_permiso
    ADD CONSTRAINT a_autorizacion_a_modulo_fk FOREIGN KEY ( a_modulo_id_modulo )
        REFERENCES a_modulo ( id_modulo )
    NOT DEFERRABLE;

ALTER TABLE a_backloog
    ADD CONSTRAINT a_backloog_a_proyecto_fk FOREIGN KEY ( a_proyecto_id_proyecto )
        REFERENCES a_proyecto ( id_proyecto )
    NOT DEFERRABLE;

ALTER TABLE a_usr_histo
    ADD CONSTRAINT a_history_a_backloog_fk FOREIGN KEY ( a_backloog_id_backloog )
        REFERENCES a_backloog ( id_backloog )
    NOT DEFERRABLE;

ALTER TABLE a_kanban
    ADD CONSTRAINT a_kanban_a_tipo_kanban_fk FOREIGN KEY ( a_tipo_kanban_id_tipo )
        REFERENCES a_tipo_kanban ( id_tipo )
    NOT DEFERRABLE;

ALTER TABLE a_proyecto
    ADD CONSTRAINT a_proyecto_estados_fk FOREIGN KEY ( estados_id_estados )
        REFERENCES a_estado ( id_estado )
    NOT DEFERRABLE;

ALTER TABLE a_rol
    ADD CONSTRAINT a_rol_a_permiso_fk FOREIGN KEY ( a_permiso_id_permiso )
        REFERENCES a_permiso ( id_permiso )
    NOT DEFERRABLE;

ALTER TABLE a_rol_usuario
    ADD CONSTRAINT a_rol_usuario_a_proyecto_fk FOREIGN KEY ( a_proyecto_id_proyecto )
        REFERENCES a_proyecto ( id_proyecto )
    NOT DEFERRABLE;

ALTER TABLE a_rol_usuario
    ADD CONSTRAINT a_rol_usuario_a_rol_fk FOREIGN KEY ( a_rol_id_rol )
        REFERENCES a_rol ( id_rol )
    NOT DEFERRABLE;

ALTER TABLE a_sprint
    ADD CONSTRAINT a_sprint_a_usr_histo_fk FOREIGN KEY ( a_usr_histo_id_history )
        REFERENCES a_usr_histo ( id_history )
    NOT DEFERRABLE;

ALTER TABLE a_sprint
    ADD CONSTRAINT a_sprint_estados_fk FOREIGN KEY ( estados_id_estados )
        REFERENCES a_estado ( id_estado )
    NOT DEFERRABLE;

ALTER TABLE a_kanban
    ADD CONSTRAINT id_kanban_a_usr_histo_fk FOREIGN KEY ( a_usr_histo_id_history )
        REFERENCES a_usr_histo ( id_history )
    NOT DEFERRABLE;

ALTER TABLE a_rol_usuario
    ADD CONSTRAINT rol_usuario_usuario_fk FOREIGN KEY ( id_usuario )
        REFERENCES a_usuario ( id_usuario )
    NOT DEFERRABLE;
