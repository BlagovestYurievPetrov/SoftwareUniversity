SELECT p.`name`, COUNT(j.id) AS journeys_count FROM planets AS p
JOIN spaceports AS s
ON s.planet_id = p.`id`
JOIN journeys AS j
ON j.destination_spaceport_id = s.`id`
GROUP BY p.`id`
ORDER BY journeys_count DESC,p.`name` ASC;