INSERT INTO `products_stores`(`product_id`, `store_id`)
SELECT p.`id`, (1) AS `store_id` FROM `products` AS p
LEFT JOIN `products_stores` as ps
ON p.`id` = ps.`product_id`
WHERE ps.`store_id` IS NULL;