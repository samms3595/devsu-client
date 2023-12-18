drop database clientes;
create database clientes;
use clientes;
CREATE USER 'clientes'@'localhost' IDENTIFIED BY 'Devsu123.';

create table cliente_cuentas (cliente_id bigint not null, cuentas bigint) engine=InnoDB;
create table persona (dtype varchar(31) not null,
                      id bigint not null,
                      apellidos varchar(255),
                      direccion varchar(255),
                      edad integer not null check ((edad>=1) and (edad<=150)),
                      genero enum ('FEMENINO','MASCULINO','NO_ESPECIFICA') not null,
                      identificacion varchar(255),
                      nombre varchar(255),
                      telefono varchar(255),
                      client_id bigint,
                      estado tinyint check (estado between 0 and 1),
                      password varchar(255), primary key (id)) engine=InnoDB;
GRANT ALL PRIVILEGES ON clientes TO 'clientes'@'localhost';
FLUSH PRIVILEGES;