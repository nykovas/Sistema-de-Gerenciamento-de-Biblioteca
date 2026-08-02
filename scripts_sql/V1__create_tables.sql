CREATE TABLE livro(
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(255),
    autor VARCHAR(255),
    genero VARCHAR(255),
    ano_publicacao int,
    estoque int
);

CREATE TABLE cliente(
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255),
    email VARCHAR(255),
    telefone VARCHAR(15),
    esta_ativo BOOLEAN DEFAULT TRUE
);

CREATE TABLE emprestimo(
    id SERIAL PRIMARY KEY,
    id_cliente INTEGER,
    id_livro INTEGER,
    data_emprestimo DATE,
    FOREIGN KEY (id_cliente)
            REFERENCES cliente(id),
    FOREIGN KEY (id_livro)
            REFERENCES livro(id)
);