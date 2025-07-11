package ariel.livros.service.validator.loan;

import ariel.livros.domain.entity.Loan;

public interface LoanValidation {
    void validate(Loan loan);
}
