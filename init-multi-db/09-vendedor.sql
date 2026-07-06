\c vendedor;

-- 2) eliminar tablas
drop table if exists venta;
drop table if exists vendedor;
drop table if exists sucursal;

-- 3) crear tablas

create table sucursal (
  idsucursal numeric primary key,
  nombre varchar(50)
);

create table vendedor (
  idvendedor numeric primary key,
  nombre varchar(50),
  
  -- >>> AQUÍ SE AGREGA LA COLUMNA PASSWORD <<<
  password varchar(50), 
  
  sueldo numeric,
  idsucursal numeric,
  foreign key (idsucursal) references sucursal(idsucursal)
);

create table venta (
  idventa numeric primary key,
  fecha varchar(10),
  total numeric,
  idvendedor numeric,
  foreign key (idvendedor) references vendedor(idvendedor)
);

-- 4) poblar tablas

insert into sucursal values (1, 'sucursal centro');
insert into sucursal values (2, 'sucursal norte');
insert into sucursal values (3, 'sucursal sur');

-- >>> AQUÍ EN LOS INSERTS SE AGREGÓ LA CONTRASEÑA DE CADA UNO EN EL TERCER LUGAR <<<
insert into vendedor values (1, 'juan',  'juan123',  500000, 1);
insert into vendedor values (2, 'ana',   'ana123',   600000, 2);
insert into vendedor values (3, 'pedro', 'pedro123', 550000, 3);

insert into venta values (1, '01-06-2024', 80000, 1);
insert into venta values (2, '05-06-2024', 90000, 2);
insert into venta values (3, '10-06-2024', 70000, 3);