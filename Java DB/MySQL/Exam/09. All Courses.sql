SELECT a.`name`, (
CASE
	WHEN HOUR(c.`start`) >= 06 AND HOUR(c.`start`) <= 20 THEN 'Day'
	ELSE 'Night'
END
) AS `day_time`, c.bill, cli.full_name, car.make, car.model, cat.name
FROM courses AS c
JOIN addresses AS a
ON a.id = c.from_address_id
JOIN clients AS cli
ON cli.id = c.client_id
JOIN cars AS car
ON car.id = c.car_id
JOIN categories AS cat
ON car.category_id = cat.id
ORDER BY c.id;
