package ariel.livros.service.interfaces;

import ariel.livros.dto.loans.LoanRequest;
import ariel.livros.dto.loans.LoanResponse;
import ariel.livros.dto.loans.LoanSummary;
import java.util.List;

public interface ILoanService {
    LoanResponse create(LoanRequest request);
    LoanResponse findById(Long id);
    void returnLoan(Long id);
    List<LoanSummary> findAll(Long studentId);
}
