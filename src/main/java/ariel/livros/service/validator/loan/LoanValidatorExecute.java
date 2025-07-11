package ariel.livros.service.validator.loan;

import ariel.livros.domain.entity.Loan;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LoanValidatorExecute {

    private final List<LoanValidation> validations;

    public void validateAll(Loan loan) {
        for (LoanValidation v: validations) {
            v.validate(loan);
        }
    }

}
