-- 1) posicionarse en la bd
\c categoria;

-- 2) eliminar tablas
drop table if exists producto;
drop table if exists categoria;
drop table if exists tipo;

-- 3) crear tablas

create table tipo (
  id_tipo numeric primary key,
  nombre varchar(50)
);

create table categoria (
  id_categoria numeric primary key,
  nombre varchar(50),
  id_tipo numeric,
  foreign key (id_tipo) references tipo(id_tipo)
);

create table producto (
  id_producto numeric primary key,
  nombre varchar(50),
  precio numeric,
  id_categoria numeric,
  foreign key (id_categoria) references categoria(id_categoria)
);

-- 4) poblar tablas

insert into tipo values (1, 'deportiva');
insert into tipo values (2, 'casual');
insert into tipo values (3, 'urbana');

insert into categoria values (1, 'running', 1);
insert into categoria values (2, 'skate', 3);
insert into categoria values (3, 'entrenamiento', 1);

insert into producto values (1, 'zapatilla run pro', 80000, 1);
insert into producto values (2, 'zapatilla skate max', 70000, 2);
insert into producto values (3, 'zapatilla gym flex', 60000, 3);