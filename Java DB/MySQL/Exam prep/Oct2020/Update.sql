SET FOREIGN_KEY_CHECKS=0;
UPDATE `employees` AS e1
SET e1.`manager_id` = 3 AND e1.`salary` = e1.`salary` - 500
WHERE YEAR(e1.`hire_date`) > 2003
AND e1.`store_id` NOT IN (5, 14);