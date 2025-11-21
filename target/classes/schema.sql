CREATE TABLE planets (
    planet_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(50) NOT NULL,
    radius_km FLOAT NOT NULL,
    mass_kg FLOAT NOT NULL,
    orbital_period_days INT NOT NULL
);

CREATE TABLE moons (
    moon_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    diameter_km FLOAT NOT NULL,
    orbital_period_days INT NOT NULL,
    planet_id INT NOT NULL
);

CREATE TABLE users (
    user_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role INT NOT NULL
);
