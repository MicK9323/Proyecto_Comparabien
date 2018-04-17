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
						select * from tb_usuarios u where u.usuario = vUsuario and u.clave = vClave;
					else
						signal sqlstate '45000' set message_text = 'Error General';
					end if;
                end if;
        end if;
    end if;
End$$
DELIMITER ;

call sp_login('70417573','70417573');
call sp_login('12345678','12345678');

select * from tb_usuarios;
