package ariel.livros.service.interfaces;

import ariel.livros.dto.students.StudentRequest;
import ariel.livros.dto.students.StudentResponse;
import ariel.livros.dto.students.StudentSummary;
import ariel.livros.dto.students.StudentUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IStudentService {

    StudentResponse create(StudentRequest request);

    StudentResponse findById(Long id);

    Page<StudentSummary> findAll(Pageable pageable);

    StudentResponse update(Long id, StudentUpdateRequest request);

    void delete(Long id);
}
