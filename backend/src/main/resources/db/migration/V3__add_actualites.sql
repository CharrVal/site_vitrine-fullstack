INSERT INTO actualites(title, description) VALUES
    ('Actualité 1', 'Une description test'),
    ('Actualité 2', 'Une description à tester')
ON CONFLICT (title) DO NOTHING;