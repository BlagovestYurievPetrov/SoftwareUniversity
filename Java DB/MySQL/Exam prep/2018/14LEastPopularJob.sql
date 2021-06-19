SELECT tc.job_during_journey FROM travel_cards AS tc
JOIN journeys AS j
ON j.id = tc.journey_id
WHERE j.id = (SELECT id FROM journeys
ORDER BY journey_end - journey_start DESC
LIMIT 1)
GROUP BY tc.job_during_journey
ORDER BY COUNT(tc.job_during_journey) ASC
LIMIT 1;

