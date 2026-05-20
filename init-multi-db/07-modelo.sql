-- 1) posicionarse en la bd
\c modelo;

-- 2) eliminar tablas
drop table if exists modelo;
drop table if exists marca;
drop table if exists categoria;

-- 3) crear tablas

create table categoria (
  idcategoria numeric primary key,
  nombre varchar(50)
);

create table marca (
  idmarca numeric primary key,
  nombre varchar(50)
);

create table modelo (
  idmodelo numeric primary key,
  nombre varchar(50),
  precio numeric,
  idmarca numeric,
  idcategoria numeric,
  foreign key (idmarca) references marca(idmarca),
  foreign key (idcategoria) references categoria(idcategoria)
);

-- 4) poblar tablas

insert into categoria values (1, 'deportiva');
insert into categoria values (2, 'casual');
insert into categoria values (3, 'urbana');

insert into marca values (1, 'nike');
insert into marca values (2, 'adidas');
insert into marca values (3, 'puma');

insert into modelo values (1, 'air max', 120000, 1, 1);
insert into modelo values (2, 'ultraboost', 150000, 2, 1);
insert into modelo values (3, 'suede classic', 90000, 3, 2);