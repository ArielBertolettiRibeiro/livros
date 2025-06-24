package ariel.livros.dto.loans;

import ariel.livros.domain.entity.Book;
import ariel.livros.domain.entity.Student;

public record LoanRequest(
        Long student,
        Long book
) {}
