-- 1) posicionarse en la bd
\c compra;

-- 2) eliminar tablas
drop table if exists detallecmp;
drop table if exists compra;
drop table if exists proveedor;

-- 3) crear tablas

create table proveedor (
  id_proveedor numeric primary key,
  nombre varchar(50),
  telefono varchar(20)
);

create table compra (
  id_compra numeric primary key,
  fecha varchar(10),
  total numeric,
  id_proveedor numeric,
  foreign key (id_proveedor) references proveedor(id_proveedor)
);

create table detallecmp (
  id_detalle numeric primary key,
  cantidad numeric,
  precio numeric,
  id_compra numeric,
  foreign key (id_compra) references compra(id_compra)
);

-- 4) poblar tablas

insert into proveedor values (1, 'proveedor 1', '111111111');
insert into proveedor values (2, 'proveedor 2', '222222222');
insert into proveedor values (3, 'proveedor 3', '333333333');

insert into compra values (1, '01-02-2024', 100000, 1);
insert into compra values (2, '05-02-2024', 150000, 2);
insert into compra values (3, '10-02-2024', 120000, 3);

insert into detallecmp values (1, 2, 50000, 1);
insert into detallecmp values (2, 3, 50000, 2);
insert into detallecmp values (3, 1, 120000, 3);