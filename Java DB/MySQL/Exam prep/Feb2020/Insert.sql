INSERT INTO `coaches`(`first_name`, `last_name`, `salary`, `coach_level`)
SELECT `first_name`, `last_name`, p.`salary` * 2 AS `salary`, CHAR_LENGTH(p.`first_name`) AS `coach_level`
FROM `players` as p
WHERE p.`age` >= 45;



