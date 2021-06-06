#12
SELECT `department_id`, MIN(`salary`) AS `minimum_salary` FROM `employees`
WHERE `department_id` IN (2,5,7) AND `hire_date` > '2000-01-01 00:00:00.000000'
GROUP BY `department_id`
ORDER BY `department_id`;

#13
CREATE TABLE `hpe` AS
SELECT * FROM `employees`
WHERE `salary` > 30000 AND `manager_id` != 42;

UPDATE `hpe`
SET `salary` = `salary` + 5000
WHERE `department_id` = 1;


SELECT `department_id`, AVG(`salary`) AS `avg_salary` FROM `hpe`
GROUP BY `department_id`
ORDER BY `department_id`;
#14
SELECT `department_id`, MAX(`salary`) AS `max_salary` FROM `hpe`
WHERE `salary` BETWEEN 30000 AND 70000
GROUP BY `department_id`
ORDER BY `department_id`;

#15
SELECT COUNT(*) FROM `employees`
WHERE `manager_id`= NULL;


#16
SELECT e.`department_id`, (
	SELECT DISTINCT e2.`salary` FROM `employees` AS e2
    WHERE e2.`department_id` = e.`department_id`
	ORDER BY e2.`salary` DESC
    LIMIT 1 OFFSET 2

) AS `ths`
FROM `employees` AS e
GROUP BY e.`department_id`
HAVING `ths` IS NOT NULL
ORDER BY e.`department_id`
;

#17

SELECT e.`first_name`, e.`last_name`, e.`department_id` 
FROM `employees` AS e
WHERE e.`salary` > (
	SELECT AVG(e2.`salary`) 
	FROM `employees` AS e2
	WHERE e2.`department_id` = e.`department_id`
) 
ORDER BY `department_id`, `employee_id`
LIMIT 10;

#18
SELECT `department_id`, SUM(`salary`) AS `total_salary` FROM `employees`
GROUP BY `department_id`
ORDER BY `department_id`;


