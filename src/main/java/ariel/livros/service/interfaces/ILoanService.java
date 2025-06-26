package ariel.livros.service.interfaces;

import ariel.livros.dto.loans.LoanRequest;
import ariel.livros.dto.loans.LoanResponse;

public interface ILoanService {
    LoanResponse create(LoanRequest request);
}
