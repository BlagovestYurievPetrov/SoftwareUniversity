DELIMITER $$
CREATE FUNCTION `udf_count_colonists_by_destination_planet`(planet_param  VARCHAR(30))
RETURNS INTEGER
DETERMINISTIC
BEGIN
	
	RETURN (SELECT COUNT(c.`id`) FROM planets AS p
	JOIN spaceports AS s
	ON s.planet_id = p.`id`
	JOIN journeys AS j
	ON j.destination_spaceport_id = s.`id`
	JOIN travel_cards AS tc
	ON tc.journey_id = j.id
	JOIN colonists AS c
	ON c.id = tc.colonist_id
	WHERE p.`name` = planet_param);
	
END$$

DELIMITER ;