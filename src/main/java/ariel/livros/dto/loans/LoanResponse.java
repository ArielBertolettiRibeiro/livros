package ariel.livros.dto.loans;

import ariel.livros.dto.books.BookSummary;
import ariel.livros.dto.students.StudentSummary;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoanResponse {
    private Long id;
    private StudentSummary student;
    private BookSummary bookSummary;
    private LocalDate loanDate;
    private LocalDate returnDate;
    private boolean active;
}
