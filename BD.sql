drop database if exists db_comparabien;
create database db_comparabien;
use db_comparabien;

drop table if exists tb_generales;
create table tb_generales
(
	id_param varchar(10) primary key not null,
    id_grupo char(5),
    descripcion varchar(50),
    fec_reg date not null
) engine = InnoDB, default charset = utf8;

drop table if exists tb_empresas;
CREATE TABLE tb_empresas
(
    id_emp char(5) primary key not null,
    ruc_empresa char(11) not null,
    nom_empresa varchar(100) not null,
    telf_empresa varchar(10) not null,
    dir_empresa varchar(100) not null,
    email_empresa varchar(50) not null,
    cobertura_dep JSON not null,
    logo JSON,
    fec_reg date not null,
    rubros json
) engine = InnoDB, default charset = utf8;


drop table if exists tb_seguros;
create table tb_seguros
(
	id_seguro char(5) primary key not null,
    id_emp char(5) not null,
    id_tipo char(10) not null,
    desc_seguro varchar(50) not null,
    fec_reg date not null,
    constraint fk_emp_seg foreign key (id_emp) references tb_empresas (id_emp),
    constraint fk_tipo_seg foreign key (id_tipo) references tb_generales (id_param)
) engine = InnoDB, default charset = utf8;

drop table if exists tb_tipo_beneficios;
create table tb_tipo_beneficios
(
	id_bene char(5) primary key not null,
    id_rubro varchar(10) not null,
    desc_tipo_beneficio varchar(50),
    constraint fk_rubro foreign key (id_rubro) references tb_generales (id_param)
) engine = InnoDB, default charset = utf8;

drop table if exists tb_soat;
create table tb_soat
(
	id_seguro char(5) not null,
    tipo_vehiculo char(5) not null,
    marcas json not null,
    modelos json not null,
    cobertura_dep json not null,
    poliza_anual double,    
    fec_reg date not null,
    constraint pk_soat primary key (id_seguro),
    constraint fk_seg_soat foreign key (id_seguro) references
    tb_seguros (id_seguro)
) engine = InnoDB, default charset = utf8;


drop table if exists tb_det_seg_beneficios;
CREATE TABLE tb_det_seg_beneficios (
    id_tipo CHAR(5) NOT NULL,
    id_seg CHAR(5) NOT NULL,
    descripcion VARCHAR(120),
    fec_reg date not null,
    CONSTRAINT fk_bene_tipo FOREIGN KEY (id_tipo)
        REFERENCES tb_tipo_beneficios (id_bene),
    CONSTRAINT fk_seg_det FOREIGN KEY (id_seg)
        REFERENCES tb_seguros (id_seguro),
    CONSTRAINT pk_det_seg_bene PRIMARY KEY (id_tipo , id_seg)
)  engine = InnoDB, default charset = utf8;

/*drop table if exists tb_contactos;
create table tb_contactos
(
	id_contacto char(5) not null primary key,
    id_empresa char(5) not null,
    nom_contacto varchar(80) not null,
    ape_contacto varchar(80) not null,
    telf_contacto varchar(10) not null,
    email_contacto varchar(80) not null,
    foto_contacto varchar(100),
    fec_reg date not null,
    constraint fk_empresa foreign key (id_empresa) references tb_empresas (id_emp)
) engine = InnoDB, default charset = utf8;*/

Drop table if exists tb_roles;
CREATE TABLE tb_roles (
    id_rol TINYINT PRIMARY KEY AUTO_INCREMENT,
    nom_rol VARCHAR(30) NOT NULL,
    fec_reg DATE NOT NULL,
    estado BOOLEAN DEFAULT 1
)engine = InnoDB, default charset = utf8;

Drop table if exists tb_enlaces;
CREATE TABLE tb_enlaces (
    id_enlace TINYINT PRIMARY KEY AUTO_INCREMENT,
    nom_enlace VARCHAR(45) NOT NULL,
    ruta VARCHAR(45) NOT NULL,
    opcion VARCHAR(5),
    fec_reg DATE NOT NULL,
    estado BOOLEAN DEFAULT 1
)engine = InnoDB, default charset = utf8;

Drop table if exists tb_rol_enlaces;
Create table tb_rol_enlaces
(
	id_rol tinyint not null,
	id_enlace tinyint not null,
	constraint pk_rol_enlaces primary key (id_rol,id_enlace),
	constraint fk_enlaces_rol foreign key (id_rol) references tb_roles(id_rol)
	on delete restrict on update restrict,
	constraint fk_enlaces_enlace foreign key (id_enlace) references tb_enlaces(id_enlace)
	on delete restrict on update restrict
)engine = InnoDB, default charset = utf8;

drop table if exists tb_usuarios;
create table tb_usuarios
(
	dni_user char(8) primary key not null,
	nom_user varchar(80) not null,
    ape_user varchar(80) not null,
    usuario char(8) not null unique,
    clave char(8) not null,
    foto varchar(100),
    id_rol tinyint not null,
    estado boolean default 1,
    fec_reg date not null,
    constraint fk_rol foreign key (id_rol) references tb_roles (id_rol)
)engine = InnoDB, default charset = utf8;


/*POPULATE TABLES*/
/*TB_GENERALES*/
/*TIPOS*/
insert into tb_generales values('RUB01','RUBRO','Seguros',curdate());
insert into tb_generales values('RUB02','RUBRO','Cuentas de Ahorros',curdate());
insert into tb_generales values('RUB03','RUBRO','Créditos y Préstamos',curdate());
insert into tb_generales values('RUB04','RUBRO','Tarjetas de Crédito',curdate());
insert into tb_generales values('RUB05','RUBRO','Comunicaciones',curdate());
insert into tb_generales values('VEH','VEH','Tipos de Vehiculos',curdate());
insert into tb_generales values('DEP','DEP','Departamentos',curdate());
/*Tipos de seguros*/
insert into tb_generales values('SEG01','RUB01','SOAT',curdate());
insert into tb_generales values('SEG02','RUB01','Seguro Oncológico',curdate());
insert into tb_generales values('SEG03','RUB01','Seguro Vehicular',curdate());
/*Departamentos*/
insert into tb_generales values('01','DEP','Amazonas',curdate());
insert into tb_generales values('02','DEP','Ancash',curdate());
insert into tb_generales values('03','DEP','Apurimac',curdate());
insert into tb_generales values('04','DEP','Arequipa',curdate());
insert into tb_generales values('05','DEP','Ayacucho',curdate());
insert into tb_generales values('06','DEP','Cajamarca',curdate());
insert into tb_generales values('07','DEP','Callao',curdate());
insert into tb_generales values('08','DEP','Cusco',curdate());
insert into tb_generales values('09','DEP','Huancavelica',curdate());
insert into tb_generales values('10','DEP','Huanuco',curdate());
insert into tb_generales values('11','DEP','Ica',curdate());
insert into tb_generales values('12','DEP','Junin',curdate());
insert into tb_generales values('13','DEP','La Libertad',curdate());
insert into tb_generales values('14','DEP','Lambayeque',curdate());
insert into tb_generales values('15','DEP','Lima',curdate());
insert into tb_generales values('16','DEP','Loreto',curdate());
insert into tb_generales values('17','DEP','Madre de Dios',curdate());
insert into tb_generales values('18','DEP','Moquegua',curdate());
insert into tb_generales values('19','DEP','Pasco',curdate());
insert into tb_generales values('20','DEP','Piura',curdate());
insert into tb_generales values('21','DEP','Puno',curdate());
insert into tb_generales values('22','DEP','San Martín',curdate());
insert into tb_generales values('23','DEP','Tacna',curdate());
insert into tb_generales values('24','DEP','Tumbes',curdate());
insert into tb_generales values('25','DEP','Ucayali',curdate());
/*Tipos de Vehiculos*/
insert into tb_generales values('VEH01','VEH','Automovil',curdate());
insert into tb_generales values('VEH02','VEH','Camioneta SW 4x2',curdate());
insert into tb_generales values('VEH03','VEH','Camioneta Rural 4x4',curdate());
insert into tb_generales values('VEH04','VEH','Camioneta Pick UP',curdate());
insert into tb_generales values('VEH05','VEH','Camioneta Panel',curdate());
insert into tb_generales values('VEH06','VEH','Motocicletas',curdate());

#Enlaces-----------------------------------------------------------------------------
-- Empresas
insert into tb_enlaces values(null,'Empresas','crudEmpresas.jsp','EMP',curdate(),1);
-- Tarjetas
insert into tb_enlaces values(null,'Tajetas de Crédito','tarjetas.jsp','TARJ',curdate(),1);
-- Cta de ahorros
insert into tb_enlaces values(null,'Ahorros','ahorros.jsp','AHO',curdate(),1);
insert into tb_enlaces values(null,'Cta. Ahorro','crudAhorros.jsp','AHO',curdate(),1);
insert into tb_enlaces values(null,'Cta. Sueldo','crudSueldos.jsp','AHO',curdate(),1);
insert into tb_enlaces values(null,'CTS','crudCts.jsp','AHO',curdate(),1);
insert into tb_enlaces values(null,'Depósito de Pension','crudPension.jsp','AHO',curdate(),1);
insert into tb_enlaces values(null,'Depósito a Plazo Fijo','crudPlazo.jsp','AHO',curdate(),1);
-- Creditos
insert into tb_enlaces values(null,'Créditos','creditos.jsp','CRED',curdate(),1);
insert into tb_enlaces values(null,'Hipotecario','crudHipotecario.jsp','CRED',curdate(),1);
insert into tb_enlaces values(null,'Vehicular','crudVehicular.jsp','CRED',curdate(),1);
insert into tb_enlaces values(null,'Estudiantil','crudEstudiantil.jsp','CRED',curdate(),1);
insert into tb_enlaces values(null,'Personal','crudPersonal.jsp','CRED',curdate(),1);
-- Seguros
insert into tb_enlaces values(null,'Seguros','seguros.jsp','SEG',curdate(),1);
insert into tb_enlaces values(null,'SOAT','crudSoat.jsp','SEG',curdate(),1);
-- Comunicaciones
insert into tb_enlaces values(null,'Comunicaciones','comunicaciones.jsp','COM',curdate(),1);
-- Parametros
insert into tb_enlaces values(null,'Vehiculos','vehiculos.jsp','ADM',curdate(),1);
insert into tb_enlaces values(null,'Rubros','rubros.jsp','ADM',curdate(),1);
insert into tb_enlaces values(null,'Servicios','servicios.jsp','ADM',curdate(),1);
-- Usuario
insert into tb_enlaces values(null,'Usuarios','usuarios.jsp','ADM',curdate(),1);

#Roles
insert into tb_roles values(null,'Administrador',curdate(),1);
insert into tb_roles values(null,'Registrador',curdate(),1);
#Administrador
insert into tb_usuarios values('70417573','Miguel Angel','Cortegana Alvarez','70417573','70417573','',1,1,curdate());
insert into tb_usuarios values('12345678','Demo','Demo','12345678','12345678','',2,1,curdate());

# Detalle de roles y enlaces -----------------
# Administrador
insert into tb_rol_enlaces values(1,1);
insert into tb_rol_enlaces values(1,2);
insert into tb_rol_enlaces values(1,3);
insert into tb_rol_enlaces values(1,4);
insert into tb_rol_enlaces values(1,5);
insert into tb_rol_enlaces values(1,6);
insert into tb_rol_enlaces values(1,7);
insert into tb_rol_enlaces values(1,8);
insert into tb_rol_enlaces values(1,9);
insert into tb_rol_enlaces values(1,10);
insert into tb_rol_enlaces values(1,11);
insert into tb_rol_enlaces values(1,12);
insert into tb_rol_enlaces values(1,13);
insert into tb_rol_enlaces values(1,14);
insert into tb_rol_enlaces values(1,15);
insert into tb_rol_enlaces values(1,16);
insert into tb_rol_enlaces values(1,17);
insert into tb_rol_enlaces values(1,18);
insert into tb_rol_enlaces values(1,19);
insert into tb_rol_enlaces values(1,20);

# Registrador
insert into tb_rol_enlaces values(2,1);
insert into tb_rol_enlaces values(2,2);
insert into tb_rol_enlaces values(2,3);
insert into tb_rol_enlaces values(2,4);
insert into tb_rol_enlaces values(2,5);
insert into tb_rol_enlaces values(2,6);
insert into tb_rol_enlaces values(2,7);
insert into tb_rol_enlaces values(2,8);
insert into tb_rol_enlaces values(2,9);
insert into tb_rol_enlaces values(2,10);
insert into tb_rol_enlaces values(2,11);
insert into tb_rol_enlaces values(2,12);
insert into tb_rol_enlaces values(2,13);
insert into tb_rol_enlaces values(2,14);
insert into tb_rol_enlaces values(2,15);
insert into tb_rol_enlaces values(2,16);

select * from tb_enlaces;