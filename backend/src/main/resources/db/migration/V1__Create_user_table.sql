INSERT INTO roles (libelle)
SELECT 'ADMIN'
WHERE NOT EXISTS (
    SELECT 1 FROM roles WHERE libelle = 'ADMIN'
);

INSERT INTO users (username, password, id_roles)
SELECT
    'admin',
    '$2a$10$Dow1FXQK1cM.U5X/8cGkU.f6Oq9x7yCz5gq7Pv9/NzD4P0Yc5fT6a',
    (SELECT id FROM roles WHERE libelle = 'ADMIN')
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE username = 'admin'
);