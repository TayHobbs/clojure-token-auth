-- name: all-users
-- Selects all users
SELECT id
       , email
       , username
       , refresh_token
FROM registered_user;
