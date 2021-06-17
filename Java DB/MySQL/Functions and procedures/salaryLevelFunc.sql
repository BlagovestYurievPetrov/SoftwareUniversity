DELIMITER $$
CREATE FUNCTION `ufn_get_salary_level`(`salary_level` DECIMAL(19,4))
RETURNS VARCHAR(20)
DETERMINISTIC
BEGIN
	DECLARE result VARCHAR(20);
	IF `salary_level` < 30000 THEN SET result = 'Low';
    ELSEIF `salary_level` BETWEEN 30000 AND 50000 THEN SET result = 'Average';
    ELSEIF `salary_level` > 50000 THEN SET result = 'High';
    END IF;
    
    RETURN result;
END;

CREATE PROCEDURE `usp_get_employees_by_salary_level`(`level` VARCHAR(20))
BEGIN
	SELECT e.`first_name`, e.`last_name` FROM `employees` as e
	WHERE (select ufn_get_salary_level(e.salary)) = `level`
	ORDER BY e.`first_name` DESC, e.`last_name` DESC;
END $$
DELIMITER ;


