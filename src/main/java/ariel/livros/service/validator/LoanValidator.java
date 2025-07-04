package ariel.livros.service.validator;

import ariel.livros.domain.entity.Book;
import ariel.livros.domain.entity.Loan;
import ariel.livros.domain.entity.Student;
import ariel.livros.exception.BookUnavailableException;
import ariel.livros.exception.LoanAlreadyReturnedException;
import ariel.livros.exception.StudentLoanLimitedExceededException;
import ariel.livros.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoanValidator {

    private final LoanRepository repository;

    public void validateBookAvailability(Book book) {
        if (book.getAvailableQuantity() <= 0) {
            throw new BookUnavailableException("Livro indisponível para empréstimo!");
        }
    }

    public void validateStudentLoanLimit(Student student) {
        int activeLoans = repository.countByStudentAndActiveTrue(student);
        if (activeLoans >= 5) {
            throw new StudentLoanLimitedExceededException("Estudante atingiu o limite de empréstimos!");
        }
    }

    public void reserveBook(Book book) {
        if (book.getAvailableQuantity() <= 0) {
            throw new BookUnavailableException("Livro indisponível!");
        }

        book.setAvailableQuantity(book.getAvailableQuantity() - 1);
    }

    public void returnBook(Book book) {
        book.setAvailableQuantity(book.getAvailableQuantity() + 1);
    }

    public void isActiveLoan(Loan loan){
        if (!loan.isActive()) {
            throw new LoanAlreadyReturnedException("Livro já foi devolvido!");
        }
    }
}
