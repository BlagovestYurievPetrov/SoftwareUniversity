DELIMITER $$
CREATE FUNCTION `udf_client_cards_count`(client_name VARCHAR(30))
RETURNS INTEGER
DETERMINISTIC
BEGIN
	
	RETURN (SELECT COUNT(c.id) AS cards FROM clients AS cli
	JOIN bank_accounts AS ba
	ON ba.client_id = cli.id
	JOIN cards AS c
	ON c.bank_account_id = ba.id
	WHERE cli.full_name = client_name);
	
END$$

DELIMITER ;