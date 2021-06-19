SELECT sps.`name`, sps.manufacturer FROM spaceships AS sps
JOIN journeys AS j
ON j.spaceship_id = sps.id
JOIN travel_cards AS tc
ON tc.journey_id = j.id
JOIN colonists AS c
ON tc.colonist_id = c.id
WHERE tc.job_during_journey = 'Pilot' AND c.birth_date > 19890101
ORDER BY sps.`name`;