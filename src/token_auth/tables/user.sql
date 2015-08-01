-- name: create-registered-user-table-if-not-exists!
-- create the registered_user table if it does not exist
CREATE TABLE IF NOT EXISTS registered_user (
   id              SERIAL PRIMARY KEY NOT NULL
   , email         CITEXT             NOT NULL UNIQUE
   , username      CITEXT             NOT NULL UNIQUE
   , password      TEXT               NOT NULL
   , refresh_token TEXT
);

-- name: drop-user-table!
-- Drop the user table
DROP TABLE registered_user;

-- name: insert-test-user
-- insert user for testing
/* This must return a result, otherwise the tests errors */
WITH rows AS (
    INSERT INTO registered_user (email, username, password, refresh_token)
    VALUES ('test@test.com', 'test.user', 'asdf', 'abc123') RETURNING id
)
SELECT id FROM rows;
