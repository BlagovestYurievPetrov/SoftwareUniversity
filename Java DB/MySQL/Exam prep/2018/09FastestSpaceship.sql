SELECT sps.`name`, spp.`name` FROM spaceships AS sps
JOIN journeys AS j
ON j.spaceship_id = sps.id
JOIN spaceports AS spp
ON spp.id = j.destination_spaceport_id
ORDER BY sps.light_speed_rate DESC
LIMIT 1;