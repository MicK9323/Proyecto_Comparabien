use db_comparabien;

drop procedure if exists sp_listaEmpresas;
DELIMITER $$
create procedure sp_listaEmpresas()
Begin
    select * from tb_empresas;
End$$
DELIMITER ;

drop procedure if exists sp_listaDepartamentos;
DELIMITER $$
create procedure sp_listaDepartamentos()
Begin
    select g.id_param, g.descripcion from tb_generales g where g.id_grupo = 'DEP';
End$$
DELIMITER ;

drop procedure if exists sp_buscarEmpresa
DELIMITER $$
create procedure sp_buscarEmpresa( vCod char(5))
Begin
    select * from tb_empresas e where e.id_emp = vCod;
End$$
DELIMITER ;


drop procedure if exists sp_regEmpresa;
DELIMITER $$
create procedure sp_regEmpresa
(
    p_ruc char(11),
    p_nom varchar(100),
    p_telf varchar(10),
    p_dir varchar(100),
    p_email varchar(50),
    p_cobertura JSON,
    p_logo varchar(10)
)
Begin
    declare cod char(5);
    declare cont tinyint;
    set cont = ( select count(*)+1 from tb_empresas );
    set cod = ( select getCorrelativo('E',cont) );
    start transaction;
		insert into tb_empresas values(cod, p_ruc, p_nom, p_telf, p_dir, p_email, p_cobertura, "", curdate(), null);
    commit;
End$$
DELIMITER ;

drop procedure if exists sp_uptEmpresa;
DELIMITER $$
create procedure sp_uptEmpresa
(
	p_id char(5),
    p_telf varchar(10),
    p_dir varchar(100),
    p_email varchar(50)
)
Begin
    start transaction;
		update tb_empresas e set e.telf_empresa = p_telf, e.dir_empresa = p_dir, e.email_empresa = p_email
        where e.id_emp = p_id;
    commit;
End$$
DELIMITER ;

