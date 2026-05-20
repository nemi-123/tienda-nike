-- 1) posicionarse en la bd
\c zapatilla;

-- 2) eliminar tablas
drop table if exists zapatilla;
drop table if exists modelo;
drop table if exists marca;

-- 3) crear tablas

create table marca (
  idmarca numeric primary key,
  nombre varchar(50)
);

create table modelo (
  idmodelo numeric primary key,
  nombre varchar(50),
  idmarca numeric,
  foreign key (idmarca) references marca(idmarca)
);

create table zapatilla (
  idzapatilla numeric primary key,
  nombre varchar(50),
  precio numeric,
  stock numeric,
  idmodelo numeric,
  foreign key (idmodelo) references modelo(idmodelo)
);

-- 4) poblar tablas

insert into marca values (1, 'nike');
insert into marca values (2, 'adidas');
insert into marca values (3, 'puma');

insert into modelo values (1, 'air max', 1);
insert into modelo values (2, 'ultraboost', 2);
insert into modelo values (3, 'suede classic', 3);

insert into zapatilla values (1, 'nike air max', 120000, 10, 1);
insert into zapatilla values (2, 'adidas ultraboost', 150000, 8, 2);
insert into zapatilla values (3, 'puma suede', 90000, 15, 3);