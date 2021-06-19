SELECT card.id, CONCAT_WS(' : ', card.card_number, cli.full_name) AS card_token FROM clients AS cli
JOIN bank_accounts as ba
ON cli.id = ba.client_id
JOIN cards AS card
ON card.bank_account_id = ba.id
ORDER BY card.id DESC;