-- 1) posicionarse en la bd
\c proveedor;

-- 2) eliminar tablas
drop table if exists compra;
drop table if exists proveedor;
drop table if exists ciudad;

-- 3) crear tablas

create table ciudad (
  idciudad numeric primary key,
  nombre varchar(50)
);

create table proveedor (
  idproveedor numeric primary key,
  nombre varchar(50),
  telefono varchar(20),
  idciudad numeric,
  foreign key (idciudad) references ciudad(idciudad)
);

create table compra (
  idcompra numeric primary key,
  fecha varchar(10),
  total numeric,
  idproveedor numeric,
  foreign key (idproveedor) references proveedor(idproveedor)
);

-- 4) poblar tablas

insert into ciudad values (1, 'santiago');
insert into ciudad values (2, 'valparaiso');
insert into ciudad values (3, 'concepcion');

insert into proveedor values (1, 'proveedor1', '111111111', 1);
insert into proveedor values (2, 'proveedor2', '222222222', 2);
insert into proveedor values (3, 'proveedor3', '333333333', 3);

insert into compra values (1, '01-05-2024', 100000, 1);
insert into compra values (2, '05-05-2024', 120000, 2);
insert into compra values (3, '10-05-2024', 90000, 3);