package ariel.livros.service.validator.student;

import ariel.livros.domain.entity.Student;
import ariel.livros.exception.StudentLoanLimitedExceededException;
import ariel.livros.repository.LoanRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ValidateStudentLoanLimit implements StudentValidation {

    private final LoanRepository repository;

    @Override
    public void validate(Student student) {
        int activeLoans = repository.countByStudentAndActiveTrue(student);
        if (activeLoans >= 5) {
            throw new StudentLoanLimitedExceededException("Estudante atingiu o limite de empréstimos!");
        }
    }
}
