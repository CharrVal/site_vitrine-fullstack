INSERT INTO categories (name) VALUES
    ('Utilitaires'),
    ('Ornements'),
    ('Personnalisable')
ON CONFLICT (name) DO NOTHING;