drop table tarjetas;
drop sequence tarjetas_seq;
drop table clientes;
drop sequence clientes_seq;
create table clientes (
	id_cliente     number(3) NOT NULL,
	cedula         number(10) NOT NULL,
	nombres        varchar2(200) NOT NULL,
	apellidos      varchar2(200) NOT NULL,
	direccion      varchar2(120) NOT NULL,
	telefono       number(10) NOT NULL
);
alter table clientes add constraint clientes_pk primary key(id_cliente);
create sequence clientes_seq;

CREATE OR REPLACE TRIGGER trg_BI_clientes
BEFORE INSERT on clientes FOR EACH ROW
DECLARE
  emp_age number;
BEGIN
  select clientes_seq.nextval
  into   :new.id_cliente
  from   dual;
END;
/
INSERT INTO clientes  (cedula,nombres,apellidos,direccion,telefono ) VALUES (79488379,'aldrin enrique','gutierrez gonzalez','calle 1',3002753812);
INSERT INTO clientes  (cedula,nombres,apellidos,direccion,telefono ) VALUES (52057650,'lilian tatiana','lopez pinto','calle 127',3123567890);

select *
from   clientes;
/
-------------------------------------------------------------------------------------
create table tarjetas (
	id_tarjeta  number(3) NOT NULL ,
	id_cliente  number(3) NOT NULL ,
	numero      number(16) NOT NULL,
	fec_vence   date NOT NULL,
	banco       varchar2(50) NOT NULL,
	predeterminada varchar2(1) default 'N' NOT NULL  
);
alter table tarjetas add constraint tarjetas_pk primary key(id_tarjeta);
alter table tarjetas add constraint tarjetas_clientes_pk foreign key(id_cliente) references clientes(id_cliente) ;
alter table tarjetas add constraint check_predeterminada check (predeterminada IN ('S', 'N'));
create sequence tarjetas_seq;

CREATE OR REPLACE TRIGGER trg_BI_tarjetas
BEFORE INSERT on tarjetas FOR EACH ROW
DECLARE
  emp_age number;
BEGIN
  select tarjetas_seq.nextval
  into   :new.id_tarjeta
  from   dual;
END;
/
INSERT INTO tarjetas  (id_cliente,numero,fec_vence,banco) VALUES (1,'1234567890123456',sysdate,'davivienda');
INSERT INTO tarjetas  (id_cliente,numero,fec_vence,banco) VALUES (2,'2234567890123456',sysdate,'bancolombia');
INSERT INTO tarjetas  (id_cliente,numero,fec_vence,banco) VALUES (2,'3234567890123456',sysdate,'bogota');

select *
from   tarjetas;

