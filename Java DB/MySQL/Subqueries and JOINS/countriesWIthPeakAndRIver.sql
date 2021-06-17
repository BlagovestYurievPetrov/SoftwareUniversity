SELECT c.`country_name`, MAX(p.`elevation`) AS `hpe`, MAX(r.`length`) AS `lrl` FROM `countries` AS C
JOIN `mountains_countries` AS mc
ON mc.`country_code` = c.`country_code`
JOIN `mountains` AS m
ON m.`id` = mc.`mountain_id`
JOIN `peaks` AS p
ON p.`mountain_id` = m.`id`
JOIN `countries_rivers` AS cr
ON cr.`country_code` = c.`country_code`
JOIN `rivers` AS r
ON r.`id` = cr.`river_id`
GROUP BY c.`country_name`
ORDER BY `hpe` DESC, `lrl` DESC, `country_name`
LIMIT 5;