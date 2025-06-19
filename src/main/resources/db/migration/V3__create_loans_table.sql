CREATE TABLE loans(
    id SERIAL PRIMARY KEY,
    student_id INTEGER NOT NULL,
    book_id INTEGER NOT NULL,
    loan_date DATE NOT NULL,
    return_date DATE,
    active BOOLEAN NOT NULL DEFAULT TRUE,

    CONSTRAINT fk_loans_student
            FOREIGN KEY (student_id)
            REFERENCES student(id),

    CONSTRAINT fk_loans_book
            FOREIGN KEY (book_id)
            REFERENCES books(id)
);