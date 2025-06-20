DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE users (
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    PRIMARY KEY (username)
);

DROP TABLE IF EXISTS aircraft_models CASCADE;

CREATE TABLE aircraft_models (
    manufacturer VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    variant VARCHAR(255) NOT NULL,
    full_model_name VARCHAR(255) GENERATED ALWAYS AS (manufacturer || '-' || model || '-' || variant) STORED,
    PRIMARY KEY (manufacturer, model, variant),
    UNIQUE (full_model_name)
);

DROP TABLE IF EXISTS user_favorite_models CASCADE;

CREATE TABLE user_favorite_models (
    username VARCHAR(255) NOT NULL,
    full_model_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (username, full_model_name),
    FOREIGN KEY (username) REFERENCES users(username) ON DELETE CASCADE,
    FOREIGN KEY (full_model_name) REFERENCES aircraft_models(full_model_name) ON DELETE CASCADE
);