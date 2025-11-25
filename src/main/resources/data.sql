INSERT INTO planets (name, type, radius_km, mass_kg, orbital_period_days)
VALUES ('Earth', 'terrestrial', 6371, 5.97e24, 365),
       ('Jupiter', 'gas giant', 69911, 1.90e27, 4333);

INSERT INTO moons (name, diameter_km, orbital_period_days, planet_id)
VALUES ('Moon', 3474, 27, 1),
       ('Europa', 3121, 85, 2);

