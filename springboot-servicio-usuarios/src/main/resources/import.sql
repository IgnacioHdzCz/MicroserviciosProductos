-- En postgret se maneja como TRUE or False
INSERT INTO usuarios (username,password,enabled,nombre,apellido,email) VALUES ('andres','$2a$10$YqfnX3sGitVosJOmnRl7zuA2I5fMZYqaCEQbebt17Wdz7I2uVTdom',TRUE,'Andres','Hernandez','master@gmail.com');
INSERT INTO usuarios (username,password,enabled,nombre,apellido,email) VALUES ('admin','$2a$10$fB1Qg6LXXcMBJvne93OkwuScE2l/yabWs4qVb2hRiaDOVdAYTN.yK',TRUE,'John','Doe','jhon.doe@gmail.com');

INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id,role_id) VALUES (1,1);
INSERT INTO usuarios_roles (usuario_id,role_id) VALUES (2,2);
INSERT INTO usuarios_roles (usuario_id,role_id) VALUES (2,1);