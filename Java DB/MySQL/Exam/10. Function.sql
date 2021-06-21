DELIMITER $$
CREATE FUNCTION `udf_courses_by_client`(phone_num VARCHAR(20))
RETURNS INTEGER
DETERMINISTIC
BEGIN
	
	RETURN (SELECT COUNT(cou.id) FROM clients AS c
	JOIN courses AS cou
	ON cou.client_id = c.id
	WHERE c.phone_number = phone_num);
	
END$$

DELIMITER ;