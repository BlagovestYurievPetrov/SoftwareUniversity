SELECT t.`name` AS `team_name`, t.`established`, t.`fan_base`, 
(SELECT COUNT(*) FROM `players` WHERE `team_id` = t.`id`) AS `players_count`
FROM `teams` AS t
GROUP BY t.`id`
ORDER BY `players_count` DESC, t.`fan_base` DESC;