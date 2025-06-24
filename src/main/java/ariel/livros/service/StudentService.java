package ariel.livros.service;

import ariel.livros.domain.entity.Student;
import ariel.livros.dto.students.StudentRequest;
import ariel.livros.dto.students.StudentResponse;
import ariel.livros.mapper.StudentMapper;
import ariel.livros.repository.StudentRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;
    private final StudentMapper mapper;

    public StudentResponse create(@RequestBody @Valid StudentRequest student) {
        repository.findByRegistration(student.registration())
                .ifPresent(students -> {
                    throw new RuntimeException("Aluno já existe!");
                });

        return mapper.toResponse(repository.save(mapper.toEntity(student)));
    }
}
