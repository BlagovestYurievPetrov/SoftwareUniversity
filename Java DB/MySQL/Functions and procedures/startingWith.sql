DELIMITER $$
CREATE PROCEDURE usp_get_towns_starting_with(`search` VARCHAR(50))
BEGIN
	SELECT `name` FROM `towns`
	WHERE LEFT(`name`,CHAR_LENGTH(`search`)) = search
    ORDER BY `name`;
END $$

DELIMITER ;	

CALL usp_get_towns_starting_with('Red');