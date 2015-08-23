-- name: all-users
-- Selects all users
SELECT id
       , email
       , username
       , refresh_token
FROM registered_user;

-- name: insert-user<!
-- inserts a user
INSERT INTO registered_user (
    email
    , username
    , password)
VALUES (
    :email
    , :username
    , :password);
