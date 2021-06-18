SELECT p.`name`, p.`price`, p.`best_before`, CONCAT(LEFT(p.`description`, 10), '...') AS `short_description`, pic.`url`
FROM `products` AS p
JOIN `pictures` as pic
ON pic.`id` = p.`picture_id`
WHERE char_length(p.`description`) > 100 AND YEAR(pic.`added_on`) < 2019 AND p.`price` > 20
ORDER BY p.`price` DESC;