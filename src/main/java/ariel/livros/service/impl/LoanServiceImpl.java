package ariel.livros.service.impl;

import ariel.livros.domain.entity.Book;
import ariel.livros.domain.entity.Loan;
import ariel.livros.domain.entity.Student;
import ariel.livros.dto.loans.LoanRequest;
import ariel.livros.dto.loans.LoanResponse;
import ariel.livros.mapper.LoanMapper;
import ariel.livros.repository.BookRepository;
import ariel.livros.repository.LoanRepository;
import ariel.livros.repository.StudentRepository;
import ariel.livros.service.interfaces.ILoanService;
import ariel.livros.service.validator.LoanValidator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements ILoanService {

    private final LoanRepository repository;
    private final LoanMapper mapper;
    private final StudentRepository studentRepository;
    private final BookRepository bookRepository;
    private final LoanValidator validator;

    @Transactional
    @Override
    public LoanResponse create(LoanRequest request) {
        Student student = studentRepository.findById(request.student())
                .orElseThrow(() -> new RuntimeException("Estudante não encontrado!"));

        Book book = bookRepository.findById(request.book())
                .orElseThrow(() -> new RuntimeException("Livro não encontrado!"));

        validator.validateBookAvailability(book);
        validator.validateStudentLoanLimit(student);

        Loan loan = new Loan();
        loan.setStudent(student);
        loan.setBook(book);
        loan.setLoanDate(LocalDate.now());
        loan.setActive(true);

        validator.reserveBook(book);
        return mapper.toResponse(repository.save(loan));
    }
}
