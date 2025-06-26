package ariel.livros.repository;

import ariel.livros.domain.entity.Loan;
import ariel.livros.domain.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    int countByStudentAndActiveTrue(Student student);
}
