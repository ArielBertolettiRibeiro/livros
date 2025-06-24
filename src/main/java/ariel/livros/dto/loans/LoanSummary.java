package ariel.livros.dto.loans;

import ariel.livros.domain.entity.Student;
import ariel.livros.dto.books.BookSummary;

public record LoanSummary(
        Long id,
        Student student,
        BookSummary bookSummary,
        boolean active
) {}
