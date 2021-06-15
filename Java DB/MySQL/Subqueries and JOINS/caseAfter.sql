SELECT e.`employee_id`, e.`first_name`, 
(CASE 
	WHEN p.`start_date` >= 20050101 THEN NULL
    ELSE p.`name`
END) AS
`project_name` FROM `employees` AS e
JOIN `employees_projects` as ep
ON e.`employee_id` = ep.`employee_id`
JOIN `projects` AS p
ON ep.`project_id` = p.`project_id`
WHERE e.`employee_id` = 24
ORDER BY p.`name`;