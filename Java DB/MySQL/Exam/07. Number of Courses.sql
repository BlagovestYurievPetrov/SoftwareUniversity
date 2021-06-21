SELECT c.id, c.make, c.mileage, COUNT(cou.id) AS count_of_courses, ROUND(AVG(cou.bill), 2) AS avg_bill FROM cars AS c
LEFT JOIN courses AS cou
ON cou.car_id = c.id
GROUP BY c.id
HAVING count_of_courses NOT IN (2)
ORDER BY count_of_courses DESC, c.id;