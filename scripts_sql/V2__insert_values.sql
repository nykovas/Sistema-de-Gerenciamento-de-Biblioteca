INSERT INTO cliente (nome, email, telefone, esta_ativo)
VALUES
    ('Ana Souza', 'ana.souza@email.com', '11987654321', TRUE),
    ('Bruno Lima', 'bruno.lima@email.com', '11976543210', TRUE),
    ('Carla Mendes', 'carla.mendes@email.com', '21998765432', TRUE),
    ('Daniel Rocha', 'daniel.rocha@email.com', '31991234567', TRUE),
    ('Eduarda Martins', 'eduarda.martins@email.com', '41999887766', FALSE),
    ('Felipe Costa', 'felipe.costa@email.com', '51995554433', TRUE),
    ('Gabriela Alves', 'gabriela.alves@email.com', '11991112222', TRUE),
    ('Henrique Oliveira', 'henrique.oliveira@email.com', '71994443322', TRUE);

INSERT INTO livro (titulo, autor, genero, ano_publicacao, estoque)
VALUES
    ('Clean Code', 'Robert C. Martin', 'Programação', 2008, 5),
    ('Effective Java', 'Joshua Bloch', 'Programação', 2018, 3),
    ('Java: Como Programar', 'Paul Deitel', 'Programação', 2020, 4),
    ('O Hobbit', 'J.R.R. Tolkien', 'Fantasia', 1937, 6),
    ('O Senhor dos Anéis', 'J.R.R. Tolkien', 'Fantasia', 1954, 2),
    ('Dom Casmurro', 'Machado de Assis', 'Romance', 1899, 7),
    ('1984', 'George Orwell', 'Ficção Científica', 1949, 5),
    ('A Revolução dos Bichos', 'George Orwell', 'Ficção', 1945, 4),
    ('Harry Potter e a Pedra Filosofal', 'J.K. Rowling', 'Fantasia', 1997, 8),
    ('Código Limpo na Prática', 'Rafael Winterhalter', 'Programação', 2022, 2);

INSERT INTO emprestimo (id_cliente, id_livro, data_emprestimo)
VALUES
    (1, 1, '2026-07-20'),
    (2, 4, '2026-07-21'),
    (3, 7, '2026-07-22'),
    (4, 2, '2026-07-23'),
    (1, 10, '2026-07-24'),
    (6, 5, '2026-07-25'),
    (7, 9, '2026-07-26'),
    (8, 3, '2026-07-27'),
    (2, 8, '2026-07-28'),
    (3, 6, '2026-07-29');
