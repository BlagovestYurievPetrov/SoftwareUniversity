DELIMITER $$
CREATE FUNCTION `udf_stadium_players_count`(stadium_name VARCHAR(30))
RETURNS INTEGER
DETERMINISTIC
BEGIN
	
	RETURN (SELECT (COUNT(p.`id`)) AS `count` FROM `players` AS p
    JOIN `teams` AS t
	ON t.`id` = p.`team_id`
	JOIN `stadiums` AS s
	ON s.`id`= t.`stadium_id`
    WHERE s.`name` = stadium_name);
	
END$$

DELIMITER ;
