UPDATE cars
SET `condition` = 'C'
WHERE mileage >= 800000 OR mileage IS NULL AND make != 'Mercedes-Benz' AND year <= 2010;