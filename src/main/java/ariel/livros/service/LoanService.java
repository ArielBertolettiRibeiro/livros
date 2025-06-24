package ariel.livros.service;

import ariel.livros.domain.entity.Loan;
import ariel.livros.dto.loans.LoanResponse;
import ariel.livros.repository.LoanRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository repository;

}
