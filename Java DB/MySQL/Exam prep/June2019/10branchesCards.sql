SELECT b.name, COUNT(c.id) AS count_of_cards FROM branches AS b
LEFT JOIN employees AS e
ON b.id = e.branch_id
LEFT JOIN employees_clients AS ec
ON ec.employee_id = e.id
LEFT JOIN clients AS cli
ON ec.client_id = cli.id
LEFT JOIN bank_accounts AS ba
ON cli.id = ba.client_id
LEFT JOIN cards AS c
ON c.bank_account_id = ba.id
GROUP BY b.name
ORDER
 BY count_of_cards DESC, b.name;