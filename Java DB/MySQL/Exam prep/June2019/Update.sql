UPDATE `employees_clients` AS ec
SET ec.`employee_id` = (SELECT ecc.`employee_id` FROM (SELECT * FROM `employees_clients`) AS ecc
GROUP BY ecc.`employee_id`
ORDER BY COUNT(ecc.`client_id`) ASC, ecc.`employee_id` ASC
LIMIT 1)
WHERE ec.`employee_id` = ec.`client_id`;
