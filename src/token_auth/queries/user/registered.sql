-- name: all-users
-- Selects all users
SELECT id
       ,username
       ,password
       ,refresh_token
       ,email
FROM registered_user;
