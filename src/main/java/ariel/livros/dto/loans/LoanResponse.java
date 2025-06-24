package ariel.livros.dto.loans;

import ariel.livros.domain.entity.Student;
import ariel.livros.dto.books.BookResponse;
import ariel.livros.dto.books.BookSummary;
import ariel.livros.dto.students.StudentSummary;

import java.time.LocalDate;

public record LoanResponse(
        Long id,
        StudentSummary student,
        BookSummary bookSummary,
        LocalDate loanDate,
        LocalDate returnDate,
        boolean active
) {}
