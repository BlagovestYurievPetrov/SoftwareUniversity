DELIMITER $$
CREATE PROCEDURE `udp_courses_by_address`(`address_name` VARCHAR(100))
BEGIN
	SELECT a.`name`, cli.full_name, (
	CASE
    WHEN c.bill <= 20 THEN 'Low'
    WHEN c.bill BETWEEN 21 AND 30 THEN 'Medium'
    ELSE 'High'
    END
) AS `level_of_bill`, car.make, car.`condition`, cat.`name` AS cat_name 
FROM addresses AS a
JOIN courses AS c
ON c.from_address_id = a.id
JOIN clients AS cli
ON cli.id = c.client_id
JOIN cars AS car
ON car.id = c.car_id
JOIN categories AS cat
ON cat.id = car.category_id
WHERE a.`name` = address_name
ORDER BY car.make, cli.full_name;
END $$

DELIMITER ;