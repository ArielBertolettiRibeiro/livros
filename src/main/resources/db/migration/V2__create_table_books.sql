CREATE TABLE books(
    id SERIAL PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    author VARCHAR(150) NOT NULL,
    publication_year INTEGER NOT NULL,
    available_quantity INTEGER NOT NULL DEFAULT 0
);