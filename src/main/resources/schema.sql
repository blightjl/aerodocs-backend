DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE users (
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    PRIMARY KEY (username)
);