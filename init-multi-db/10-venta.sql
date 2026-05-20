-- 1) posicionarse en la bd
\c venta;

-- 2) eliminar tablas
drop table if exists detallevta;
drop table if exists venta;
drop table if exists cliente;

-- 3) crear tablas

create table cliente (
  idcliente numeric primary key,
  nombre varchar(50)
);

create table venta (
  idventa numeric primary key,
  fecha varchar(10),
  total numeric,
  idcliente numeric,
  foreign key (idcliente) references cliente(idcliente)
);

create table detallevta (
  iddetalle numeric primary key,
  cantidad numeric,
  subtotal numeric,
  idventa numeric,
  foreign key (idventa) references venta(idventa)
);

-- 4) poblar tablas

insert into cliente values (1, 'juan');
insert into cliente values (2, 'ana');
insert into cliente values (3, 'pedro');

insert into venta values (1, '01-07-2024', 50000, 1);
insert into venta values (2, '05-07-2024', 70000, 2);
insert into venta values (3, '10-07-2024', 60000, 3);

insert into detallevta values (1, 2, 50000, 1);
insert into detallevta values (2, 3, 70000, 2);
insert into detallevta values (3, 1, 60000, 3);