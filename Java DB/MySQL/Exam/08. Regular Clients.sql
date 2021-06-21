SELECT cli.full_name, COUNT(cars.id) AS count_of_cars, SUM(cour.bill) AS total_sum FROM clients AS cli
JOIN courses AS cour
ON cli.id = cour.client_id
JOIN cars AS cars
ON cars.id = cour.car_id
GROUP BY cli.id
HAVING SUBSTRING(cli.full_name, 2, 1) = 'a' AND count_of_cars >1
ORDER BY cli.full_name;