DELIMITER //
CREATE PROCEDURE udp_update_product_price(address_name VARCHAR (50))
BEGIN
	IF LEFT(address_name, 1) = '0' 
		THEN 
		UPDATE products SET  `price` = `price`+ 100;
	ELSE
		UPDATE products SET  `price` = `price` + 200;
END IF;
END//
DELIMITER ;