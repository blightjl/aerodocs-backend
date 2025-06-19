DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE users (
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    PRIMARY KEY (username)
);

DROP TABLE IF EXISTS aircraft_models;

CREATE TABLE aircraft_models (
    manufacturer VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    variant VARCHAR(255) NOT NULL,
    full_model_name VARCHAR(765) GENERATED ALWAYS AS (manufacturer || '-' || model || '-' || variant) STORED,
    PRIMARY KEY (manufacturer, model, variant),
    UNIQUE (full_model_name)
);