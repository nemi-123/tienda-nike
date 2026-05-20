-- 1) posicionarse en la bd
\c cliente;

-- 2) eliminar tablas
drop table if exists compra;
drop table if exists cliente;
drop table if exists ciudad;

-- 3) crear tablas

create table ciudad (
  id_ciudad numeric primary key,
  nombre varchar(50)
);

create table cliente (
  id_cliente numeric primary key,
  nombre varchar(50),
  telefono varchar(20),
  id_ciudad numeric,
  foreign key (id_ciudad) references ciudad(id_ciudad)
);

create table compracli (
  id_compra numeric primary key,
  fecha varchar(10),
  total numeric,
  id_cliente numeric,
  foreign key (id_cliente) references cliente(id_cliente)
);

-- 4) poblar tablas

insert into ciudad values (1, 'santiago');
insert into ciudad values (2, 'valparaiso');
insert into ciudad values (3, 'concepcion');

insert into cliente values (1, 'juan', '123456789', 1);
insert into cliente values (2, 'ana', '987654321', 2);
insert into cliente values (3, 'pedro', '456123789', 3);

insert into compracli values (1, '01-01-2024', 50000, 1);
insert into compracli values (2, '05-01-2024', 70000, 2);
insert into compracli values (3, '10-01-2024', 60000, 3);