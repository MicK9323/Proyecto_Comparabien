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
    if not exists (select * from tb_usuarios u where u.usuario = vUsuario) then
		signal sqlstate '45000' set message_text = 'Usuario no registrado';
    else
		if exists (select * from tb_usuarios u where u.usuario = vUsuario and u.estado = 0) then
			signal sqlstate '45000' set message_text = 'Usuario Bloqueado';
        else
			if exists (select * from tb_usuarios u where u.usuario = vUsuario and u.clave != sha(vClave))  then	
					signal sqlstate '45000' set message_text = 'Clave Incorrecta';								
                else
					if exists (select * from tb_usuarios u where u.usuario = vUsuario and u.clave = sha(vClave) and u.estado = 1) then
						select * from tb_usuarios u where u.usuario = vUsuario and u.clave = sha(vClave);
                        /*select u.dni_user,
							u.nom_user,
                            u.ape_user,
                            u.usuario,
                            u.clave,
                            u.foto,
                            u.id_rol,
                            r.nom_rol,
                            u.estado,
                            u.foto							
                        from tb_usuarios u inner join tb_roles r
                        on u.id_rol = r.id_rol where u.usuario = vUsuario and u.clave = sha(vClave);*/
					else
						signal sqlstate '45000' set message_text = 'Error General';
					end if;
                end if;
        end if;
    end if;
End$$
DELIMITER ;

call sp_login('70417573','70417573');
select * from tb_usuarios;
select * from tb_roles;