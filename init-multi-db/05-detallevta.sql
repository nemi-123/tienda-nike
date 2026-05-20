-- 1) posicionarse en la bd
\c detallevta;

-- 2) eliminar tablas
drop table if exists detallevta;
drop table if exists venta;
drop table if exists producto;

-- 3) crear tablas

create table producto (
  idproducto numeric primary key,
  nombre varchar(50),
  precio numeric
);

create table venta (
  idventa numeric primary key,
  fecha varchar(10),
  total numeric
);

create table detallevta (
  iddetalle numeric primary key,
  cantidad numeric,
  subtotal numeric,
  idventa numeric,
  idproducto numeric,
  foreign key (idventa) references venta(idventa),
  foreign key (idproducto) references producto(idproducto)
);

-- 4) poblar tablas

insert into producto values (1, 'zapatilla nike', 80000);
insert into producto values (2, 'zapatilla adidas', 90000);
insert into producto values (3, 'zapatilla puma', 70000);

insert into venta values (1, '01-04-2024', 160000);
insert into venta values (2, '05-04-2024', 180000);
insert into venta values (3, '10-04-2024', 70000);

insert into detallevta values (1, 2, 160000, 1, 1);
insert into detallevta values (2, 2, 180000, 2, 2);
insert into detallevta values (3, 1, 70000, 3, 3);