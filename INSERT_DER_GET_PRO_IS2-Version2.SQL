--1. MODULOS
--2. ROLES 
--3. USUARIOS
--4. ROL_USUARIO
--5. ELIMINAR USUARIO: ELIMINAR TABLA ROL_USUARIO , ELIMINAR USUARIO
--insert into sprint (NOMBRE_SPRINT,FECHA_INICIO) values ('nombre',sysdate);
--1. MODULOS
INSERT INTO MODULOS ( id_modulo,nombre_modulo,descripcion) VALUES (1,'PROYECTO','DESCRIPCION PRYECTO');
INSERT INTO MODULOS ( id_modulo,nombre_modulo,descripcion) VALUES (2,'BACKLOG','DESCRIPCION BACKLOG');
INSERT INTO MODULOS ( id_modulo,nombre_modulo,descripcion) VALUES (3,'SPRINT','DESCRIPCION SPRINT');
INSERT INTO MODULOS ( id_modulo,nombre_modulo,descripcion) VALUES (4,'USER_HISTORY','DESCRIPCION USER_HISTORY');
INSERT INTO MODULOS ( id_modulo,nombre_modulo,descripcion) VALUES (5,'KANBAN','DESCRIPCION KANBAN');

--2-  ROL
INSERT INTO ROL ( id_rol,nombre_rol,id_modulo) VALUES (1,'SCRUM MASTER',1);
INSERT INTO ROL ( id_rol,nombre_rol,id_modulo) VALUES (1,'SCRUM MASTER',2);
INSERT INTO ROL ( id_rol,nombre_rol,id_modulo) VALUES (1,'SCRUM MASTER',3);
INSERT INTO ROL ( id_rol,nombre_rol,id_modulo) VALUES (1,'SCRUM MASTER',4);    
INSERT INTO ROL ( id_rol,nombre_rol,id_modulo) VALUES (2,'PRODUCT OWNER',1); 
INSERT INTO ROL ( id_rol,nombre_rol,id_modulo) VALUES (2,'PRODUCT OWNER',2);
INSERT INTO ROL ( id_rol,nombre_rol,id_modulo) VALUES (2,'PRODUCT OWNER',3);
INSERT INTO ROL ( id_rol,nombre_rol,id_modulo) VALUES (2,'PRODUCT OWNER',4);
INSERT INTO ROL ( id_rol,nombre_rol,id_modulo) VALUES (3,'DEVELOPMENT TEAM',1);
INSERT INTO ROL ( id_rol,nombre_rol,id_modulo) VALUES (3,'DEVELOPMENT TEAM',2);
INSERT INTO ROL ( id_rol,nombre_rol,id_modulo) VALUES (3,'DEVELOPMENT TEAM',3);
INSERT INTO ROL ( id_rol,nombre_rol,id_modulo) VALUES (3,'DEVELOPMENT TEAM',4);

--3 USUARIO
INSERT INTO USUARIO (id_usuario,nombre_usuario,contrasena,nombre,apellido,email,telefono)
VALUES(1,'VGOMEZ',12345678,'VANESA','GOMEZ','VANESA.SALDIVAR@GMAIL.COM','0985264992');
INSERT INTO USUARIO (id_usuario,nombre_usuario,contrasena,nombre,apellido,email,telefono)
VALUES(,2'DALCARAZ',12345678,'DARIO','ALCARAZ','DARIO@GMAIL.COM','0985264996');
INSERT INTO USUARIO (id_usuario,nombre_usuario,contrasena,nombre,apellido,email,telefono)
VALUES(3,'ABORJA',12345678,'ADRIAN','BORJA','ADRIAN8@GMAIL.COM','0985264992');
INSERT INTO USUARIO (id_usuario,nombre_usuario,contrasena,nombre,apellido,email,telefono)
VALUES(4,'JGALEANO',12345678,'JUAN','GALEANO','JUAN@GMAIL.COM','0985264992');

--4 ROL USUARIO
INSERT INTO ROL_USUARIO( ID_ROL_USUARIO, ID_USUARIO, ID_ROL, ID_PROYECTO) VALUES 
(1,3,1,1);

INSERT INTO ROL_USUARIO( ID_ROL_USUARIO, ID_USUARIO, ID_ROL, ID_PROYECTO) VALUES 
(2,2,3,1);
INSERT INTO ROL_USUARIO( ID_ROL_USUARIO, ID_USUARIO, ID_ROL, ID_PROYECTO) VALUES 
(3,1,2,1);
INSERT INTO ROL_USUARIO( ID_ROL_USUARIO, ID_USUARIO, ID_ROL, ID_PROYECTO) VALUES 
(4,4,4,1);

--5 INSERT PROYECTO
