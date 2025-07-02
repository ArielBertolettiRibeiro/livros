package ariel.livros.service.impl;

import ariel.livros.domain.entity.Book;
import ariel.livros.domain.entity.Loan;
import ariel.livros.domain.entity.Student;
import ariel.livros.dto.loans.LoanRequest;
import ariel.livros.dto.loans.LoanResponse;
import ariel.livros.dto.loans.LoanSummary;
import ariel.livros.exception.BookNotFoundException;
import ariel.livros.exception.LoanNotFoundExcpetion;
import ariel.livros.exception.StudentNotFoundException;
import ariel.livros.mapper.LoanMapper;
import ariel.livros.repository.BookRepository;
import ariel.livros.repository.LoanRepository;
import ariel.livros.repository.StudentRepository;
import ariel.livros.service.interfaces.ILoanService;
import ariel.livros.service.validator.LoanValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

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
        Student student = studentRepository.findById(request.getStudent())
                .orElseThrow(() -> new StudentNotFoundException("Estudante não encontrado!"));

        Book book = bookRepository.findById(request.getBook())
                .orElseThrow(() -> new BookNotFoundException("Livro não encontrado!"));

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

    @Transactional(readOnly = true)
    @Override
    public LoanResponse findById(Long id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new LoanNotFoundExcpetion("Id não encontrado!"));
    }

    @Transactional
    @Override
    public void returnLoan(Long id) {
        Loan loan = repository.findById(id)
                .orElseThrow(() -> new LoanNotFoundExcpetion("Empréstimo não encontrado!"));

        validator.isActiveLoan(loan);
        loan.setActive(false);
        loan.setReturnDate(LocalDate.now());

        Book book = loan.getBook();
        validator.returnBook(book);

        repository.save(loan);
    }

    @Transactional(readOnly = true)
    @Override
    public List<LoanSummary> findAll(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Estudante não encontrado!"));

        List<Loan> loans = repository.findByStudentAndActiveTrue(student);

        return loans
                .stream()
                .map(mapper::toSummary)
                .toList();
    }

}
