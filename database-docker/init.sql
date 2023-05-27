CREATE DATABASE crud_oak;

\c crud_oak;

SELECT * FROM pg_extension WHERE extname = 'pgcrypto';

CREATE EXTENSION pgcrypto;