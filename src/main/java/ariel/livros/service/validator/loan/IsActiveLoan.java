package ariel.livros.service.validator.loan;

import ariel.livros.domain.entity.Loan;
import ariel.livros.exception.LoanAlreadyReturnedException;

public class IsActiveLoan implements LoanValidation {
    @Override
    public void validate(Loan loan) {
        if (!loan.isActive()) {
            throw new LoanAlreadyReturnedException("Livro já foi devolvido!");
        }
    }
}
