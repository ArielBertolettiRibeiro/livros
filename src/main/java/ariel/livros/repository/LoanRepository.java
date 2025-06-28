package ariel.livros.repository;

import ariel.livros.domain.entity.Loan;
import ariel.livros.domain.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    int countByStudentAndActiveTrue(Student student);
    List<Loan> findByStudentAndActiveTrue(Student student);
}
