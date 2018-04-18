use db_comparabien;

-- LOGIN JUGADORES
Drop procedure if exists sp_login;
DELIMITER $$
Create procedure sp_login
(
vUsuario char(8),
vClave char(8)
)
Begin	
    if not exists (select * from tb_usuarios u where u.usuario = vUsuario) then
		signal sqlstate '45000' set message_text = 'Usuario no registrado';
    else
		if exists (select * from tb_usuarios u where u.usuario = vUsuario and u.estado = 0) then
			signal sqlstate '45000' set message_text = 'Usuario Bloqueado';
        else
			if exists (select * from tb_usuarios u where u.usuario = vUsuario and u.clave != vClave)  then	
					signal sqlstate '45000' set message_text = 'Clave Incorrecta';								
                else
					if exists (select * from tb_usuarios u where u.usuario = vUsuario and u.clave = vClave and u.estado = 1) then
						select u.*, r.nom_rol from tb_usuarios u inner join tb_roles r
                        on u.id_rol = r.id_rol where u.usuario = vUsuario and u.clave = vClave;
					else
						signal sqlstate '45000' set message_text = 'Error General';
					end if;
                end if;
        end if;
    end if;
End$$
DELIMITER ;

-- OBTENER ENLACES
Drop procedure if exists sp_cargarEnlaces;
DELIMITER $$
Create procedure sp_cargarEnlaces
(
vRol int,
vOpcion varchar(5)
)
Begin
		Select
		e.nom_enlace,
		e.ruta
		from tb_roles r inner join tb_rol_enlaces d
        on r.id_rol = d.id_rol inner join tb_enlaces e
        on d.id_enlace = e.id_enlace where d.id_rol = vRol and e.opcion = vOpcion
        order by 1 asc;
End$$
DELIMITER ;

call sp_login('70417573','70417573');

call sp_cargarEnlaces(1,'EMP');
