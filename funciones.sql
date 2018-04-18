use db_comparabien;


Drop function if exists getCorrelativo;
DELIMITER $$
CREATE FUNCTION getCorrelativo (cod char(3), cont int) RETURNS varchar(5)
BEGIN
	Declare p_codigo varchar(5);
	IF(cont<10)THEN
		SET p_codigo= CONCAT(cod,'000',cont);
		ELSE IF(cont<100) THEN
			SET p_codigo= CONCAT(cod,'00',cont);
			ELSE IF(cont<1000)THEN
				SET p_codigo= CONCAT(cod,'0',cont);
                ELSE IF(cont<10000)THEN
					SET p_codigo = concat(cod,cont);
				END IF;
			END IF;
		END IF;
	END IF;
    RETURN p_codigo;
END$$
DELIMITER ;

select getCorrelativo('E',495);