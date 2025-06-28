package ariel.livros.dto.loans;

import ariel.livros.dto.books.BookSummary;
import ariel.livros.dto.students.StudentSummary;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoanSummary {
    private Long id;
    private StudentSummary student;
    private BookSummary bookSummary;
    private boolean active;
}
