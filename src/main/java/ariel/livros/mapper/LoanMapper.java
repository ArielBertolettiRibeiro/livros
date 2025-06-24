package ariel.livros.mapper;

import ariel.livros.domain.entity.Loan;
import ariel.livros.domain.entity.Student;
import ariel.livros.dto.books.BookSummary;
import ariel.livros.dto.loans.LoanResponse;
import ariel.livros.dto.students.StudentResponse;
import ariel.livros.dto.students.StudentSummary;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoanMapper {

    private final ModelMapper mapper;

    public LoanResponse toResponse(Loan loan) {
        return new LoanResponse(
                loan.getId(),
                new StudentSummary(loan.getStudent().getId(), loan.getStudent().getName()),
                new BookSummary(loan.getBook().getId(), loan.getBook().getTitle(), loan.getBook().getAuthor()),
                        loan.getLoanDate(),
                        loan.getReturnDate(),
                        loan.isActive()
        );
    }
}
