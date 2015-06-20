psql

CREATE DATABASE token_auth;
CREATE DATABASE token_auth_test;

CREATE ROLE auth_user LOGIN;
ALTER ROLE auth_user WITH PASSWORD 'asdf';

GRANT ALL PRIVILEGES ON DATABASE token_auth TO auth_user;
GRANT ALL PRIVILEGES ON DATABASE token_auth_test to auth_user;
