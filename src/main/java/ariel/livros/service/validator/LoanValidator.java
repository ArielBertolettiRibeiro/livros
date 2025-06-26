package ariel.livros.service.validator;

import ariel.livros.domain.entity.Book;
import ariel.livros.domain.entity.Student;
import ariel.livros.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoanValidator {

    private final LoanRepository repository;

    public void validateBookAvailability(Book book) {
        if (book.getAvailableQuantity() <= 0) {
            throw new RuntimeException("Livro indisponível para empréstimo!");
        }
    }

    public void validateStudentLoanLimit(Student student) {
        int activeLoans = repository.countByStudentAndActiveTrue(student);
        if (activeLoans >= 5) {
            throw new RuntimeException("Estudante atingiu o limite de empréstimos!");
        }
    }

    public void reserveBook(Book book) {
        if (book.getAvailableQuantity() <= 0) {
            throw new RuntimeException("Livro indisponível!");
        }

        book.setAvailableQuantity(book.getAvailableQuantity() - 1);
    }
}
