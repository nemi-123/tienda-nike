-- 1) posicionarse en la bd
\c marca;

-- 2) eliminar tablas
drop table if exists modelo;
drop table if exists marca;
drop table if exists pais;

-- 3) crear tablas

create table pais (
  idpais numeric primary key,
  nombre varchar(50)
);

create table marca (
  idmarca numeric primary key,
  nombre varchar(50),
  idpais numeric,
  foreign key (idpais) references pais(idpais)
);

create table modelo (
  idmodelo numeric primary key,
  nombre varchar(50),
  precio numeric,
  idmarca numeric,
  foreign key (idmarca) references marca(idmarca)
);

-- 4) poblar tablas

insert into pais values (1, 'chile');
insert into pais values (2, 'usa');
insert into pais values (3, 'alemania');

insert into marca values (1, 'nike', 2);
insert into marca values (2, 'adidas', 3);
insert into marca values (3, 'puma', 3);

insert into modelo values (1, 'air max', 120000, 1);
insert into modelo values (2, 'ultraboost', 150000, 2);
insert into modelo values (3, 'suede classic', 90000, 3);