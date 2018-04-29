use db_dondeestudiar;

-- LOGIN JUGADORES
Drop procedure if exists sp_login;
DELIMITER $$
Create procedure sp_login
(
vUsuario char(8),
vClave char(8)
)
Begin
	select * from tb_usuarios u where u.usuario = vUsuario and u.clave = sha(vClave);
End$$
DELIMITER ;


-- Cargar ubigueo
Drop procedure if exists sp_Ubigueo;
DELIMITER $$
Create procedure sp_Ubigueo
(
	vUbicacion varchar(80)
)
Begin
	if( length(vUbicacion)>2 ) then
		Select 
		* 
		from tb_ubigueos u 
		where u.desc_dist like (concat(upper(vUbicacion),'%'));
	end if;
End$$
DELIMITER ;

call sp_Ubigueo('lim');

select sha('12345678');
select * from tb_usuarios;

select * from tb_instituciones;
select * from tb_sedes;

