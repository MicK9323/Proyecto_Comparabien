-- =============================================
-- CREACION DE BASE DE DATOS
-- =============================================
drop database if exists db_dondeestudiar;
create database db_dondeestudiar;
Use db_dondeestudiar;

-- =============================================
-- CREACION DE TABLA TB_GENERALES
-- =============================================
Drop table if exists tb_generales;
create table tb_generales
(
	id_param char(5) primary key not null,
    id_grupo char(5) not null,
    desc_param varchar(50) not null,
    estado boolean default 1,
    fec_reg date not null
) engine = InnoDB, default charset = utf8;

-- =============================================
-- CREACION DE TABLA TB_UBIGUEOS
-- =============================================
Drop table if exists tb_ubigueos;
CREATE TABLE tb_ubigueos(
	cod_ubigueo char(6) primary key not null,
	cod_dep char(2),
	desc_dep varchar(50),
	cod_prov char(2),
	desc_prov varchar(50),
	cod_dist char(2),
	desc_dist varchar(50)
)engine = InnoDB, default charset = utf8;

-- =============================================
-- CREACION DE TABLAS TB_AREAS
-- =============================================
Drop table if exists tb_areas;
Create table tb_areas
(
	id_area int primary key auto_increment,
    desc_area varchar(100),
    estado boolean not null default 1,
    fec_reg date not null
) engine = InnoDB, default charset = utf8;

-- =============================================
-- CREACION DE TABLA TB_CARRERAS
-- =============================================
Drop table if exists tb_carreras;
Create table tb_carreras
(
	id_carrera int primary key auto_increment,
    nom_carrera varchar(100) not null,
    id_area int not null,
    tipo_carrera char(5) not null,
    desc_carrera varchar(220) not null,
    duracion tinyint not null,
    cursos json,
    perfil_profesional json,
    perfil_laboral json,
    popularidad smallint,
    estado boolean not null default 1,
    fec_reg date not null,
    constraint fk_area foreign key (id_area) references tb_areas(id_area),
    constraint fk_tipo_carrera foreign key (tipo_carrera) references tb_generales(id_param)
) engine = InnoDB, default charset = utf8;

-- =============================================
-- CREACION DE TABLA TB_INSTITUCIONES
-- =============================================
Drop table if exists tb_instituciones;
Create table tb_instituciones
(
	id_institucion int primary key auto_increment,
    ruc char(11) not null,
    nom_institucion varchar(100) not null,
    tipo_institucion char(5) not null,
    tipo_gestion char(5) not null,
    telf_institucion varchar(10),
    dir_web varchar(120) not null,
    reputacion smallint not null,
    residencial boolean default 0,
    logo varchar(120) not null,
    estado boolean default 1,
    fec_reg date not null,
    constraint fk_tipo_inst foreign key (tipo_institucion) references tb_generales(id_param),
    constraint fk_tipo_gest foreign key (tipo_gestion) references tb_generales(id_param)
) engine = InnoDB, default charset = utf8;

-- =============================================
-- CREACION DE TABLAS TB_SEDES
-- =============================================
Drop table if exists tb_sedes;
Create table tb_sedes
(
	id_sede int auto_increment,
    id_institucion int not null,
	nom_sede varchar(120) not null,
	cod_ubigueo char(6) not null,
	direccion varchar(120) not null,
	telf varchar(10) not null,
	coordenada_x text,
	coordenada_y text,
	estado boolean not null default 1,
	fec_reg date not null,
    constraint fk_ubigueo foreign key(cod_ubigueo) references tb_ubigueos(cod_ubigueo),
    constraint fk_institucion foreign key(id_institucion) references tb_instituciones(id_institucion),
    constraint pk_sede primary key(id_sede, id_institucion)
)engine = InnoDB, default charset = utf8;

-- =============================================
-- CREACION DE TABLA DETALLE SEDE CARRERA
-- =============================================
Drop table if exists det_sede_carrera;
Create table det_sede_carrera
(
	id_carrera int not null,
    id_sede int not null,
    acreditado boolean default 0,
    costo_anual double not null,
    postulaciones_anual tinyint not null,
    rel_ingresantes_postulantes tinyint,
    constraint fk_carrera foreign key(id_carrera) references tb_carreras(id_carrera),
    constraint fk_sede foreign key(id_sede) references tb_sedes(id_sede),
    constraint pk_det_sede_carrera primary key (id_carrera, id_sede)
)engine = InnoDB, default charset = utf8;

-- =============================================
-- CREACION DE TABLAS DE ROLES
-- =============================================
Drop table if exists tb_roles;
CREATE TABLE tb_roles (
    id_rol TINYINT PRIMARY KEY AUTO_INCREMENT,
    nom_rol VARCHAR(30) NOT NULL,
    fec_reg DATE NOT NULL,
    estado BOOLEAN DEFAULT 1
)engine = InnoDB, default charset = utf8;

-- =============================================
-- CREACION DE TABLAS DE ENLACES
-- =============================================
/*Drop table if exists tb_enlaces;
CREATE TABLE tb_enlaces (
    id_enlace TINYINT PRIMARY KEY AUTO_INCREMENT,
    nom_enlace VARCHAR(45) NOT NULL,
    ruta VARCHAR(45) NOT NULL,
    opcion VARCHAR(5),
    fec_reg DATE NOT NULL,
    estado BOOLEAN DEFAULT 1
)engine = InnoDB, default charset = utf8;*/

-- =============================================
-- CREACION DE TABLAS DETALLE ROL - ENLACES
-- =============================================
/*Drop table if exists tb_rol_enlaces;
Create table tb_rol_enlaces
(
	id_rol tinyint not null,
	id_enlace tinyint not null,
	constraint pk_rol_enlaces primary key (id_rol,id_enlace),
	constraint fk_enlaces_rol foreign key (id_rol) references tb_roles(id_rol)
	on delete restrict on update restrict,
	constraint fk_enlaces_enlace foreign key (id_enlace) references tb_enlaces(id_enlace)
	on delete restrict on update restrict
)engine = InnoDB, default charset = utf8;*/

-- =============================================
-- CREACION DE TABLA DE USUARIOS
-- *La contraseña sera encryptada con SHA
-- =============================================
drop table if exists tb_usuarios;
create table tb_usuarios
(
	dni_user char(8) primary key not null,
	nom_user varchar(80) not null,
    ape_user varchar(80) not null,
    usuario char(8) not null unique,
    clave varchar(80) not null,
    foto varchar(100),
    id_rol tinyint not null,
    estado boolean default 1,
    fec_reg date not null,
    constraint fk_rol foreign key (id_rol) references tb_roles (id_rol)
)engine = InnoDB, default charset = utf8;


-- =============================================
-- --------------DATA GENERAL-------------------
-- =============================================
/*TIPOS  DE CARRERA*/
insert into tb_generales values('TC001','TCARR','Técnica',1,curdate());
insert into tb_generales values('TC002','TCARR','Universitaria',1,curdate());
/*TIPOS  DE INSTITUCIONES*/
insert into tb_generales values('TI001','TINST','Instituto',1,curdate());
insert into tb_generales values('TI002','TINST','Universidad',1,curdate());
/*TIPOS  DE GESTION*/
insert into tb_generales values('TG001','TGEST','Pública',1,curdate());
insert into tb_generales values('TG002','TGEST','Privada',1,curdate());
select * from tb_generales;
/*ROLES*/
insert into tb_roles values(null,'Administrador',curdate(),1);
/*USUARIOS*/
insert into tb_usuarios values('70417573','Miguel Angel','Cortegana Alvarez','70417573','6257b312addd71a858e484e683c6ef03bae518de','',1,1,curdate());
insert into tb_usuarios values('12345678','Demo','Demo','12345678','7c222fb2927d828af22f592134e8932480637c0d','',1,1,curdate());

select * from tb_usuarios;

select * from tb_ubigueos;

