-- name: all-users
-- Selects all users
SELECT id
       , email
       , username
       , refresh_token
FROM registered_user;

-- name: get-user-by-email
-- Selects user by email
SELECT id
       , email
       , username
       , refresh_token
FROM   registered_user
WHERE  email = :email;

-- name: get-user-by-username
-- Selects user by username
SELECT id
       , email
       , username
       , refresh_token
FROM   registered_user
WHERE  username = :username;

-- name: get-user-by-refresh-token
-- Selects user by refresh token
SELECT id
       , email
       , username
       , refresh_token
FROM   registered_user
WHERE  refresh_token = :refresh_token;

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
