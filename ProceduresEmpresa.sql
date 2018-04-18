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

drop procedure if exists sp_regEmpresa;
DELIMITER $$
create procedure sp_regEmpresa
(
    p_ruc char(11),
    p_nom varchar(50),
    p_telf varchar(10),
    p_dir varchar(50),
    p_email varchar(50),
    p_cobertura JSON,
    p_logo varchar(100)
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

select * from tb_empresas;