package ariel.livros.dto.loans;

import ariel.livros.domain.entity.Student;
import ariel.livros.dto.books.BookSummary;
import ariel.livros.dto.students.StudentSummary;

public record LoanSummary(
        Long id,
        StudentSummary student,
        BookSummary bookSummary,
        boolean active
) {}
